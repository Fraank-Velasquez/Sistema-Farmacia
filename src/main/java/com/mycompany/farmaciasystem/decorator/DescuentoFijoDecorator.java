/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.decorator;

/**
 *
 * @author Frank
 */
public class DescuentoFijoDecorator extends PrecioDecorator {

    private double montoDescuento;

    public DescuentoFijoDecorator(IPrecio precioBase, double montoDescuento) {
        super(precioBase);
        this.montoDescuento = montoDescuento;
    }

    @Override
    public double calcularPrecio() {
        double precioOriginal = precioBase.calcularPrecio();
        double precioFinal = precioOriginal - montoDescuento;
        return precioFinal > 0 ? precioFinal : 0;
    }

    @Override
    public String obtenerDescripcion() {
        return precioBase.obtenerDescripcion() + " Descuento S/. " + montoDescuento;
    }

    public double getMontoDescuento() {
        return montoDescuento;
    }

}
