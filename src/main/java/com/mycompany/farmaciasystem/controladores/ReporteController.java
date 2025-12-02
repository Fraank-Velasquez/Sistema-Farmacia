/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.controladores;

import com.mycompany.farmaciasystem.factory.IReporte;
import com.mycompany.farmaciasystem.factory.ReportesFactory;
import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.modelo.entidades.Venta;
import com.mycompany.farmaciasystem.servicios.LoteService;
import com.mycompany.farmaciasystem.servicios.ProductoService;
import com.mycompany.farmaciasystem.servicios.VentaService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Frank
 */
public class ReporteController {

    private VentaService ventaService;
    private ProductoService productoService;
    private LoteService loteService;
    private String rutaReportes;

    public ReporteController() {
        this.ventaService = new VentaService();
        this.productoService = new ProductoService();
        this.loteService = new LoteService();
        this.rutaReportes = "C:/reportes_farmacia/";

        crearDirectorioReportes();
    }

    private void crearDirectorioReportes() {
        File directorio = new File(rutaReportes);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }

    public String generarReporteVentas(LocalDate fechaInicio, LocalDate fechaFin) throws IOException {
        List<Venta> ventas = ventaService.listarPorFecha(fechaInicio, fechaFin);

        IReporte reporte = ReportesFactory.crearReporte(
                ReportesFactory.TipoReporte.VENTAS,
                ventas,
                fechaInicio,
                fechaFin
        );

        reporte.generarReporte();

        String rutaArchivo = rutaReportes + reporte.obtenerNombreArchivo();
        Files.write(Paths.get(rutaArchivo), reporte.obtenerBytes());

        return rutaArchivo;
    }

    public String generarReporteInventario() throws IOException {
        List<Producto> productos = productoService.listarTodos();

        IReporte reporte = ReportesFactory.crearReporte(
                ReportesFactory.TipoReporte.INVENTARIO,
                productos
        );

        reporte.generarReporte();

        String rutaArchivo = rutaReportes + reporte.obtenerNombreArchivo();
        Files.write(Paths.get(rutaArchivo), reporte.obtenerBytes());

        return rutaArchivo;
    }

    public String generarReporteLotesVencidos() throws IOException {
        List<Lote> lotesVencidos = loteService.ListarVencidos();

        IReporte reporte = ReportesFactory.crearReporte(
                ReportesFactory.TipoReporte.LOTES_VENCIDOS,
                lotesVencidos
        );

        reporte.generarReporte();

        String rutaArchivo = rutaReportes + reporte.obtenerNombreArchivo();
        Files.write(Paths.get(rutaArchivo), reporte.obtenerBytes());

        return rutaArchivo;
    }

    public String generarReporteBajoStock() throws IOException {
        List<Producto> productosBajoStock = productoService.listarProductosBajoStock();

        IReporte reporte = ReportesFactory.crearReporte(
                ReportesFactory.TipoReporte.BAJO_STOCK,
                productosBajoStock
        );

        reporte.generarReporte();

        String rutaArchivo = rutaReportes + reporte.obtenerNombreArchivo();
        Files.write(Paths.get(rutaArchivo), reporte.obtenerBytes());

        return rutaArchivo;
    }

    public String getRutaReportes() {
        return rutaReportes;
    }

}
