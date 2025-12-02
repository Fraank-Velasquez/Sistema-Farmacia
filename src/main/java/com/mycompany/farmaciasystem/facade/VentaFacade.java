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

    private IVentaRepository ventaRepository;
    private IProductoRepository productoRepository;
    private ILoteRepository loteRepository;
    private IPromocionRepository promocionRepository;

    public VentaFacade() {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.loteRepository = loteRepository;
        this.promocionRepository = promocionRepository;
    }

    public ResultadoVenta procesarVenta(VentaDTO ventaDTO) {

        try {

            //validar que haya productos
            if (ventaDTO.getItems() == null || ventaDTO.getItems().isEmpty()) {
                return new ResultadoVenta(false, "No hay productos en la venta");
            }

            //validar Stock disponible
            ResultadoVenta validacion = validarStockDisponible(ventaDTO);
            if (!validacion.isExitoso()) {
                return validacion;
            }

            //calcular total con promociones y descuentos
            double subtotal = 0;
            double descuentoTotal = 0;
            List<DetalleVentaConLote> detallesConLotes = new ArrayList<>();

            for (ItemVentaDTO item : ventaDTO.getItems()) {
                Producto producto = productoRepository.buscarPorID(item.getIdProducto());

                //Obtener lote mas antiguo disponible  
                Lote lote = loteRepository.obtenerLoteMasAntiguoDisponible(item.getIdProducto());
                if (lote == null) {

                    return new ResultadoVenta(false, "No hay lotes disponibles para el producto " + producto.getNombre());
                }

                // Decorator de  precio
                IPrecio precio = new PrecioBase(producto, item.getCantidad());

                // verificar si hay promociones activas
                List<Promocion> promociones = promocionRepository.listarPorProductos(item.getIdProducto());
                if (!promociones.isEmpty()) {
                    precio = new PromocionDecorator(precio, promociones.get(0));
                }

                //aplicar descuentos adicionales si existen
                if (item.getDescuentoAplicado() > 0) {
                    precio = new DescuentoFijoDecorator(precio, item.getDescuentoAplicado());
                }

                //calcular precios
                double precioOriginal = producto.getPrecioVenta() * item.getCantidad();
                double precioFinal = precio.calcularPrecio();
                double descuentoItem = precioOriginal - precioFinal;

                subtotal += precioOriginal;
                descuentoTotal += descuentoItem;

                // Guardar detalle con lote
                DetalleVentaConLote detalle = new DetalleVentaConLote();
                detalle.setIdLote(lote.getIdLote());
                detalle.setCantidad(item.getCantidad());
                detalle.setPrecioUnitario(producto.getPrecioVenta());
                detalle.setDescuentoAplicado(descuentoItem);
                detallesConLotes.add(detalle);

            }

            // Aplicar descuento general de la venta
            descuentoTotal += ventaDTO.getDescuentoGeneral();
            double total = subtotal - descuentoTotal;

            //registrar venta
            Venta venta = new Venta();
            venta.setIdCliente(ventaDTO.getIdCliente());
            venta.setIdUsuario(ventaDTO.getIdUsuario());
            venta.setSubtotal(subtotal);
            venta.setDescuento(descuentoTotal);
            venta.setTotal(total);

            int idVenta = ventaRepository.insertarYRetornarID(venta);
            if (idVenta == -1) {

                return new ResultadoVenta(false, "Error al registrar la venta");
            }

            //registrar detalle de venta
            
            IDetalleVentaRepository detalleVentaRepo = new DetalleVentaRepositoryImpl();
            for (DetalleVentaConLote detalle : detallesConLotes) {

                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setIdVenta(idVenta);
                detalleVenta.setIdLote(detalle.getIdLote());
                detalleVenta.setCantidad(detalle.getCantidad());
                detalleVenta.setPrecioUnitario(detalle.getPrecioUnitario());
                detalleVenta.setDescuentoAplicado(detalle.getDescuentoAplicado());

                detalleVentaRepo.insertar(detalleVenta);
            }

            //actualizar stock de productos
            actualizarStock(ventaDTO);

            return new ResultadoVenta(true, "venta registrada exitosamente", idVenta, total);

        } catch (Exception e) {
            e.toString();
            return new ResultadoVenta(false, "Error al procesar la venta");
        }

    }

    private ResultadoVenta validarStockDisponible(VentaDTO ventaDTO) {

        for (ItemVentaDTO item : ventaDTO.getItems()) {
            Producto producto = productoRepository.buscarPorID(item.getIdProducto());

            if (producto == null) {
                return new ResultadoVenta(true, "Producto no encontrado");
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

    private void actualizarStock(VentaDTO ventaDTO) {
        for (ItemVentaDTO item : ventaDTO.getItems()) {
            Producto producto = productoRepository.buscarPorID(item.getIdProducto());
            producto.setStockActual(producto.getStockActual() - item.getCantidad());
            productoRepository.actualizar(item.getIdProducto(), producto);
        }

    }

    private class DetalleVentaConLote {

        private int idLote;
        private int cantidad;
        private double precioUnitario;
        private double descuentoAplicado;

        public int getIdLote() {
            return idLote;
        }

        public void setIdLote(int idLote) {
            this.idLote = idLote;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(double precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public double getDescuentoAplicado() {
            return descuentoAplicado;
        }

        public void setDescuentoAplicado(double descuentoAplicado) {
            this.descuentoAplicado = descuentoAplicado;
        }
    }

}
