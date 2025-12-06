/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.modelo.DTO;

/**
 * Clase para transferir los datos de un producto individual en el carrito de
 * ventas.
 *
 * @author Frank
 */
public class ItemVentaDTO {

    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double descuentoAplicado;

    public ItemVentaDTO() {
    }

    public ItemVentaDTO(int idProducto, int cantidad, double precioUnitario, double descuentoAplicado) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuentoAplicado = descuentoAplicado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

}
