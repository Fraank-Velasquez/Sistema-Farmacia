/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Interfaces;

import com.mycompany.farmaciasystem.modelo.entidades.Compra;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Frank
 */
public interface ICompraRepository extends IRepository<Compra> {

    int insertarYRetornarId(Compra compra);

    List<Compra> listarPorFecha(LocalDate fechaInicio, LocalDate fechaFin);

    List<Compra> listarPorProveedor(int idProveedor);

    Compra buscarPorNumeroFactura(String numeroFactura);

}
