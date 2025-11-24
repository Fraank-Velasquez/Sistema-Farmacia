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
public class Lote {

    private int idLote;
    private int idProducto;
    private int idEmpresaProveedor;
    private String numeroLote;
    private LocalDate fechaFabricacion;
    private LocalDate fechaVencimiento;
    private double precioCompra;
    private int cantidadInicial;
    private LocalDateTime fechaIngreso;
    private boolean activo;

    public Lote() {
    }

    public Lote(int idLote, int idProducto, int idEmpresaProveedor, String numeroLote, LocalDate fechaFabricacion, LocalDate fechaVencimiento, double precioCompra, int cantidadInicial, LocalDateTime fechaIngreso, boolean activo) {
        this.idLote = idLote;
        this.idProducto = idProducto;
        this.idEmpresaProveedor = idEmpresaProveedor;
        this.numeroLote = numeroLote;
        this.fechaFabricacion = fechaFabricacion;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.cantidadInicial = cantidadInicial;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdEmpresaProveedor() {
        return idEmpresaProveedor;
    }

    public void setIdEmpresaProveedor(int idEmpresaProveedor) {
        this.idEmpresaProveedor = idEmpresaProveedor;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(int cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    

}
