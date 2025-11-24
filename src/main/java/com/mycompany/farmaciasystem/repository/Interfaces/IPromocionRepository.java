/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Interfaces;

import com.mycompany.farmaciasystem.modelo.entidades.Promocion;
import java.util.List;

/**
 *
 * @author Frank
 */
public interface IPromocionRepository extends IRepository<Promocion> {

    List<Promocion> listarActivas();

    List<Promocion> listarPorProductos(int idProducto);

}
