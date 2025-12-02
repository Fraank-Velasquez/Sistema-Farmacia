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
    public Cliente buscarPorDni(String dni) {

        List<Cliente> listaPorDni = new ArrayList<>();
        String sql = "call buscarClientesPorDNI(?)";

        try (Connection conn = conectarDB.establecerConexion(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, dni);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                listaPorDni.add(guardarDatosClientes(rs));
            }
        } catch (SQLException e) {
            e.toString();
        }

        return (Cliente) listaPorDni;
    }

    @Override
    public List<Cliente> buscarPorNombre(String nombre) {

        List<Cliente> listaPorNombre = new ArrayList<>();
        String sql = "call buscarClientesPorNombre(?)";

        try (Connection conn = conectarDB.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nombre);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                listaPorNombre.add(guardarDatosClientes(rs));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return listaPorNombre;
    }

    @Override
    public List<Cliente> listarTodos() {

        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "select * from clientes";

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

        String sql = "call buscarClientePorID(?)";

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

        String sql = "call insertarClientes(?,?,?,?,?,?)";

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

        String sql = "call actualizarClientes(?,?,?,?,?)";

        try (Connection conn = conectarDB.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, entidad.getDni());
            pst.setString(2, entidad.getNombres());
            pst.setString(3, entidad.getApellidos());
            pst.setString(4, entidad.getTelefono());
            pst.setString(5, entidad.getEmail());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {

        String sql = " call eliminarCliente(?)";

        try (Connection conn = conectarDB.establecerConexion(); 
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.toString();
        }
        return false;
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
