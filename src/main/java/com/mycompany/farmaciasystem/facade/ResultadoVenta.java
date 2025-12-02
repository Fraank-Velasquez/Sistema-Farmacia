/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.facade;

/**
 *
 * @author Frank
 */
public class ResultadoVenta {

    private boolean exitoso;
    private String mensaje;
    private int idVenta;
    private double totalVenta;

    public ResultadoVenta() {
    }

    public ResultadoVenta(boolean exitoso, String mensaje) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
    }

    public ResultadoVenta(boolean exitoso, String mensaje, int idVenta, double totalVenta) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
        this.idVenta = idVenta;
        this.totalVenta = totalVenta;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

}
