/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Interfaces;

import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import java.util.List;

/**
 *
 * @author Frank
 */
public interface IProductoRepository extends IRepository<Producto> {

    List<Producto> buscarPorNombre(String nombre);

    List<Producto> listarBajoStock();

    List<Producto> listarPorCategoria(int idCategoria);

}
