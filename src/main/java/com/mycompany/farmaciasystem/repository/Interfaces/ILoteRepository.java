/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.repository.Interfaces;

import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import java.util.List;

/**
 *
 * @author Frank
 */
public interface ILoteRepository extends IRepository<Lote> {

    List<Lote> ListarProximosVencer(int dias);

    List<Lote> ListarVencidos();

    List<Lote> listarPorProducto(int idProducto);

    public Lote obtenerLoteMasAntiguoDisponible(int idProducto);

    public boolean actualizarCantidadLote(int idLote, int cantidadConsumida);

}
