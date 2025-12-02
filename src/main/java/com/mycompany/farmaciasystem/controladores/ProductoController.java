/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.controladores;

import com.mycompany.farmaciasystem.command.ActualizarProductoCommand;
import com.mycompany.farmaciasystem.command.CommandInvoker;
import com.mycompany.farmaciasystem.command.EliminarProductoCommand;
import com.mycompany.farmaciasystem.command.ICommand;
import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.servicios.ProductoService;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Frank
 */
public class ProductoController {

    private ProductoService productoService;
    private CommandInvoker commandInvoker;

    public ProductoController() {
        this.productoService = new ProductoService();
        this.commandInvoker = new CommandInvoker();
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoService.listarTodos();
    }

    public Producto buscarProductoPorId(int id) {
        return productoService.buscarPorId(id);
    }

    public boolean guardarProducto(Producto producto) {
        return productoService.guardarProducto(producto);
    }

    public boolean actualizarProducto(Producto producto) {
        ICommand comando = new ActualizarProductoCommand(producto);
        return commandInvoker.ejecutarComando(comando);
    }

    public boolean eliminarProducto(int id) {
        ICommand comando = new EliminarProductoCommand(id);
        return commandInvoker.ejecutarComando(comando);
    }

    public boolean deshacerUltimaOperacion() {
        return commandInvoker.deshacerUltimoComando();
    }

    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoService.buscarPorNombre(nombre);
    }

    public List<Producto> obtenerProductosBajoStock() {
        return productoService.listarProductosBajoStock();
    }

    public void cargarProductosEnTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        List<Producto> productos = obtenerTodosLosProductos();

        for (Producto producto : productos) {
            Object[] fila = {
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecioVenta(),
                producto.getStockActual(),
                producto.getStockMinimo(),
                producto.isActivo() ? "Activo" : "Inactivo"
            };
            modeloTabla.addRow(fila);
        }
    }

    public void cargarProductosBajoStockEnTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        List<Producto> productos = obtenerProductosBajoStock();

        for (Producto producto : productos) {
            Object[] fila = {
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getStockActual(),
                producto.getStockMinimo(),
                "ALERTA"
            };
            modeloTabla.addRow(fila);
        }
    }

    public boolean validarStockDisponible(int idProducto, int cantidad) {
        return productoService.validarStockDisponible(idProducto, cantidad);
    }

    public int contarProductosActivos() {
        return productoService.contarProductosActivos();
    }

    public int contarProductosBajoStock() {
        return productoService.contarProductosBajoStock();
    }

}
