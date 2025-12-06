/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.command;

import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.repository.Implementaciones.ProductoRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IProductoRepository;

/**
 * Comando que se encarga de actualizar la información de un producto existente.
 * @author Frank
 */
public class ActualizarProductoCommand implements ICommand {

    private Producto productoNuevo;
    private Producto productoAnterior;
    private IProductoRepository productoRepository;

    public ActualizarProductoCommand(Producto productoNuevo) {
        this.productoNuevo = productoNuevo;
        this.productoRepository = new ProductoRepositoryImpl();

        // Guardamos cómo estaba el producto antes para poder deshacer los cambios si queremos
        this.productoAnterior = productoRepository.buscarPorID(productoNuevo.getIdProducto());
    }

    @Override
    public boolean ejecutar() {
        try {
            // Mandamos los nuevos datos a la base de datos
            boolean resultado = productoRepository.actualizar(productoNuevo.getIdProducto(), productoNuevo);

            if (resultado) {
                System.out.println("Producto actualizado correctamente: " + productoNuevo.getIdProducto());
                return true;
            } else {
                System.err.println("No se pudo actualizar el producto.");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Pasó algo raro al actualizar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deshacer() {
        // Verificamos que tengamos el respaldo del producto viejo
        if (productoAnterior == null) {
            System.err.println("No se puede deshacer porque no encontré el producto original.");
            return false;
        }

        try {
            // Restauramos los datos viejos en la base de datos
            boolean resultado = productoRepository.actualizar(productoAnterior.getIdProducto(), productoAnterior);

            if (resultado) {
                System.out.println("Cambios revertidos para el producto ID: " + productoAnterior.getIdProducto());
                return true;
            } else {
                System.err.println("Error al intentar revertir los cambios.");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error al deshacer la actualización: " + e.getMessage());
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