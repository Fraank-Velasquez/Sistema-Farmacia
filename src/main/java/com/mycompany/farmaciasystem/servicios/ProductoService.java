/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.servicios;

import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.observer.InventarioObservable;
import com.mycompany.farmaciasystem.repository.Implementaciones.ProductoRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IProductoRepository;
import java.util.List;

/**
 *
 * @author Frank
 */
public class ProductoService {

    private IProductoRepository productoRepository;
    private InventarioObservable inventarioObservable;

    public ProductoService() {
        this.productoRepository = new ProductoRepositoryImpl();
        this.inventarioObservable = new InventarioObservable();
    }

    public List<Producto> listarTodos() {
        return productoRepository.listarTodos();
    }

    public Producto buscarPorId(int id) {
        return productoRepository.buscarPorID(id);
    }

    public boolean guardarProducto(Producto producto) {
        if (!validarProducto(producto)) {
            return false;
        }

        return productoRepository.insertar(producto);
    }

    public boolean actualizarProducto(Producto producto) {
        if (!validarProducto(producto)) {
            return false;
        }

        boolean resultado = productoRepository.actualizar(producto.getIdProducto(),producto);

        if (resultado) {
            inventarioObservable.notificarActualizacionStock(producto);
        }

        return resultado;
    }

    public boolean eliminarProducto(int id) {
        return productoRepository.eliminar(id);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return listarTodos();
        }
        return productoRepository.buscarPorNombre(nombre);
    }

    public List<Producto> listarProductosBajoStock() {
        return productoRepository.listarBajoStock();
    }

    public List<Producto> listarPorCategoria(int idCategoria) {
        return productoRepository.listarPorCategoria(idCategoria);
    }

    public boolean validarStockDisponible(int idProducto, int cantidadRequerida) {
        Producto producto = buscarPorId(idProducto);
        if (producto == null) {
            return false;
        }
        return producto.getStockActual() >= cantidadRequerida;
    }

    private boolean validarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            System.err.println("Validacion fallida: Nombre del producto es requerido");
            return false;
        }

        if (producto.getPrecioVenta() <= 0) {
            System.err.println("Validacion fallida: Precio debe ser mayor a 0");
            return false;
        }

        if (producto.getStockMinimo() < 0) {
            System.err.println("Validacion fallida: Stock minimo no puede ser negativo");
            return false;
        }

        return true;
    }

    public int contarProductosActivos() {
        return listarTodos().size();
    }

    public int contarProductosBajoStock() {
        return listarProductosBajoStock().size();
    }

}
