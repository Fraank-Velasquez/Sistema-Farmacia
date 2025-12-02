/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.factory;

import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Frank
 */
public class ReporteInventario implements IReporte {

    private List<Producto> productos;
    private byte[] pdfBytes;

    public ReporteInventario(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public void generarReporte() {

        StringBuilder contenido = new StringBuilder();
        contenido.append("----------------------------------------\n");
        contenido.append("     REPORTE DE INVENTARIO\n");
        contenido.append("----------------------------------------\n");
        contenido.append("Fecha: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n");
        contenido.append("Total de productos: ").append(productos.size()).append("\n");
        contenido.append("----------------------------------------\n\n");

        double valorTotalInventario = 0;
        int productosEnStock = 0;

        for (Producto producto : productos) {
            contenido.append("ID del producto: ").append(producto.getIdProducto()).append("\n");
            contenido.append("Nombre: ").append(producto.getNombre()).append("\n");
            contenido.append("Stock Actual: ").append(producto.getStockActual()).append("\n");
            contenido.append("Stock Minimo: ").append(producto.getStockMinimo()).append("\n");
            contenido.append("Precio Venta: S/. ").append(String.format("%.2f", producto.getPrecioVenta())).append("\n");

            if (producto.getStockActual() <= producto.getStockMinimo()) {
                contenido.append(" !!! ALERTA: STOCK BAJO \n");
            }

            contenido.append("----------------------------------------\n");

            valorTotalInventario += producto.getPrecioVenta() * producto.getStockActual();
            if (producto.getStockActual() > 0) {
                productosEnStock++;
            }
        }

        contenido.append("\n----------------------------------------\n");
        contenido.append("RESUMEN:\n");
        contenido.append("Productos en stock: ").append(productosEnStock).append("\n");
        contenido.append("Valor total inventario: S/. ").append(String.format("%.2f", valorTotalInventario)).append("\n");
        contenido.append("----------------------------------------\n");

        this.pdfBytes = contenido.toString().getBytes();

        System.out.println("Reporte de Inventario generado exitosamente");

    }

    @Override
    public byte[] obtenerBytes() {
        return pdfBytes;
    }

    @Override
    public String obtenerNombreArchivo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return "Reporte_Inventario_" + LocalDateTime.now().format(formatter) + ".txt";
    }

    @Override
    public String obenerTipoReporte() {
        return "Reporte de Inventario";
    }

}
