/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.decorator;

/**
 *
 * @author Frank
 */
public class PrecioDecorator implements IPrecio {

    protected IPrecio precioBase;

    public PrecioDecorator(IPrecio precioBase) {
        this.precioBase = precioBase;
    }

    @Override
    public double calcularPrecio() {
        return precioBase.calcularPrecio();
    }

    @Override
    public String obtenerDescripcion() {

        return precioBase.obtenerDescripcion();

    }

}
