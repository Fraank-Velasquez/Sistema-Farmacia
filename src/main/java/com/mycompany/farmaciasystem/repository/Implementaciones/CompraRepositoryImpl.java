/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Implementaciones;

import com.mycompany.farmaciasystem.configuración.ConexionDb;
import com.mycompany.farmaciasystem.modelo.entidades.Compra;
import com.mycompany.farmaciasystem.repository.Interfaces.ICompraRepository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank
 */
public class CompraRepositoryImpl implements ICompraRepository {

    private final ConexionDb conectardb;

    public CompraRepositoryImpl() {
        this.conectardb = ConexionDb.getInstancia();
    }

    @Override
    public List<Compra> listarPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {

        List<Compra> comprasPorFecha = new ArrayList<>();
        String sql = "SELECT * FROM compras WHERE fecha_compra BETWEEN ? AND ? "
                + "ORDER BY fecha_compra DESC";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setDate(1, Date.valueOf(fechaInicio));
            pst.setDate(2, Date.valueOf(fechaFin));
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                comprasPorFecha.add(guardarDatosCompra(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return comprasPorFecha;
    }

    @Override
    public List<Compra> listarPorProveedor(int idProveedor) {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compras WHERE id_proveedor = ? ORDER BY fecha_compra DESC";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idProveedor);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                compras.add(guardarDatosCompra(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return compras;
    }

    @Override
    public Compra buscarPorNumeroFactura(String numeroFactura) {
        String sql = "SELECT * FROM compras WHERE numero_factura = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, numeroFactura);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosCompra(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }

        return null;
    }

    @Override
    public List<Compra> listarTodos() {
        List<Compra> listaCompras = new ArrayList<>();

        String sql = "SELECT * FROM compras ORDER BY fecha_compra DESC";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                listaCompras.add(guardarDatosCompra(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return listaCompras;
    }

    @Override
    public Compra buscarPorID(int id) {
        String sql = "SELECT * FROM compras WHERE id_compra = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosCompra(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }

        return null;
    }

    @Override
    public boolean insertar(Compra entidad) {
        String sql = "INSERT INTO compras (id_proveedor, id_usuario, numero_factura, subtotal, "
                + "impuesto, total, fecha_compra, observaciones, fecha_registro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, entidad.getIdProveedor());
            pst.setInt(2, entidad.getIdUsuario());
            pst.setString(3, entidad.getNumeroFactura());
            pst.setDouble(4, entidad.getSubtotal());
            pst.setDouble(5, entidad.getImpuesto());
            pst.setDouble(6, entidad.getTotal());
            pst.setDate(7, Date.valueOf(entidad.getFechaCompra()));
            pst.setString(8, entidad.getObservaciones());
            pst.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    @Override
    public boolean actualizar(int id_entidad, Compra entidad) {
        String sql = "UPDATE compras SET id_proveedor = ?, numero_factura = ?, subtotal = ?, "
                + "impuesto = ?, total = ?, fecha_compra = ?, observaciones = ? "
                + "WHERE id_compra = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, entidad.getIdProveedor());
            pst.setString(2, entidad.getNumeroFactura());
            pst.setDouble(3, entidad.getSubtotal());
            pst.setDouble(4, entidad.getImpuesto());
            pst.setDouble(5, entidad.getTotal());
            pst.setDate(6, Date.valueOf(entidad.getFechaCompra()));
            pst.setString(7, entidad.getObservaciones());
            pst.setInt(8, entidad.getIdCompra());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = " call eliminarCompra(?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }
        return false;
    }

    @Override
    public int insertarYRetornarId(Compra compra) {
        String sql = "INSERT INTO compras (id_proveedor, id_usuario, numero_factura, subtotal, "
                + "impuesto, total, fecha_compra, observaciones, fecha_registro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_compra";

        try (Connection conn = conectardb.establecerConexion();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, compra.getIdProveedor());
            pst.setInt(2, compra.getIdUsuario());
            pst.setString(3, compra.getNumeroFactura());
            pst.setDouble(4, compra.getSubtotal());
            pst.setDouble(5, compra.getImpuesto());
            pst.setDouble(6, compra.getTotal());
            pst.setDate(7, Date.valueOf(compra.getFechaCompra()));
            pst.setString(8, compra.getObservaciones());
            pst.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_compra");
            }

        } catch (SQLException e) {
            e.toString();
        }

        return -1;
    }

    private Compra guardarDatosCompra(ResultSet rs) throws SQLException {

        Compra compra = new Compra();
        compra.setIdCompra(rs.getInt("id_compra"));
        compra.setIdProveedor(rs.getInt("id_proveedor"));
        compra.setIdUsuario(rs.getInt("id_usuario"));
        compra.setNumeroFactura(rs.getString("numero_factura"));
        compra.setSubtotal(rs.getDouble("subtotal"));
        compra.setImpuesto(rs.getDouble("impuesto"));
        compra.setTotal(rs.getDouble("total"));

        Date fechaCompra = rs.getDate("fecha_compra");
        if (fechaCompra != null) {
            compra.setFechaCompra(fechaCompra.toLocalDate());
        }

        compra.setObservaciones(rs.getString("observaciones"));

        Timestamp timestamp = rs.getTimestamp("fecha_registro");
        if (timestamp != null) {
            compra.setFechaRegistro(timestamp.toLocalDateTime());
        }

        return compra;
    }
}
