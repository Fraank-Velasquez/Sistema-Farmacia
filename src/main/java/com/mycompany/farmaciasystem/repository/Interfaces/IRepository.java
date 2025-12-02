/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Interfaces;

import java.util.List;

/**
 *
 * @author Frank
 * @param <T>
 */
public interface IRepository<T> {

    List<T> listarTodos();

    T buscarPorID(int id);

    boolean insertar(T entidad);

    boolean actualizar(int id_entidad, T entidad);

    boolean eliminar(int id);
    

}
