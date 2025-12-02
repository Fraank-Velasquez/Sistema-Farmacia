package com.mycompany.farmaciasystem.observer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Frank
 */
public class LogSistemaObserver implements IObserver {

    private String rutaArchivo;
    private DateTimeFormatter formatter;

    public LogSistemaObserver(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void actualizar(String evento, Object datos) {
        String timestamp = LocalDateTime.now().format(formatter);
        String mensaje = construirMensaje(evento, datos);
        registrarEnLog(timestamp, evento, mensaje);
    }

    private String construirMensaje(String evento, Object datos) {
        return switch (evento) {
            case "STOCK_BAJO" -> "Detectados productos con stock bajo";
            case "STOCK_CRITICO" -> "Producto sin stock detectado";
            case "LOTES_VENCIDOS" -> "Detectados lotes vencidos en inventario";
            case "LOTES_PROXIMOS_VENCER" -> "Detectados lotes proximos a vencer";
            case "PRODUCTO_BAJO_STOCK" -> "Producto alcanzo nivel de stock bajo";
            default -> "Evento: " + evento;
        };
    }

    private void registrarEnLog(String timestamp, String evento, String mensaje) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            writer.println("[" + timestamp + "] [" + evento + "] " + mensaje);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error al escribir en el log: " + e.getMessage());
        }
    }
}
