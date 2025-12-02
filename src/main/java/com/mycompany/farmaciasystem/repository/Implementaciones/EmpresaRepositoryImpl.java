/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 */
package com.mycompany.farmaciasystem.repository.Implementaciones;

import com.mycompany.farmaciasystem.configuración.ConexionDb;
import com.mycompany.farmaciasystem.modelo.entidades.Empresa;
import com.mycompany.farmaciasystem.repository.Interfaces.IEmpresaRepository;
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
public class EmpresaRepositoryImpl implements IEmpresaRepository {

    private final ConexionDb conectardb;

    public EmpresaRepositoryImpl() {
        this.conectardb = ConexionDb.getInstancia();
    }

    @Override
    public List<Empresa> listarTodos() {

        List<Empresa> listaTodos = new ArrayList<>();
        String sql = "select * from empresa";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                listaTodos.add(guardarDatosEmpresa(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }
        return listaTodos;
    }

    @Override
    public Empresa buscarPorID(int id) {

        String sql = "call buscarEmpresaID(?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosEmpresa(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }
        return null;
    }

    @Override
    public boolean insertar(Empresa entidad) {

        String sql = "call insertarEmpresa(?,?,?,?,?,?,?,?,?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, entidad.getNombre());
            pst.setString(2, entidad.getTelefono());
            pst.setString(3, entidad.getEmail());
            pst.setString(4, entidad.getDireccion());
            pst.setString(5, entidad.getCiudad());
            pst.setString(6, entidad.getRuc());
            pst.setString(7, entidad.getTipoEmpresa());
            pst.setTimestamp(8, entidad.getFechaRegistro());
            pst.setBoolean(9, true);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }
        return false;

    }

    @Override
    public boolean actualizar(int id_entidad, Empresa entidad) {
        String sql = "call actualizarEmpresa(?,?,?,?,?,?,?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, entidad.getNombre());
            pst.setString(2, entidad.getTelefono());
            pst.setString(3, entidad.getEmail());
            pst.setString(4, entidad.getDireccion());
            pst.setString(5, entidad.getCiudad());
            pst.setString(6, entidad.getRuc());
            pst.setString(7, entidad.getTipoEmpresa());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {

        String sql = " call eliminarEmpresa(?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }
        return false;
    }

    private Empresa guardarDatosEmpresa(ResultSet rs) throws SQLException {

        Empresa empresa = new Empresa();

        empresa.setIdEmpresa(rs.getInt("id_empresa"));
        empresa.setNombre(rs.getString("nombre"));
        empresa.setTelefono(rs.getString("telefono"));
        empresa.setEmail(rs.getString("email"));
        empresa.setDireccion(rs.getString("direccion"));
        empresa.setCiudad(rs.getString("ciudad"));
        empresa.setRuc(rs.getString("ruc"));
        empresa.setTipoEmpresa(rs.getString("tipo_empresa"));
        empresa.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        empresa.setActivo(rs.getBoolean("activo"));

        return empresa;
    }

    @Override
    public List<Empresa> listarPorTipo(String tipoEmpresa) {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM empresa WHERE tipo_empresa = ? AND activo = true";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, tipoEmpresa);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                empresas.add(guardarDatosEmpresa(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return empresas;
    }

    @Override
    public Empresa buscarPorRuc(String ruc) {
        String sql = "SELECT * FROM empresa WHERE ruc = ? AND activo = true";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, ruc);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosEmpresa(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }

        return null;
    }

    @Override
    public List<Empresa> listarProveedores() {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM empresa WHERE (tipo_empresa = 'proveedor' OR tipo_empresa = 'ambos') "
                + "AND activo = true ORDER BY nombre";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                empresas.add(guardarDatosEmpresa(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return empresas;
    }

    @Override
    public List<Empresa> listarLaboratorios() {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM empresa WHERE (tipo_empresa = 'laboratorio' OR tipo_empresa = 'ambos') "
                + "AND activo = true ORDER BY nombre";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                empresas.add(guardarDatosEmpresa(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return empresas;
    }
}
