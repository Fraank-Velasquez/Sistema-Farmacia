package com.mycompany.farmaciasystem.factory;

import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Frank
 */
public class ReporteLotesVencidos implements IReporte {

    private List<Lote> lotes;
    private byte[] pdfBytes;

    public ReporteLotesVencidos(List<Lote> lotes) {
        this.lotes = lotes;
    }

    @Override
    public void generarReporte() {
        StringBuilder contenido = new StringBuilder();
        contenido.append("----------------------------------------\n");
        contenido.append("   REPORTE DE LOTES VENCIDOS\n");
        contenido.append("----------------------------------------\n");
        contenido.append("Fecha: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n");
        contenido.append("Total de lotes vencidos: ").append(lotes.size()).append("\n");
        contenido.append("----------------------------------------\n\n");

        for (Lote lote : lotes) {
            contenido.append("ID de lote: ").append(lote.getIdLote()).append("\n");
            contenido.append("Numero de Lote: ").append(lote.getNumeroLote()).append("\n");
            contenido.append("Producto ID: ").append(lote.getIdProducto()).append("\n");
            contenido.append("Fecha Vencimiento: ").append(lote.getFechaVencimiento()).append("\n");
            contenido.append("Cantidad Inicial: ").append(lote.getCantidadInicial()).append("\n");
            contenido.append("*** VENCIDO ***\n");
            contenido.append("----------------------------------------\n");
        }

        contenido.append("\n----------------------------------------\n");
        contenido.append("TOTAL LOTES VENCIDOS: ").append(lotes.size()).append("\n");
        contenido.append("----------------------------------------\n");

        this.pdfBytes = contenido.toString().getBytes();

        System.out.println("Reporte de Lotes Vencidos generado exitosamente");
    }

    @Override
    public byte[] obtenerBytes() {
        return pdfBytes;
    }

    @Override
    public String obtenerNombreArchivo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return "Reporte_Lotes_Vencidos_" + LocalDateTime.now().format(formatter) + ".txt";
    }

    @Override
    public String obenerTipoReporte() {
        return "Reporte de Lotes Vencidos";
    }

}
