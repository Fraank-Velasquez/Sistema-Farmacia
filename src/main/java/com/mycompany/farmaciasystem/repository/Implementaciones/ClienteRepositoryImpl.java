/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Implementaciones;

import com.mycompany.farmaciasystem.configuración.ConexionDb;
import com.mycompany.farmaciasystem.modelo.entidades.Cliente;
import com.mycompany.farmaciasystem.repository.Interfaces.IClienteRepository;
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
public class ClienteRepositoryImpl implements IClienteRepository {

    private final ConexionDb conectarDB;

    public ClienteRepositoryImpl() {
        this.conectarDB = ConexionDb.getInstancia();
    }

    @Override
    public List<Cliente> buscarPorNombre(String criterio) {

        String sql = "SELECT * FROM clientes WHERE (LOWER(nombres) LIKE LOWER(?) OR LOWER(apellidos) LIKE LOWER(?) OR dni LIKE ?) AND activo = true";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = conectarDB.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            String filtro = "%" + criterio + "%";
            pst.setString(1, filtro);
            pst.setString(2, filtro);
            pst.setString(3, filtro);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(guardarDatosClientes(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Cliente> listarTodos() {

        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE activo = true ORDER BY id_cliente";
        try (Connection conn = conectarDB.establecerConexion(); PreparedStatement pst = conn.prepareCall(sql)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                listaClientes.add(guardarDatosClientes(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }
        return listaClientes;
    }

    @Override
    public Cliente buscarPorID(int id) {

        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        try (Connection conn = conectarDB.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return guardarDatosClientes(rs);
            }

        } catch (SQLException e) {
            e.toString();
        }
        return null;
    }

    @Override
    public boolean insertar(Cliente entidad) {

        String sql = "INSERT INTO clientes (dni, nombres, apellidos, telefono, email, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectarDB.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, entidad.getDni());
            pst.setString(2, entidad.getNombres());
            pst.setString(3, entidad.getApellidos());
            pst.setString(4, entidad.getTelefono());
            pst.setString(5, entidad.getEmail());
            pst.setBoolean(6, true);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }
        return false;
    }

    @Override
    public boolean actualizar(int id_entidad, Cliente entidad) {

        String sql = "UPDATE clientes SET dni=?, nombres=?, apellidos=?, telefono=?, email=? WHERE id_cliente=?";
        try (Connection conn = conectarDB.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, entidad.getDni());
            pst.setString(2, entidad.getNombres());
            pst.setString(3, entidad.getApellidos());
            pst.setString(4, entidad.getTelefono());
            pst.setString(5, entidad.getEmail());
            pst.setInt(6, id_entidad);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {

        String sql = "UPDATE clientes SET activo = false WHERE id_cliente = ?";
        try (Connection conn = conectarDB.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    private Cliente guardarDatosClientes(ResultSet rs) throws SQLException {

        Cliente cliente = new Cliente();

        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setDni(rs.getString("dni"));
        cliente.setNombres(rs.getString("nombres"));
        cliente.setApellidos(rs.getString("apellidos"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setEmail(rs.getString("email"));
        cliente.setActivo(rs.getBoolean("activo"));

        return cliente;
    }
}
