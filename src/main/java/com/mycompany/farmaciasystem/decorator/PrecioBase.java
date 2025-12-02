/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.decorator;

import com.mycompany.farmaciasystem.modelo.entidades.Producto;

/**
 *
 * @author Frank
 */
public class PrecioBase implements IPrecio {

    private Producto producto;
    private int cantidad;

    public PrecioBase(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public double calcularPrecio() {
        return producto.getPrecioVenta() * cantidad;
    }

    @Override
    public String obtenerDescripcion() {
        return producto.getNombre() + "(x" + cantidad + ")";
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

}
