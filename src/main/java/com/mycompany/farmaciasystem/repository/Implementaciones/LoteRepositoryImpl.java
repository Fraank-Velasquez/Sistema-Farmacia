/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Implementaciones;

import com.mycompany.farmaciasystem.configuración.ConexionDb;
import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import com.mycompany.farmaciasystem.repository.Interfaces.ILoteRepository;
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
public class LoteRepositoryImpl implements ILoteRepository {

    private final ConexionDb conectardb;
    private ProductoRepositoryImpl productoRepository;

    public LoteRepositoryImpl() {
        this.conectardb = ConexionDb.getInstancia();
        this.productoRepository = new ProductoRepositoryImpl();
    }

    @Override
    public List<Lote> listarTodos() {
        List<Lote> lotes = new ArrayList<>();
        String sql = "SELECT * FROM lotes WHERE activo = true ORDER BY fecha_vencimiento";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lotes.add(guardarDatosLote(rs));
            }

        } catch (SQLException e) {
            e.toString(); 
        }

        return lotes;
    }

    @Override
    public Lote buscarPorID(int id) {
        String sql = "SELECT * FROM lotes WHERE id_lote = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosLote(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }

        return null;
    }

    @Override
    public boolean insertar(Lote entidad) {
        String sql = "INSERT INTO lotes (id_producto, id_empresa_proveedor, numero_lote, "
                + "fecha_fabricacion, fecha_vencimiento, precio_compra, cantidad_inicial, "
                + "activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, entidad.getIdProducto());
            pst.setInt(2, entidad.getIdEmpresaProveedor());
            pst.setString(3, entidad.getNumeroLote());
            pst.setDate(4, Date.valueOf(entidad.getFechaFabricacion()));
            pst.setDate(5, Date.valueOf(entidad.getFechaVencimiento()));
            pst.setDouble(6, entidad.getPrecioCompra());
            pst.setInt(7, entidad.getCantidadInicial());
            pst.setBoolean(8, true);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    @Override
    public boolean actualizar(int id_entidad, Lote entidad) {
        String sql = "UPDATE lotes SET numero_lote = ?, fecha_fabricacion = ?, "
                + "fecha_vencimiento = ?, precio_compra = ?, cantidad_inicial = ? " 
                + "WHERE id_lote = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, entidad.getNumeroLote());
            pst.setDate(2, Date.valueOf(entidad.getFechaFabricacion()));
            pst.setDate(3, Date.valueOf(entidad.getFechaVencimiento()));
            pst.setDouble(4, entidad.getPrecioCompra());
            pst.setInt(5, entidad.getCantidadInicial());
            pst.setInt(6, entidad.getIdLote()); 

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {

        String sql = "UPDATE lotes SET activo = false WHERE id_lote = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }
        return false;
    }

    @Override
    public List<Lote> ListarProximosVencer(int dias) {

        List<Lote> lotesPorVencer = new ArrayList<>();
        String sql = "SELECT * FROM lotes WHERE fecha_vencimiento BETWEEN CURRENT_DATE "
                + "AND CURRENT_DATE + INTERVAL '" + dias + " days' AND activo = true "
                + "ORDER BY fecha_vencimiento";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lotesPorVencer.add(guardarDatosLote(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return lotesPorVencer;
    }

    @Override
    public List<Lote> listarPorProducto(int idProducto) {
        List<Lote> lotes = new ArrayList<>();
        String sql = "SELECT * FROM lotes WHERE id_producto = ? AND activo = true "
                + "ORDER BY fecha_vencimiento";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idProducto);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lotes.add(guardarDatosLote(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return lotes;
    }

    @Override
    public List<Lote> ListarVencidos() {
        List<Lote> lotesVencidos = new ArrayList<>();
        String sql = "SELECT * FROM lotes WHERE fecha_vencimiento < CURRENT_DATE "
                + "AND activo = true ORDER BY fecha_vencimiento";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lotesVencidos.add(guardarDatosLote(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return lotesVencidos;
    }

    @Override
    public Lote obtenerLoteMasAntiguoDisponible(int idProducto) {
        String sql = "SELECT * FROM lotes WHERE id_producto = ? AND activo = true "
                + "AND fecha_vencimiento > CURRENT_DATE AND cantidad_inicial > 0 "
                + "ORDER BY fecha_vencimiento LIMIT 1";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idProducto);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosLote(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }

        return null;
    }

    private Lote guardarDatosLote(ResultSet rs) throws SQLException {

        Lote lote = new Lote();

        lote.setIdLote(rs.getInt("id_lote"));
        lote.setIdProducto(rs.getInt("id_producto"));
        lote.setIdEmpresaProveedor(rs.getInt("id_empresa_proveedor"));
        lote.setNumeroLote(rs.getString("numero_lote"));

        Date fechaFab = rs.getDate("fecha_fabricacion");
        if (fechaFab != null) {
            lote.setFechaFabricacion(fechaFab.toLocalDate());
        }

        Date fechaVenc = rs.getDate("fecha_vencimiento");
        if (fechaVenc != null) {
            lote.setFechaVencimiento(fechaVenc.toLocalDate());
        }

        lote.setPrecioCompra(rs.getDouble("precio_compra"));
        lote.setCantidadInicial(rs.getInt("cantidad_inicial"));
        lote.setActivo(rs.getBoolean("activo"));

        return lote;
    }

    @Override
    public boolean actualizarCantidadLote(int idLote, int cantidadConsumida) {
        String sql = "UPDATE lotes SET cantidad_inicial = cantidad_inicial - ? WHERE id_lote = ?";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, cantidadConsumida); 
            pst.setInt(2, idLote);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    @Override
    public int obtenerIdPorNumeroLote(String numeroLote) {
        String sql = "SELECT id_lote FROM lotes WHERE numero_lote = ? ORDER BY id_lote DESC LIMIT 1";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, numeroLote);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_lote");
            }
        } catch (SQLException e) {
            e.toString();
        }
        return -1;
    }
}
