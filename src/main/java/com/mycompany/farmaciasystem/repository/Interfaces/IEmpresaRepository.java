/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Interfaces;

import com.mycompany.farmaciasystem.modelo.entidades.Empresa;
import java.util.List;

/**
 *
 * @author Frank
 */
public interface IEmpresaRepository extends IRepository<Empresa> {

    List<Empresa> listarPorTipo(String tipoEmpresa);

    List<Empresa> listarProveedores();

    List<Empresa> listarLaboratorios();


}
