/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Interfaces;

import com.mycompany.farmaciasystem.modelo.entidades.Venta;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Frank
 */
public interface IVentaRepository extends IRepository<Venta> {

    int insertarYRetornarID(Venta venta);

    List<Venta> listarPorFecha(LocalDate fechaInicio, LocalDate fechaFin);

    List<Venta> listarPorCliente(int idCliente);

}
