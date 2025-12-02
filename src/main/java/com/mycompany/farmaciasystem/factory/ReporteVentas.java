/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.factory;

import com.mycompany.farmaciasystem.modelo.entidades.Venta;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Frank
 */
public class ReporteVentas implements IReporte {

    private List<Venta> ventas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private byte[] pdfBytes;

    public ReporteVentas(List<Venta> ventas, LocalDate fechaInicio, LocalDate fechaFin) {
        this.ventas = ventas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public void generarReporte() {
        StringBuilder contenido = new StringBuilder();
        contenido.append("---------------------------------------\n");
        contenido.append("       REPORTE DE VENTAS\n");
        contenido.append("---------------------------------------\n");
        contenido.append("Periodo: ").append(fechaInicio).append(" a ").append(fechaFin).append("\n");
        contenido.append("Total de ventas: ").append(ventas.size()).append("\n");
        contenido.append("---------------------------------------\n\n");

        double totalGeneral = 0;

        for (Venta venta : ventas) {

            contenido.append("ID de venta: ").append(venta.getIdVenta()).append("\n");
            contenido.append("Fecha: ").append(venta.getFechaVenta()).append("\n");
            contenido.append("ID de cliente: ").append(venta.getIdCliente()).append("\n");
            contenido.append("Total: S/. ").append(String.format("%.2f", venta.getTotal())).append("\n");
            contenido.append("---------------------------------------\n");
            totalGeneral += venta.getTotal();

        }

        contenido.append("\n---------------------------------------\n");
        contenido.append("TOTAL GENERAL: S/. ").append(String.format("%.2f", totalGeneral)).append("\n");
        contenido.append("---------------------------------------\n");

        this.pdfBytes = contenido.toString().getBytes();

        System.out.println("Reporte de Ventas generado exitosamente");

    }

    @Override
    public byte[] obtenerBytes() {
        return pdfBytes;
    }

    @Override
    public String obtenerNombreArchivo() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return "Reporte_Ventas_" + fechaInicio.format(formatter) + "_" + fechaFin.format(formatter) + ".txt";

    }

    @Override
    public String obenerTipoReporte() {
        return "Reporte de Ventas";
    }

}
