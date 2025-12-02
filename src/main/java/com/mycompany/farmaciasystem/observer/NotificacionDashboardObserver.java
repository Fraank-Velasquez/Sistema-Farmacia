/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.observer;

import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank
 */
public class NotificacionDashboardObserver implements IObserver {

     private List<String> notificaciones;
    private int contadorStockBajo;
    private int contadorStockCritico;
    private int contadorLotesVencidos;
    private int contadorLotesProximos;
    
    public NotificacionDashboardObserver() {
        this.notificaciones = new ArrayList<>();
        this.contadorStockBajo = 0;
        this.contadorStockCritico = 0;
        this.contadorLotesVencidos = 0;
        this.contadorLotesProximos = 0;
    }
    
    @Override
    public void actualizar(String evento, Object datos) {
        switch (evento) {
            case "STOCK_BAJO" -> {
                List<Producto> productosBajos = (List<Producto>) datos;
                contadorStockBajo = productosBajos.size();
                notificaciones.add(productosBajos.size() + " productos con stock bajo");
             }
                
            case "STOCK_CRITICO" -> {
                contadorStockCritico++;
                Producto prod = (Producto) datos;
                notificaciones.add("CRITICO: " + prod.getNombre() + " sin stock");
             }
                
            case "LOTES_VENCIDOS" -> {
                List<Lote> lotesVencidos = (List<Lote>) datos;
                contadorLotesVencidos = lotesVencidos.size();
                notificaciones.add(lotesVencidos.size() + " lotes vencidos");
             }
                
            case "LOTES_PROXIMOS_VENCER" -> {
                List<Lote> lotesProximos = (List<Lote>) datos;
                contadorLotesProximos = lotesProximos.size();
                notificaciones.add(lotesProximos.size() + " lotes proximos a vencer");
             }
                
            case "PRODUCTO_BAJO_STOCK" -> {
                Producto producto = (Producto) datos;
                notificaciones.add("Nuevo: " + producto.getNombre() + " bajo stock");
             }
        }
    }
    
    public List<String> obtenerNotificaciones() {
        return new ArrayList<>(notificaciones);
    }
    
    public void limpiarNotificaciones() {
        notificaciones.clear();
        contadorStockBajo = 0;
        contadorStockCritico = 0;
        contadorLotesVencidos = 0;
        contadorLotesProximos = 0;
    }
    
    public int getContadorStockBajo() {
        return contadorStockBajo;
    }
    
    public int getContadorStockCritico() {
        return contadorStockCritico;
    }
    
    public int getContadorLotesVencidos() {
        return contadorLotesVencidos;
    }
    
    public int getContadorLotesProximos() {
        return contadorLotesProximos;
    }
    
    public int getTotalAlertas() {
        return contadorStockBajo + contadorStockCritico + 
               contadorLotesVencidos + contadorLotesProximos;
    }
    
    public boolean hayAlertas() {
        return getTotalAlertas() > 0;
    }

}
