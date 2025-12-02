/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.command;

import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.repository.Implementaciones.ProductoRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IProductoRepository;

/**
 *
 * @author Frank
 */
public class ActualizarProductoCommand implements ICommand {

    private Producto productoNuevo;
    private Producto productoAnterior;
    private IProductoRepository productoRepository;

    public ActualizarProductoCommand(Producto productoNuevo) {
        this.productoNuevo = productoNuevo;
        this.productoRepository = new ProductoRepositoryImpl();

        // Guardar estado anterior
        this.productoAnterior = productoRepository.buscarPorID(productoNuevo.getIdProducto());
    }

    @Override
    public boolean ejecutar() {
        try {
            boolean resultado = productoRepository.actualizar(productoNuevo.getIdProducto(), productoNuevo);

            if (resultado) {
                System.out.println("Command: Producto actualizado - ID: " + productoNuevo.getIdProducto());
                return true;
            } else {
                System.err.println("Command: Error al actualizar producto");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Command: Excepcion al ejecutar - " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deshacer() {
        if (productoAnterior == null) {
            System.err.println("Command: No hay estado anterior para restaurar");
            return false;
        }

        try {
            boolean resultado = productoRepository.actualizar(productoAnterior.getIdProducto(), productoAnterior);

            if (resultado) {
                System.out.println("Command: Producto restaurado a estado anterior - ID: " + productoAnterior.getIdProducto());
                return true;
            } else {
                System.err.println("Command: Error al restaurar producto");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Command: Excepcion al deshacer - " + e.getMessage());
            return false;
        }
    }

    @Override
    public String obtenerDescripcion() {
        return "Actualizar producto: " + productoNuevo.getNombre()
                + " (ID: " + productoNuevo.getIdProducto() + ")";
    }

    @Override
    public String obtenerTipo() {
        return "ACTUALIZAR_PRODUCTO";
    }

}
