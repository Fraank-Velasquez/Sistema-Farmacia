/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Implementaciones;

import com.mycompany.farmaciasystem.configuración.ConexionDb;
import com.mycompany.farmaciasystem.modelo.entidades.Promocion;
import com.mycompany.farmaciasystem.repository.Interfaces.IPromocionRepository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank
 */
public class PromocionRepositoryImpl implements IPromocionRepository {

    private final ConexionDb conectardb;

    public PromocionRepositoryImpl() {
        this.conectardb = ConexionDb.getInstancia();
    }

    @Override
    public List<Promocion> listarTodos() {

        List<Promocion> lista = new ArrayList<>();
        String sql = "SELECT * FROM promociones WHERE activo = true ORDER BY id_promocion";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(guardarDatosPromocion(rs));
            }
        } catch (SQLException e) {
            e.toString();
        }
        return lista;
    }

    @Override
    public Promocion buscarPorID(int id) {

        String sql = "SELECT * FROM promociones WHERE id_promocion = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosPromocion(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }
        return null;
    }

    @Override
    public boolean insertar(Promocion entidad) {

        String sql = "INSERT INTO promociones (nombre, descripcion, tipo_descuento, valor_descuento, fecha_inicio, fecha_fin, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, entidad.getNombre());
            pst.setString(2, entidad.getDescripcion());
            pst.setString(3, entidad.getTipoDescuento()); 
            pst.setDouble(4, entidad.getValorDescuento());
            pst.setDate(5, java.sql.Date.valueOf(entidad.getFechaInicio()));
            pst.setDate(6, java.sql.Date.valueOf(entidad.getFechaFin()));
            pst.setBoolean(7, true);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insertando promoción: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(int id_entidad, Promocion entidad) {

        String sql = "UPDATE promociones SET nombre=?, descripcion=?, tipo_descuento=?, valor_descuento=?, fecha_inicio=?, fecha_fin=? "
                + "WHERE id_promocion=?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, entidad.getNombre());
            pst.setString(2, entidad.getDescripcion());
            pst.setString(3, entidad.getTipoDescuento());
            pst.setDouble(4, entidad.getValorDescuento());
            pst.setDate(5, java.sql.Date.valueOf(entidad.getFechaInicio()));
            pst.setDate(6, java.sql.Date.valueOf(entidad.getFechaFin()));
            pst.setInt(7, id_entidad);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error actualizando promoción: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "UPDATE promociones SET activo = false WHERE id_promocion = ?";
        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Promocion> listarActivas() {

        List<Promocion> promociones = new ArrayList<>();
        String sql = "SELECT * FROM promociones WHERE activo = true "
                + "AND fecha_inicio <= CURRENT_DATE AND fecha_fin >= CURRENT_DATE "
                + "ORDER BY fecha_inicio DESC";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                promociones.add(guardarDatosPromocion(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return promociones;
    }

    @Override
    public List<Promocion> listarPorProductos(int idProducto) {
        List<Promocion> promociones = new ArrayList<>();
        String sql = "SELECT p.* FROM promociones p "
                + "INNER JOIN producto_promocion pp ON p.id_promocion = pp.id_promocion "
                + "WHERE pp.id_producto = ? AND p.activo = true "
                + "AND p.fecha_inicio <= CURRENT_DATE AND p.fecha_fin >= CURRENT_DATE";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idProducto);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                promociones.add(guardarDatosPromocion(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return promociones;
    }

    @Override
    public List<Promocion> buscarPorNombre(String nombre) {
        List<Promocion> lista = new ArrayList<>();
        String sql = "SELECT * FROM promociones WHERE LOWER(nombre) LIKE LOWER(?) AND activo = true";
        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + nombre + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(guardarDatosPromocion(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Promocion guardarDatosPromocion(ResultSet rs) throws SQLException {
        Promocion promocion = new Promocion();
        promocion.setIdPromocion(rs.getInt("id_promocion"));
        promocion.setNombre(rs.getString("nombre"));
        promocion.setDescripcion(rs.getString("descripcion"));
        promocion.setTipoDescuento(rs.getString("tipo_descuento"));
        promocion.setValorDescuento(rs.getDouble("valor_descuento"));

        Date fechaInicio = rs.getDate("fecha_inicio");
        if (fechaInicio != null) {
            promocion.setFechaInicio(fechaInicio.toLocalDate());
        }

        Date fechaFin = rs.getDate("fecha_fin");
        if (fechaFin != null) {
            promocion.setFechaFin(fechaFin.toLocalDate());
        }

        promocion.setActivo(rs.getBoolean("activo"));

        return promocion;
    }

}
