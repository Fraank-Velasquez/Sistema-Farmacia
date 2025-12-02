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
public class EliminarProductoCommand implements ICommand {

    private int idProducto;
    private Producto productoEliminado;
    private IProductoRepository productoRepository;

    public EliminarProductoCommand(int idProducto) {
        this.idProducto = idProducto;
        this.productoRepository = new ProductoRepositoryImpl();
    }

    @Override
    public boolean ejecutar() {
        try {
            // Guardar producto antes de eliminar
            productoEliminado = productoRepository.buscarPorID(idProducto);

            if (productoEliminado == null) {
                System.err.println("Command: Producto no encontrado - ID: " + idProducto);
                return false;
            }

            boolean resultado = productoRepository.eliminar(idProducto);

            if (resultado) {
                System.out.println("Command: Producto eliminado - ID: " + idProducto);
                return true;
            } else {
                System.err.println("Command: Error al eliminar producto");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Command: Excepcion al ejecutar - " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deshacer() {
        if (productoEliminado == null) {
            System.err.println("Command: No hay producto para restaurar");
            return false;
        }

        try {
            // Restaurar producto (cambiar activo a true)
            productoEliminado.setActivo(true);
            boolean resultado = productoRepository.actualizar(productoEliminado.getIdProducto(),productoEliminado);

            if (resultado) {
                System.out.println("Command: Producto restaurado - ID: " + productoEliminado.getIdProducto());
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
        return "Eliminar producto ID: " + idProducto
                + (productoEliminado != null ? " (" + productoEliminado.getNombre() + ")" : "");
    }

    @Override
    public String obtenerTipo() {
        return "ELIMINAR_PRODUCTO";
    }

}
