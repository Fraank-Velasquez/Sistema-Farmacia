package com.mycompany.farmaciasystem.modelo.entidades;

public class DetalleCompra {

    private int idDetalleCompra;
    private int idCompra;
    private int idLote;
    private int cantidad;

    public DetalleCompra() {
    }

    public DetalleCompra(int idCompra, int idLote, int cantidad) {
        this.idCompra = idCompra;
        this.idLote = idLote;
        this.cantidad = cantidad;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
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
}
