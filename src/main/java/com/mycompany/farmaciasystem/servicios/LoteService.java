/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.servicios;

import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import com.mycompany.farmaciasystem.repository.Implementaciones.LoteRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.ILoteRepository;
import java.util.List;

/**
 *
 * @author Frank
 */
public class LoteService {

    private ILoteRepository loteRepository;

    public LoteService() {
        this.loteRepository = new LoteRepositoryImpl();
    }

    public List<Lote> listarTodos() {
        return loteRepository.listarTodos();
    }

    public Lote buscarPorId(int id) {
        return loteRepository.buscarPorID(id);
    }

    public boolean guardarLote(Lote lote) {
        if (!validarLote(lote)) {
            return false;
        }

        return loteRepository.insertar(lote);
    }

    public boolean actualizarLote(Lote lote) {
        if (!validarLote(lote)) {
            return false;
        }

        return loteRepository.actualizar(lote.getIdLote(), lote);
    }

    public boolean eliminarLote(int id) {
        return loteRepository.eliminar(id);
    }

    public List<Lote> listarPorProducto(int idProducto) {
        return loteRepository.listarPorProducto(idProducto);
    }

    public List<Lote> listarProximosVencer(int dias) {
        return loteRepository.ListarProximosVencer(dias);
    }

    public List<Lote> ListarVencidos() {
        return loteRepository.ListarVencidos();
    }

    public Lote obtenerLoteMasAntiguoDisponible(int idProducto) {
        return loteRepository.obtenerLoteMasAntiguoDisponible(idProducto);
    }

    private boolean validarLote(Lote lote) {
        if (lote.getNumeroLote() == null || lote.getNumeroLote().trim().isEmpty()) {
            System.err.println("Validacion fallida: Numero de lote es requerido");
            return false;
        }

        if (lote.getFechaVencimiento() == null) {
            System.err.println("Validacion fallida: Fecha de vencimiento es requerida");
            return false;
        }

        if (lote.getFechaFabricacion() != null
                && lote.getFechaVencimiento().isBefore(lote.getFechaFabricacion())) {
            System.err.println("Validacion fallida: Fecha de vencimiento debe ser posterior a fecha de fabricacion");
            return false;
        }

        if (lote.getCantidadInicial() <= 0) {
            System.err.println("Validacion fallida: Cantidad inicial debe ser mayor a 0");
            return false;
        }

        return true;
    }

    public int contarLotesVencidos() {
        return ListarVencidos().size();
    }

    public int contarLotesProximosVencer(int dias) {
        return listarProximosVencer(dias).size();
    }

}
