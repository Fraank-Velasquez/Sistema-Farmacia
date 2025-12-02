/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.factory;

import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.modelo.entidades.Venta;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Frank
 */
public class ReportesFactory {

    public enum TipoReporte {
        VENTAS,
        INVENTARIO,
        LOTES_VENCIDOS,
        BAJO_STOCK
    }

    public static IReporte crearReporte(TipoReporte tipo, Object... parametros) {
        switch (tipo) {
            case VENTAS -> {
                List<Venta> ventas = (List<Venta>) parametros[0];
                LocalDate fechaInicio = (LocalDate) parametros[1];
                LocalDate fechaFin = (LocalDate) parametros[2];
                return new ReporteVentas(ventas, fechaInicio, fechaFin);
            }

            case INVENTARIO -> {
                List<Producto> productosInventario = (List<Producto>) parametros[0];
                return new ReporteInventario(productosInventario);
            }

            case LOTES_VENCIDOS -> {
                List<Lote> lotes = (List<Lote>) parametros[0];
                return new ReporteLotesVencidos(lotes);
            }

            case BAJO_STOCK -> {
                List<Producto> productosBajoStock = (List<Producto>) parametros[0];
                return new ReporteBajoStock(productosBajoStock);
            }

            default ->
                throw new IllegalArgumentException("Tipo de reporte no soportado: " + tipo);
        }
    }
}
