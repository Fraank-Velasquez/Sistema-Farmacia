/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Implementaciones;

import com.mycompany.farmaciasystem.Conexion.ConexionDb;
import com.mycompany.farmaciasystem.modelo.entidades.Usuario;
import com.mycompany.farmaciasystem.repository.Interfaces.IUsuarioRepository;
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
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    private final ConexionDb conectarBD;

    public UsuarioRepositoryImpl() {
        this.conectarBD = ConexionDb.getInstancia();
    }

    @Override
    public Usuario ValidarLogin(String nombreUsuario, String contrasenia) {

        Usuario usuario = null;

        try {

            Connection conex = conectarBD.establecerConexion();
            PreparedStatement pst = conex.prepareStatement("Select * from usuarios where nombre_usuario= ?  and contrasenia =?");
            pst.setString(1, nombreUsuario);
            pst.setString(2, contrasenia);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setRol(rs.getString("rol"));
                usuario.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                usuario.setActivo(rs.getBoolean("activo"));
            }

        } catch (SQLException e) {
            e.toString();
        }

        return usuario;

    }

    @Override
    public List<Usuario> listarTodos() {

        List<Usuario> usuarios = new ArrayList<>();
        String sql = "select * from usuarios";

        try {

            Connection conx = conectarBD.establecerConexion();
            PreparedStatement st = conx.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Usuario user = new Usuario();

                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setNombreUsuario(rs.getString("nombre_usuario"));
                user.setContrasenia(rs.getString("contrasenia"));
                user.setNombres(rs.getString("nombres"));
                user.setApellidos(rs.getString("apellidos"));
                user.setEmail(rs.getString("email"));
                user.setRol(rs.getString("rol"));
                user.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                user.setActivo(rs.getBoolean("activo"));

                usuarios.add(user);
            }

        } catch (SQLException e) {
            e.toString();
        }

        return usuarios;
    }

    @Override
    public Usuario buscarPorID(int id) {

        List<Usuario> usersPorID = new ArrayList<>();
        String sql = "call buscar_usuarioID(?)";

        try {

            Connection conn = conectarBD.establecerConexion();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                Usuario user = new Usuario();

                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setNombreUsuario(rs.getString("nombre_usuario"));
                user.setContrasenia(rs.getString("contrasenia"));
                user.setNombres(rs.getString("nombres"));
                user.setApellidos(rs.getString("apellidos"));
                user.setEmail(rs.getString("email"));
                user.setRol(rs.getString("rol"));
                user.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                user.setActivo(rs.getBoolean("activo"));

                usersPorID.add(user);

            }

        } catch (SQLException e) {

            e.toString();
        }
        return (Usuario) usersPorID;
    }

    @Override
    public boolean insertar(Usuario entidad) {

        String sql = "call insertar_usuario(?,?,?,?,?,?)";
        try {

            Connection conn = conectarBD.establecerConexion();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, entidad.getNombreUsuario());
            st.setString(2, entidad.getContrasenia());
            st.setString(3, entidad.getNombres());
            st.setString(4, entidad.getApellidos());
            st.setString(5, entidad.getEmail());
            st.setString(6, entidad.getRol());

            st.executeUpdate();

        } catch (SQLException e) {
            e.toString();
        }
        return false;
    }

    @Override
    public boolean actualizar(Usuario entidad) {

        String sql = "call editar_usuario(?,?,?,?,?,?)";

        try {
            Connection conn = conectarBD.establecerConexion();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, entidad.getNombreUsuario());
            st.setString(2, entidad.getContrasenia());
            st.setString(3, entidad.getNombres());
            st.setString(4, entidad.getApellidos());
            st.setString(5, entidad.getEmail());
            st.setString(6, entidad.getRol());

            st.executeUpdate();

        } catch (SQLException e) {
            e.toString();
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {

        String sql = "call eliminar_usuario(?)";

        try {
            Connection conn = conectarBD.establecerConexion();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.toString();
        }
        return false;
    }
}
