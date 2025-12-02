/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Implementaciones;

import com.mycompany.farmaciasystem.configuración.ConexionDb;
import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.repository.Interfaces.IProductoRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank
 */
public class ProductoRepositoryImpl implements IProductoRepository {

    private final ConexionDb conectardb;

    public ProductoRepositoryImpl() {
        this.conectardb = ConexionDb.getInstancia();
    }

    @Override
    public List<Producto> listarTodos() {

        List<Producto> listaProductos = new ArrayList<>();
        String sql = "select * from productos";

        try (Connection conn = conectardb.establecerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                listaProductos.add(guardarDatosProductos(rs));
            }

        } catch (SQLException e) {
            e.toString(); 
        }
        return listaProductos;

    }

    @Override
    public Producto buscarPorID(int id) {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";

        try (Connection conn = conectardb.establecerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosProductos(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }
        return null;
    }

    @Override
    public boolean insertar(Producto entidad) {
        
        String sql = "call insertarProductos(?,?,?,?,?,?,?,?)";

        try (Connection conn = conectardb.establecerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, entidad.getNombre());
            pst.setString(2, entidad.getDescripcion());
            pst.setInt(3, entidad.getIdCategoria());
            pst.setInt(4, entidad.getIdEmpresaFabricante());
            pst.setDouble(5, entidad.getPrecioVenta());
            pst.setInt(6, entidad.getStockActual());
            pst.setInt(7, entidad.getStockMinimo());
            pst.setBoolean(8, true); 

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.toString();
        }
        return false;

    }

    @Override
    public boolean actualizar(int id_entidad, Producto entidad) {
        
        String sql = "UPDATE productos SET nombre=?, descripcion=?, id_categoria=?, "
                   + "id_empresa_fabricante=?, precio_venta=?, stock_actual=?, stock_minimo=? "
                   + "WHERE id_producto=?";

        // IMPLEMENTACIÓN DE TRY-WITH-RESOURCES
        try (Connection conn = conectardb.establecerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, entidad.getNombre());
            pst.setString(2, entidad.getDescripcion());
            pst.setInt(3, entidad.getIdCategoria());
            pst.setInt(4, entidad.getIdEmpresaFabricante());
            pst.setDouble(5, entidad.getPrecioVenta());
            pst.setInt(6, entidad.getStockActual());
            pst.setInt(7, entidad.getStockMinimo());
            
            // Faltaba asignar el ID para saber a quién actualizar
            pst.setInt(8, entidad.getIdProducto()); 

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id_producto = ?"; 
        
        try (Connection conn = conectardb.establecerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }
        return false;
    }

    private Producto guardarDatosProductos(ResultSet rs) throws SQLException {

        Producto producto = new Producto();

        producto.setIdProducto(rs.getInt("id_producto"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setIdCategoria(rs.getInt("id_categoria"));
        producto.setIdEmpresaFabricante(rs.getInt("id_empresa_fabricante")); 
        producto.setPrecioVenta(rs.getDouble("precio_venta"));
        producto.setStockActual(rs.getInt("stock_actual"));
        producto.setStockMinimo(rs.getInt("stock_minimo"));
        producto.setActivo(rs.getBoolean("activo"));

        return producto;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {

        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE LOWER(nombre) LIKE LOWER(?) AND activo = true";

        try (Connection conn = conectardb.establecerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, "%" + nombre + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                productos.add(guardarDatosProductos(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return productos;
    }

    @Override
    public List<Producto> listarBajoStock() {

        List<Producto> productosBajoStock = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE stock_actual <= stock_minimo AND activo = true";

        try (Connection conn = conectardb.establecerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                productosBajoStock.add(guardarDatosProductos(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return productosBajoStock;
    }

    @Override
    public List<Producto> listarPorCategoria(int idCategoria) {

        List<Producto> productosPorCategoria = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE id_categoria = ? AND activo = true";

        try (Connection conn = conectardb.establecerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idCategoria);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                productosPorCategoria.add(guardarDatosProductos(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return productosPorCategoria;
    }

}