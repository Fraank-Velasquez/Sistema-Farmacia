/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.facade;

import com.mycompany.farmaciasystem.decorator.DescuentoFijoDecorator;
import com.mycompany.farmaciasystem.decorator.IPrecio;
import com.mycompany.farmaciasystem.decorator.PrecioBase;
import com.mycompany.farmaciasystem.decorator.PromocionDecorator;
import com.mycompany.farmaciasystem.modelo.DTO.ItemVentaDTO;
import com.mycompany.farmaciasystem.modelo.DTO.VentaDTO;
import com.mycompany.farmaciasystem.modelo.entidades.DetalleVenta;
import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.modelo.entidades.Promocion;
import com.mycompany.farmaciasystem.modelo.entidades.Venta;
import com.mycompany.farmaciasystem.repository.Implementaciones.DetalleVentaRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.LoteRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.ProductoRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.PromocionRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.VentaRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IDetalleVentaRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.ILoteRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.IProductoRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.IPromocionRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.IVentaRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank
 */
public class VentaFacade {

    private final IVentaRepository ventaRepository;
    private final IProductoRepository productoRepository;
    private final ILoteRepository loteRepository;
    private final IPromocionRepository promocionRepository;

    public VentaFacade() {
        this.ventaRepository = new VentaRepositoryImpl();
        this.productoRepository = new ProductoRepositoryImpl();
        this.loteRepository = new LoteRepositoryImpl();
        this.promocionRepository = new PromocionRepositoryImpl();
    }

    public ResultadoVenta procesarVenta(VentaDTO ventaDTO) {

        try {

            if (ventaDTO.getItems() == null || ventaDTO.getItems().isEmpty()) {
                return new ResultadoVenta(false, "No hay productos en la venta");
            }

            // Validación inicial: Usando el stock total del producto
            ResultadoVenta validacion = validarStockTotalDisponible(ventaDTO);
            if (!validacion.isExitoso()) {
                return validacion;
            }

            double subtotal = 0;
            double descuentoTotal = 0;

            List<DetalleVenta> detallesVentaFinales = new ArrayList<>();
            List<LoteConsumidoDTO> lotesConsumidos = new ArrayList<>();

            // 2. Procesar cada ItemVentaDTO y aplicar lógica FEFO y de precios
            for (ItemVentaDTO item : ventaDTO.getItems()) {
                Producto producto = productoRepository.buscarPorID(item.getIdProducto());
                int cantidadPendiente = item.getCantidad();

                if (producto == null) {
                    return new ResultadoVenta(false, "Producto no encontrado (ID: " + item.getIdProducto() + ")");
                }

                IPrecio precioDecorator = new PrecioBase(producto, item.getCantidad());
                List<Promocion> promociones = promocionRepository.listarPorProductos(item.getIdProducto());

                if (!promociones.isEmpty()) {
                    precioDecorator = new PromocionDecorator(precioDecorator, promociones.get(0));
                }

                if (item.getDescuentoAplicado() > 0) {
                    precioDecorator = new DescuentoFijoDecorator(precioDecorator, item.getDescuentoAplicado());
                }

                // Cálculos de precios 
                double precioOriginalItem = producto.getPrecioVenta() * item.getCantidad();
                double precioFinalItem = precioDecorator.calcularPrecio();
                double descuentoTotalItem = precioOriginalItem - precioFinalItem;

                double descuentoUnitarioPromedio = descuentoTotalItem / item.getCantidad();

                subtotal += precioOriginalItem;
                descuentoTotal += descuentoTotalItem;

                List<Lote> lotesDisponibles = loteRepository.listarPorProducto(item.getIdProducto());

                for (Lote lote : lotesDisponibles) {
                    if (cantidadPendiente == 0) {
                        break; 
                    }

                    int stockEnLote = lote.getCantidadInicial();

                    if (stockEnLote > 0) {
                        int cantidadAConsumir = Math.min(cantidadPendiente, stockEnLote);

                        // 3. Crear DetalleVenta
                        DetalleVenta detalle = new DetalleVenta();
                        detalle.setIdLote(lote.getIdLote());
                        detalle.setCantidad(cantidadAConsumir);
                        detalle.setPrecioUnitario(producto.getPrecioVenta());
                        detalle.setDescuentoAplicado(descuentoUnitarioPromedio * cantidadAConsumir);

                        detallesVentaFinales.add(detalle);

                        // --- CORRECCIÓN AQUÍ --- 
                        // Pasamos explícitamente el ID del producto para evitar el error 'prodConsumido is null'
                        lotesConsumidos.add(new LoteConsumidoDTO(lote.getIdLote(), cantidadAConsumir, producto.getIdProducto()));

                        cantidadPendiente -= cantidadAConsumir;
                    }
                }

                if (cantidadPendiente > 0) {
                    return new ResultadoVenta(false, "Fallo crítico en inventario: El producto " + producto.getNombre() + " no tiene suficientes lotes activos para cubrir la demanda.");
                }
            }

            // --- REGISTRO DE VENTA Y DETALLE ---
            descuentoTotal += ventaDTO.getDescuentoGeneral();
            double total = subtotal - descuentoTotal;

            Venta venta = new Venta();
            venta.setIdCliente(ventaDTO.getIdCliente());
            venta.setIdUsuario(ventaDTO.getIdUsuario());
            venta.setSubtotal(subtotal);
            venta.setDescuento(descuentoTotal);
            venta.setTotal(total);

            int idVenta = ventaRepository.insertarYRetornarID(venta);
            if (idVenta == -1) {
                return new ResultadoVenta(false, "Error al registrar la venta principal en la base de datos.");
            }

            IDetalleVentaRepository detalleVentaRepo = new DetalleVentaRepositoryImpl();
            for (DetalleVenta detalle : detallesVentaFinales) {
                detalle.setIdVenta(idVenta);
                if (!detalleVentaRepo.insertar(detalle)) {
                    return new ResultadoVenta(false, "Error al registrar el detalle de la venta (Lote: " + detalle.getIdLote() + ").");
                }
            }

            // Actualizar Stock de Lotes y Producto
            for (LoteConsumidoDTO consumo : lotesConsumidos) {
                loteRepository.actualizarCantidadLote(consumo.getIdLote(), consumo.getCantidadConsumida());

                // Ahora consumo.getIdProducto() ya tendrá el valor correcto
                Producto prodConsumido = productoRepository.buscarPorID(consumo.getIdProducto());
                
                if (prodConsumido != null) {
                    productoRepository.actualizarStock(prodConsumido.getIdProducto(), -consumo.getCantidadConsumida());
                } else {
                    System.err.println("Advertencia: No se pudo actualizar el stock global para el producto ID " + consumo.getIdProducto());
                }
            }

            return new ResultadoVenta(true, "Venta registrada exitosamente", idVenta, total);
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir stack trace completo para depuración
            return new ResultadoVenta(false, "Error crítico al procesar la venta. Detalle: " + e.getMessage());
        }
    }

