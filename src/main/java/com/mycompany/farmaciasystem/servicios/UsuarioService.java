/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.servicios;

import com.mycompany.farmaciasystem.modelo.entidades.Usuario;
import com.mycompany.farmaciasystem.repository.Implementaciones.UsuarioRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IUsuarioRepository;
import java.util.List;

/**
 *
 * @author Frank
 */
public class UsuarioService {

    private IUsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepositoryImpl();
    }

    public Usuario validarLogin(String nombreUsuario, String contrasenia) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            return null;
        }

        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            return null;
        }

        return usuarioRepository.ValidarLogin(nombreUsuario, contrasenia);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.listarTodos();
    }

    public Usuario buscarPorId(int id) {
        return usuarioRepository.buscarPorID(id);
    }

    public boolean guardarUsuario(Usuario usuario) {
        if (!validarUsuario(usuario)) {
            return false;
        }
        return usuarioRepository.insertar(usuario);
    }

    public boolean actualizarUsuario(Usuario usuario) {
        if (!validarUsuario(usuario)) {
            return false;
        }

        return usuarioRepository.actualizar(usuario.getIdUsuario(), usuario);
    }

    public boolean eliminarUsuario(int id) {
        return usuarioRepository.eliminar(id);
    }

    private boolean validarUsuario(Usuario usuario) {
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
            System.err.println("Validacion fallida: Nombre de usuario es requerido");
            return false;
        }

        if (usuario.getContrasenia() == null || usuario.getContrasenia().trim().isEmpty()) {
            System.err.println("Validacion fallida: Contrasenia es requerida");
            return false;
        }

        if (usuario.getNombres() == null || usuario.getNombres().trim().isEmpty()) {
            System.err.println("Validacion fallida: Nombres son requeridos");
            return false;
        }

        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
            System.err.println("Validacion fallida: Rol es requerido");
            return false;
        }

        return true;
    }

}
