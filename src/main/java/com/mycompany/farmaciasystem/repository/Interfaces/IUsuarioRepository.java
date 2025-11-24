/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Interfaces;

import com.mycompany.farmaciasystem.modelo.entidades.Usuario;

/**
 *
 * @author Frank
 */
public interface IUsuarioRepository extends IRepository<Usuario> {

    Usuario ValidarLogin(String nombreUsuario, String contrasenia);

}
