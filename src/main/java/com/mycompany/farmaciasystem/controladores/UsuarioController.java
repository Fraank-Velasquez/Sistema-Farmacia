/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.controladores;

import com.mycompany.farmaciasystem.modelo.entidades.Usuario;
import com.mycompany.farmaciasystem.servicios.UsuarioService;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Frank
 */
public class UsuarioController {

    private final UsuarioService usuarioService;
    private Usuario usuarioActual;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    public Usuario validarLogin(String nombreUsuario, String contrasenia) {
        Usuario usuario = usuarioService.validarLogin(nombreUsuario, contrasenia);

        if (usuario != null) {
            this.usuarioActual = usuario;
        }

        return usuario;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.listarTodos();
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioService.buscarPorId(id);
    }

    public boolean guardarUsuario(Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioService.actualizarUsuario(usuario);
    }

    public boolean eliminarUsuario(int id) {
        return usuarioService.eliminarUsuario(id);
    }

    public void cargarUsuariosEnTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        List<Usuario> usuarios = obtenerTodosLosUsuarios();

        for (Usuario usuario : usuarios) {
            Object[] fila = {
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getRol()
            };
            modeloTabla.addRow(fila);
        }
    }
}
