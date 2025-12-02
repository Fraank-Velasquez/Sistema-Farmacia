/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.observer;

import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import java.util.List;

/**
 *
 * @author Frank
 */
public class AlertaLoteVencidoObserver implements IObserver {

    private String nombre;

    public AlertaLoteVencidoObserver(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String evento, Object datos) {
        switch (evento) {
            case "LOTES_PROXIMOS_VENCER" -> {
                List<Lote> lotesProximos = (List<Lote>) datos;
                mostrarAlertaLotesProximos(lotesProximos);
            }

            case "LOTES_VENCIDOS" -> {
                List<Lote> lotesVencidos = (List<Lote>) datos;
                mostrarAlertaLotesVencidos(lotesVencidos);
            }
        }
    }

    private void mostrarAlertaLotesProximos(List<Lote> lotes) {
        System.out.println("---------------------------------------");
        System.out.println(nombre + " ALERTA: LOTES PROXIMOS A VENCER");
        System.out.println("---------------------------------------");
        System.out.println("Total de lotes: " + lotes.size());
        System.out.println();

        for (Lote lote : lotes) {
            long diasRestantes = lote.getFechaVencimiento().toEpochDay() - java.time.LocalDate.now().toEpochDay();

            System.out.println("- Lote: " + lote.getNumeroLote());
            System.out.println("  Producto ID: " + lote.getIdProducto());
            System.out.println("  Fecha vencimiento: " + lote.getFechaVencimiento());
            System.out.println("  Dias restantes: " + diasRestantes);
            System.out.println("  Cantidad: " + lote.getCantidadInicial() + " unidades");

            if (diasRestantes <= 7) {
                System.out.println("  >>> URGENTE: Vence en menos de una semana <<<");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }

    private void mostrarAlertaLotesVencidos(List<Lote> lotes) {
        System.out.println("---------------------------------------");
        System.out.println(nombre + "] ALERTA CRITICA: LOTES VENCIDOS");
        System.out.println("---------------------------------------");
        System.out.println("Total de lotes vencidos: " + lotes.size());
        System.out.println();

        for (Lote lote : lotes) {
            long diasVencido = java.time.LocalDate.now().toEpochDay() - lote.getFechaVencimiento().toEpochDay();

            System.out.println("- Lote: " + lote.getNumeroLote());
            System.out.println("  Producto ID: " + lote.getIdProducto());
            System.out.println("  Fecha vencimiento: " + lote.getFechaVencimiento());
            System.out.println("  Vencido hace: " + diasVencido + " dias");
            System.out.println("  Cantidad afectada: " + lote.getCantidadInicial() + " unidades");
            System.out.println("  >>> ACCION REQUERIDA: Retirar del inventario <<<");
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }

}
