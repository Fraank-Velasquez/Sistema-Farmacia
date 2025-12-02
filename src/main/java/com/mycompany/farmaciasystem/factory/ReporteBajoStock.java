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
public class ReporteBajoStock implements IReporte {

    private List<Producto> productos;
    private byte[] pdfBytes;

    public ReporteBajoStock(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public void generarReporte() {
        StringBuilder contenido = new StringBuilder();
        contenido.append("----------------------------------------\n");
        contenido.append("   REPORTE DE PRODUCTOS BAJO STOCK\n");
        contenido.append("----------------------------------------\n");
        contenido.append("Fecha: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n");
        contenido.append("Total de productos: ").append(productos.size()).append("\n");
        contenido.append("----------------------------------------\n\n");

        for (Producto producto : productos) {
            contenido.append("ID: ").append(producto.getIdProducto()).append("\n");
            contenido.append("Nombre: ").append(producto.getNombre()).append("\n");
            contenido.append("Stock Actual: ").append(producto.getStockActual()).append("\n");
            contenido.append("Stock Minimo: ").append(producto.getStockMinimo()).append("\n");

            int diferencia = producto.getStockMinimo() - producto.getStockActual();
            contenido.append("Requiere reposicion: ").append(diferencia).append(" unidades\n");
            contenido.append("*** ALERTA: STOCK BAJO ***\n");
            contenido.append("----------------------------------------\n");
        }

        contenido.append("\n----------------------------------------\n");
        contenido.append("TOTAL PRODUCTOS BAJO STOCK: ").append(productos.size()).append("\n");
        contenido.append("----------------------------------------\n");

        this.pdfBytes = contenido.toString().getBytes();

        System.out.println("Reporte de Productos Bajo Stock generado exitosamente");
    }

    @Override
    public byte[] obtenerBytes() {
        return pdfBytes;
    }

    @Override
    public String obtenerNombreArchivo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return "Reporte_Bajo_Stock_" + LocalDateTime.now().format(formatter) + ".txt";
    }

    @Override
    public String obenerTipoReporte() {
        return "Reporte de Productos Bajo Stock";
    }

}
