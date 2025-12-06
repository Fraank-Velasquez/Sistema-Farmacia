/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Implementaciones;

import com.mycompany.farmaciasystem.configuración.ConexionDb;
import com.mycompany.farmaciasystem.modelo.entidades.DetalleVenta;
import com.mycompany.farmaciasystem.repository.Interfaces.IDetalleVentaRepository;
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
public class DetalleVentaRepositoryImpl implements IDetalleVentaRepository {

    private final ConexionDb conectardb;

    public DetalleVentaRepositoryImpl() {
        this.conectardb = ConexionDb.getInstancia();
    }

    @Override
    public List<DetalleVenta> listarPorVenta(int idVenta) {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_ventas WHERE id_venta = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idVenta);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdDetalle(rs.getInt("id_detalle"));
                detalle.setIdVenta(rs.getInt("id_venta"));
                detalle.setIdLote(rs.getInt("id_lote"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalle.setDescuentoAplicado(rs.getDouble("descuento_aplicado"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    @Override
    public List<DetalleVenta> listarTodos() {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_ventas";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                detalles.add(guardarDatosDetVentas(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return detalles;
    }

    @Override
    public DetalleVenta buscarPorID(int id) {
        String sql = "SELECT * FROM detalle_ventas WHERE id_detalle = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosDetVentas(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }

        return null;
    }

    @Override
    public boolean insertar(DetalleVenta entidad) {
        String sql = "INSERT INTO detalle_ventas (id_venta, id_lote, cantidad, precio_unitario, descuento_aplicado) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, entidad.getIdVenta());
            pst.setInt(2, entidad.getIdLote());
            pst.setInt(3, entidad.getCantidad());
            pst.setDouble(4, entidad.getPrecioUnitario());
            pst.setDouble(5, entidad.getDescuentoAplicado());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    @Override
    public boolean actualizar(int id_entidad, DetalleVenta entidad) {
        String sql = "UPDATE detalle_ventas SET cantidad = ?, precio_unitario = ?, "
                + "descuento_aplicado = ? WHERE id_detalle = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, entidad.getCantidad());
            pst.setDouble(2, entidad.getPrecioUnitario());
            pst.setDouble(3, entidad.getDescuentoAplicado());
            pst.setInt(4, id_entidad);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM detalle_ventas WHERE id_detalle = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
            return false;
        }

    }

    private DetalleVenta guardarDatosDetVentas(ResultSet rs) throws SQLException {

        DetalleVenta detalle = new DetalleVenta();
        detalle.setIdDetalle(rs.getInt("id_detalle"));
        detalle.setIdVenta(rs.getInt("id_venta"));
        detalle.setIdLote(rs.getInt("id_lote"));
        detalle.setCantidad(rs.getInt("cantidad"));
        detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
        detalle.setDescuentoAplicado(rs.getDouble("descuento_aplicado"));
        return detalle;
    }

}
