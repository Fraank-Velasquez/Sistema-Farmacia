/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Implementaciones;

import com.mycompany.farmaciasystem.configuración.ConexionDb;
import com.mycompany.farmaciasystem.modelo.entidades.Venta;
import com.mycompany.farmaciasystem.repository.Interfaces.IVentaRepository;
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
public class VentaRepositoryImpl implements IVentaRepository {

    private final ConexionDb conectardb;

    public VentaRepositoryImpl() {
        this.conectardb = ConexionDb.getInstancia();
    }

    @Override
    public int insertarYRetornarID(Venta venta) {

        String sql = "INSERT INTO ventas (id_cliente, id_usuario, subtotal, descuento, total, fecha_venta) "
                + "VALUES (?, ?, ?, ?, ?, ?) RETURNING id_venta";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, venta.getIdCliente());
            pst.setInt(2, venta.getIdUsuario());
            pst.setDouble(3, venta.getSubtotal());
            pst.setDouble(4, venta.getDescuento());
            pst.setDouble(5, venta.getTotal());
            pst.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_venta");
            }

        } catch (SQLException e) {
            e.toString();
        }

        return -1;
    }

    @Override
    public List<Venta> listarPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {

        List<Venta> ventasPorFecha = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE DATE(fecha_venta) BETWEEN ? AND ? "
                + "ORDER BY fecha_venta DESC";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setDate(1, Date.valueOf(fechaInicio));
            pst.setDate(2, Date.valueOf(fechaFin));
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ventasPorFecha.add(guardarDatosVenta(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return ventasPorFecha;
    }

    @Override
    public List<Venta> listarPorCliente(int idCliente) {

        List<Venta> ventasPorCliente = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE id_cliente = ? ORDER BY fecha_venta DESC";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idCliente);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ventasPorCliente.add(guardarDatosVenta(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return ventasPorCliente;
    }

    @Override
    public List<Venta> listarTodos() {

        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas ORDER BY fecha_venta DESC";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ventas.add(guardarDatosVenta(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return ventas;
    }

    @Override
    public Venta buscarPorID(int id) {
        String sql = "SELECT * FROM ventas WHERE id_venta = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosVenta(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }
        return null;
    }

    @Override
    public boolean insertar(Venta entidad) {
        String sql = "INSERT INTO ventas (id_cliente, id_usuario, subtotal, descuento, total, fecha_venta) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, entidad.getIdCliente());
            pst.setInt(2, entidad.getIdUsuario());
            pst.setDouble(3, entidad.getSubtotal());
            pst.setDouble(4, entidad.getDescuento());
            pst.setDouble(5, entidad.getTotal());
            pst.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    @Override
    public boolean actualizar(int id_entidad, Venta entidad) {
        String sql = "UPDATE ventas SET id_cliente = ?, subtotal = ?, descuento = ?, "
                + "total = ? WHERE id_venta = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, entidad.getIdCliente());
            pst.setDouble(2, entidad.getSubtotal());
            pst.setDouble(3, entidad.getDescuento());
            pst.setDouble(4, entidad.getTotal());
            pst.setInt(5, id_entidad);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM ventas WHERE id_venta = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }
        return false;
    }

    private Venta guardarDatosVenta(ResultSet rs) throws SQLException {

        Venta venta = new Venta();
        venta.setIdVenta(rs.getInt("id_venta"));
        venta.setIdCliente(rs.getInt("id_cliente"));
        venta.setIdUsuario(rs.getInt("id_usuario"));
        venta.setSubtotal(rs.getDouble("subtotal"));
        venta.setDescuento(rs.getDouble("descuento"));
        venta.setTotal(rs.getDouble("total"));

        Timestamp fechaVenta = rs.getTimestamp("fecha_venta");
        if (fechaVenta != null) {
            venta.setFechaVenta(fechaVenta.toLocalDateTime());
        }

        return venta;
    }

}
