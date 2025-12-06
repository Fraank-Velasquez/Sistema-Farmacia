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
        // Inicializar los repositorios para acceder a datos
        this.ventaRepository = new VentaRepositoryImpl();
        this.productoRepository = new ProductoRepositoryImpl();
        this.loteRepository = new LoteRepositoryImpl();
        this.promocionRepository = new PromocionRepositoryImpl();
    }

    public ResultadoVenta procesarVenta(VentaDTO ventaDTO) {

        try {
            if (ventaDTO.getItems() == null || ventaDTO.getItems().isEmpty()) {
                return new ResultadoVenta(false, "El carrito está vacío");
            }

            // validar si hay stock general suficiente
            ResultadoVenta validacion = validarStockTotalDisponible(ventaDTO);
            if (!validacion.isExitoso()) {
                return validacion;
            }

            double subtotal = 0;
            double descuentoTotal = 0;

            List<DetalleVenta> detallesVentaFinales = new ArrayList<>();
            List<LoteConsumidoDTO> lotesConsumidos = new ArrayList<>();

            // Recorrer cada producto del carrito
            for (ItemVentaDTO item : ventaDTO.getItems()) {
                Producto producto = productoRepository.buscarPorID(item.getIdProducto());
                int cantidadPendiente = item.getCantidad();

                if (producto == null) {
                    return new ResultadoVenta(false, "No se encontró el producto ID: " + item.getIdProducto());
                }

                // Calcular el precio base y buscar si tiene promociones
                IPrecio precioDecorator = new PrecioBase(producto, item.getCantidad());
                List<Promocion> promociones = promocionRepository.listarPorProductos(item.getIdProducto());

                if (!promociones.isEmpty()) {
                    precioDecorator = new PromocionDecorator(precioDecorator, promociones.get(0));
                }

                // Aplicars descuentos  si los hubiera
                if (item.getDescuentoAplicado() > 0) {
                    precioDecorator = new DescuentoFijoDecorator(precioDecorator, item.getDescuentoAplicado());
                }

                // Calcular montos finales del item
                double precioOriginalItem = producto.getPrecioVenta() * item.getCantidad();
                double precioFinalItem = precioDecorator.calcularPrecio();
                double descuentoTotalItem = precioOriginalItem - precioFinalItem;
                double descuentoUnitarioPromedio = descuentoTotalItem / item.getCantidad();

                subtotal += precioOriginalItem;
                descuentoTotal += descuentoTotalItem;

                // Buscar los lotes (ordenados por fecha de vencimiento)
                List<Lote> lotesDisponibles = loteRepository.listarPorProducto(item.getIdProducto());

                for (Lote lote : lotesDisponibles) {
                    if (cantidadPendiente == 0) {
                        break;
                    }

                    int stockEnLote = lote.getCantidadInicial();

                    // Si el lote tiene stock, descontar de aquí
                    if (stockEnLote > 0) {
                        int cantidadAConsumir = Math.min(cantidadPendiente, stockEnLote);

                        // Preparar el detalle para guardar
                        DetalleVenta detalle = new DetalleVenta();
                        detalle.setIdLote(lote.getIdLote());
                        detalle.setCantidad(cantidadAConsumir);
                        detalle.setPrecioUnitario(producto.getPrecioVenta());
                        detalle.setDescuentoAplicado(descuentoUnitarioPromedio * cantidadAConsumir);

                        detallesVentaFinales.add(detalle);

                        // Guardar qué lote se usó para actualizarlo luego
                        lotesConsumidos.add(new LoteConsumidoDTO(lote.getIdLote(), cantidadAConsumir, producto.getIdProducto()));

                        cantidadPendiente -= cantidadAConsumir;
                    }
                }

                if (cantidadPendiente > 0) {
                    return new ResultadoVenta(false, "Error: Stock insuficiente en lotes para el producto " + producto.getNombre());
                }
            }

            // Aplicar descuento global a la venta
            descuentoTotal += ventaDTO.getDescuentoGeneral();
            double total = subtotal - descuentoTotal;

            // Guardar la cabecera de la venta
            Venta venta = new Venta();
            venta.setIdCliente(ventaDTO.getIdCliente());
            venta.setIdUsuario(ventaDTO.getIdUsuario());
            venta.setSubtotal(subtotal);
            venta.setDescuento(descuentoTotal);
            venta.setTotal(total);

            int idVenta = ventaRepository.insertarYRetornarID(venta);
            if (idVenta == -1) {
                return new ResultadoVenta(false, "Error al guardar la venta en base de datos.");
            }

            // Guardar el detalle de cada producto vendido
            IDetalleVentaRepository detalleVentaRepo = new DetalleVentaRepositoryImpl();
            for (DetalleVenta detalle : detallesVentaFinales) {
                detalle.setIdVenta(idVenta);
                if (!detalleVentaRepo.insertar(detalle)) {
                    return new ResultadoVenta(false, "Error al guardar detalle del lote: " + detalle.getIdLote());
                }
            }

            // Actualizar el stock real en la base de datos
            for (LoteConsumidoDTO consumo : lotesConsumidos) {
                // Restamos del lote
                loteRepository.actualizarCantidadLote(consumo.getIdLote(), consumo.getCantidadConsumida());

                // Restar del producto general
                Producto prodConsumido = productoRepository.buscarPorID(consumo.getIdProducto());
                if (prodConsumido != null) {
                    productoRepository.actualizarStock(prodConsumido.getIdProducto(), -consumo.getCantidadConsumida());
                }
            }

            return new ResultadoVenta(true, "Venta realizada correctamente", idVenta, total);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultadoVenta(false, "Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    // Validar que exista el producto y tenga stock suficiente antes de procesar
    private ResultadoVenta validarStockTotalDisponible(VentaDTO ventaDTO) {
        for (ItemVentaDTO item : ventaDTO.getItems()) {
            Producto producto = productoRepository.buscarPorID(item.getIdProducto());

            if (producto == null) {
                return new ResultadoVenta(false, "No existe el producto ID: " + item.getIdProducto());
            }
            if (!producto.isActivo()) {
                return new ResultadoVenta(false, "El producto " + producto.getNombre() + " está inactivo.");
            }
            if (producto.getStockActual() < item.getCantidad()) {
                return new ResultadoVenta(false,
                        "Stock insuficiente: " + producto.getNombre()
                        + " (Disponible: " + producto.getStockActual() + ")");
            }
        }
        return new ResultadoVenta(true, "Stock OK");
    }

    // Clase para manejar el consumo de stock
    private class LoteConsumidoDTO {

        private int idLote;
        private int idProducto;
        private int cantidadConsumida;

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
