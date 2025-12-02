/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.observer;

import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import java.util.List;

/**
 *
 * @author Frank
 */
public class AlertaBajoStockObserver implements IObserver {

    private String nombre;

    public AlertaBajoStockObserver(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String evento, Object datos) {
        switch (evento) {
            case "STOCK_BAJO":
                List<Producto> productos = (List<Producto>) datos;
                mostrarAlertaStockBajo(productos);
                break;

            case "STOCK_CRITICO":
                Producto producto = (Producto) datos;
                mostrarAlertaStockCritico(producto);
                break;

            case "PRODUCTO_BAJO_STOCK":
                Producto prod = (Producto) datos;
                mostrarAlertaProductoIndividual(prod);
                break;

            case "RESUMEN_STOCK_CRITICO":
                int cantidad = (int) datos;
                mostrarResumenStockCritico(cantidad);
                break;
        }
    }

    private void mostrarAlertaStockBajo(List<Producto> productos) {
        System.out.println("---------------------------------------");
        System.out.println("" + nombre + " ALERTA: PRODUCTOS CON BAJO STOCK");
        System.out.println("---------------------------------------");
        System.out.println("Total de productos afectados: " + productos.size());
        System.out.println();

        for (Producto producto : productos) {
            System.out.println("- " + producto.getNombre());
            System.out.println("  Stock actual: " + producto.getStockActual());
            System.out.println("  Stock minimo: " + producto.getStockMinimo());
            System.out.println("  Requiere: " + (producto.getStockMinimo() - producto.getStockActual()) + " unidades");
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }

    private void mostrarAlertaStockCritico(Producto producto) {
        System.out.println(" ALERTA CRITICA ");
        System.out.println("Producto SIN STOCK: " + producto.getNombre());
        System.out.println("ID: " + producto.getIdProducto());
        System.out.println("Accion requerida: Reabastecer URGENTEMENTE");
        System.out.println();
    }

    private void mostrarAlertaProductoIndividual(Producto producto) {
        System.out.println("" + nombre + " Alerta: El producto '" + producto.getNombre()
                + "' ha alcanzado stock bajo (" + producto.getStockActual() + " unidades)");
    }

    private void mostrarResumenStockCritico(int cantidad) {
        System.out.println("---------------------------------------");
        System.out.println("RESUMEN: " + cantidad + " productos sin stock");
        System.out.println("---------------------------------------");
    }

}
