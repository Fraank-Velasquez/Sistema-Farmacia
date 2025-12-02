/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.decorator;

import com.mycompany.farmaciasystem.modelo.entidades.Promocion;

/**
 *
 * @author Frank
 */
public class PromocionDecorator extends PrecioDecorator {

    private Promocion promocion;

    public PromocionDecorator(IPrecio precioBase, Promocion promocion) {
        super(precioBase);
        this.promocion = promocion;
    }

    @Override
    public double calcularPrecio() {
        double precioOriginal = precioBase.calcularPrecio();

        if (promocion.getTipoDescuento().equalsIgnoreCase("porcentaje")) {
            double descuento = precioOriginal * (promocion.getValorDescuento() / 100.0);
            return precioOriginal - descuento;
        } else if (promocion.getTipoDescuento().equalsIgnoreCase("fijo")) {
            double precioFinal = precioOriginal - promocion.getValorDescuento();
            return precioFinal > 0 ? precioFinal : 0;
        }

        return precioOriginal;
    }

    @Override
    public String obtenerDescripcion() {
        return precioBase.obtenerDescripcion() + " Promoción: " + promocion.getNombre();
    }

    public double getMontoDescuento() {
        double precioOriginal = precioBase.calcularPrecio();

        if (promocion.getTipoDescuento().equalsIgnoreCase("porcentaje")) {
            return precioOriginal * (promocion.getValorDescuento() / 100.0);
        } else if (promocion.getTipoDescuento().equalsIgnoreCase("fijo")) {
            return promocion.getValorDescuento();
        }

        return 0;
    }
}
