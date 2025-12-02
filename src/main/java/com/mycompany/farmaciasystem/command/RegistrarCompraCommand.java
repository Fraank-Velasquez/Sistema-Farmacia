/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.command;

import com.mycompany.farmaciasystem.modelo.entidades.Compra;
import com.mycompany.farmaciasystem.repository.Implementaciones.CompraRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.ICompraRepository;

/**
 *
 * @author Frank
 */
public class RegistrarCompraCommand implements ICommand {

    private Compra compra;
    private ICompraRepository compraRepository;
    private int idCompraGenerada;

    public RegistrarCompraCommand(Compra compra) {
        this.compra = compra;
        this.compraRepository = new CompraRepositoryImpl();
        this.idCompraGenerada = -1;
    }

    @Override
    public boolean ejecutar() {
        try {
            int idGenerado = compraRepository.insertarYRetornarId(compra);

            if (idGenerado != -1) {
                idCompraGenerada = idGenerado;
                System.out.println("Command: Compra registrada exitosamente - ID: " + idCompraGenerada);
                return true;
            } else {
                System.err.println("Command: Error al registrar compra");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Command: Excepcion al ejecutar - " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deshacer() {
        if (idCompraGenerada == -1) {
            System.err.println("Command: No hay compra para deshacer");
            return false;
        }

        try {
            boolean eliminada = compraRepository.eliminar(idCompraGenerada);

            if (eliminada) {
                System.out.println("Command: Compra anulada exitosamente - ID: " + idCompraGenerada);
                return true;
            } else {
                System.err.println("Command: Error al anular compra");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Command: Excepcion al deshacer - " + e.getMessage());
            return false;
        }
    }

    @Override
    public String obtenerDescripcion() {
        return "Registrar compra a proveedor ID: " + compra.getIdProveedor()
                + " por S/. " + String.format("%.2f", compra.getTotal());
    }

    @Override
    public String obtenerTipo() {
        return "REGISTRAR_COMPRA";
    }

    public int getIdCompraGenerada() {
        return idCompraGenerada;
    }

}
