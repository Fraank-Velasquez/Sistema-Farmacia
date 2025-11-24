/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Interfaces;

import com.mycompany.farmaciasystem.modelo.entidades.Cliente;
import java.util.List;

/**
 *
 * @author Frank
 */
public interface IClienteRepository extends  IRepository<Cliente> {
    
    Cliente buscarPorDni(String dni);
    List<Cliente> buscarPorNombre(String nombre);
}
