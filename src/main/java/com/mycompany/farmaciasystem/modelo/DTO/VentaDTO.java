/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.modelo.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank
 */
public class VentaDTO {

    private int idCliente;
    private int idUsuario;
    private List<ItemVentaDTO> items;
    private double descuentoGeneral;

    public VentaDTO() {
        this.items = new ArrayList<>();
    }

    public VentaDTO(int idCliente, int idUsuario) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
    }

    public VentaDTO(int idCliente, int idUsuario, List<ItemVentaDTO> items, double descuentoGeneral) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.items = items;
        this.descuentoGeneral = descuentoGeneral;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<ItemVentaDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemVentaDTO> items) {
        this.items = items;
    }

    public double getDescuentoGeneral() {
        return descuentoGeneral;
    }

    public void setDescuentoGeneral(double descuentoGeneral) {
        this.descuentoGeneral = descuentoGeneral;
    }

}
