/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.modelo.entidades;

/**
 *
 * @author Frank
 */
public class DetalleVenta {

    private int idDetalle;
    private int idVenta;
    private int idLote;
    private int cantidad;
    private double precioUnitario;
    private double descuentoAplicado;

    public DetalleVenta() {
    }

    public DetalleVenta(int idDetalle, int idVenta, int idLote, int cantidad,
            double precioUnitario, double descuentoAplicado) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idLote = idLote;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuentoAplicado = descuentoAplicado;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(double descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    public double getSubtotal() {
        return (precioUnitario * cantidad) - descuentoAplicado;
    }

}
