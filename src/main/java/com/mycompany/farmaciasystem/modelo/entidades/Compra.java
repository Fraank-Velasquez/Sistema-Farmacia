/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.modelo.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Frank
 */
public class Compra {

    private int idCompra;
    private int idProveedor;
    private int idUsuario;
    private String numeroFactura;
    private double subtotal;
    private double impuesto;
    private double total;
    private LocalDate fechaCompra;
    private String observaciones;
    private LocalDateTime fechaRegistro;

    public Compra() {
    }

    public Compra(int idCompra, int idProveedor, int idUsuario, String numeroFactura, double subtotal, double impuesto, double total, LocalDate fechaCompra, String observaciones, LocalDateTime fechaRegistro) {
        this.idCompra = idCompra;
        this.idProveedor = idProveedor;
        this.idUsuario = idUsuario;
        this.numeroFactura = numeroFactura;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
        this.fechaCompra = fechaCompra;
        this.observaciones = observaciones;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    

}
