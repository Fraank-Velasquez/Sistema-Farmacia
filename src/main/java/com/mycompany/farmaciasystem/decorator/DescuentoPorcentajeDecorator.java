/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.decorator;

/**
 *
 * @author Frank
 */
public class DescuentoPorcentajeDecorator extends PrecioDecorator {

    private double porcentajeImpuesto;

    public DescuentoPorcentajeDecorator(IPrecio precioBase, double porcentajeImpuesto) {
        super(precioBase);
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    @Override
    public double calcularPrecio() {
        double precioBase = this.precioBase.calcularPrecio();
        double impuesto = precioBase * (porcentajeImpuesto / 100.0);
        return precioBase + impuesto;
    }

    @Override
    public String obtenerDescripcion() {
        return precioBase.obtenerDescripcion() + " [+IGV " + porcentajeImpuesto + "%]";
    }

    public double getMontoImpuesto() {
        return precioBase.calcularPrecio() * (porcentajeImpuesto / 100.0);
    }
}
