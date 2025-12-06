/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Interfaces;

import com.mycompany.farmaciasystem.modelo.entidades.DetalleVenta;
import java.util.List;

/**
 *
 * @author Frank
 */
public interface IDetalleVentaRepository extends IRepository<DetalleVenta> {


    List<DetalleVenta> listarPorVenta(int idVenta);

}