    private ResultadoVenta validarStockTotalDisponible(VentaDTO ventaDTO) {
        for (ItemVentaDTO item : ventaDTO.getItems()) {
            Producto producto = productoRepository.buscarPorID(item.getIdProducto());

            if (producto == null) {
                return new ResultadoVenta(false, "Producto no encontrado (ID: " + item.getIdProducto() + ")");
            }
            if (!producto.isActivo()) {
                return new ResultadoVenta(false, "El producto " + producto.getNombre() + " no está activo.");
            }
            if (producto.getStockActual() < item.getCantidad()) {
                return new ResultadoVenta(false,
                        "Stock insuficiente para el producto: " + producto.getNombre()
                        + ". Disponible: " + producto.getStockActual()
                        + ", Solicitado: " + item.getCantidad());
            }
        }
        return new ResultadoVenta(true, "Stock validado correctamente");
    }

    // CLASE INTERNA CORREGIDA
    private class LoteConsumidoDTO {

        private int idLote;
        private int idProducto;
        private int cantidadConsumida;

        // CORRECCIÓN: Se agrega idProducto al constructor
        public LoteConsumidoDTO(int idLote, int cantidadConsumida, int idProducto) {
            this.idLote = idLote;
            this.cantidadConsumida = cantidadConsumida;
            this.idProducto = idProducto;
        }

        public int getIdLote() {
            return idLote;
        }

        public int getCantidadConsumida() {
            return cantidadConsumida;
        }

        public int getIdProducto() {
            return idProducto;
        }
    }
}