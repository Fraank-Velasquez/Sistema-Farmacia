/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.servicios;

import com.mycompany.farmaciasystem.modelo.entidades.Promocion;
import com.mycompany.farmaciasystem.repository.Implementaciones.PromocionRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IPromocionRepository;
import java.util.List;

/**
 *
 * @author Frank
 */
public class PromocionService {

    private IPromocionRepository promocionRepository;

    public PromocionService() {
        this.promocionRepository = new PromocionRepositoryImpl();
    }

    public List<Promocion> listarTodas() {
        return promocionRepository.listarTodos();
    }

    public Promocion buscarPorId(int id) {
        return promocionRepository.buscarPorID(id);
    }

    public List<Promocion> listarActivas() {
        return promocionRepository.listarActivas();
    }

    public List<Promocion> listarPorProducto(int idProducto) {
        return promocionRepository.listarPorProductos(idProducto);
    }

    public boolean guardarPromocion(Promocion promocion) {
        if (!validarPromocion(promocion)) {
            return false;
        }

        return promocionRepository.insertar(promocion);
    }

    public boolean actualizarPromocion(Promocion promocion) {
        if (!validarPromocion(promocion)) {
            return false;
        }

        return promocionRepository.actualizar(promocion.getIdPromocion(), promocion);
    }

    public boolean eliminarPromocion(int id) {
        return promocionRepository.eliminar(id);
    }

    private boolean validarPromocion(Promocion promocion) {
        if (promocion.getNombre() == null || promocion.getNombre().trim().isEmpty()) {
            System.err.println("Validacion fallida: Nombre de promocion es requerido");
            return false;
        }

        if (promocion.getFechaInicio() == null) {
            System.err.println("Validacion fallida: Fecha de inicio es requerida");
            return false;
        }

        if (promocion.getFechaFin() == null) {
            System.err.println("Validacion fallida: Fecha de fin es requerida");
            return false;
        }

        if (promocion.getFechaFin().isBefore(promocion.getFechaInicio())) {
            System.err.println("Validacion fallida: Fecha fin debe ser posterior a fecha inicio");
            return false;
        }

        if (promocion.getValorDescuento() <= 0) {
            System.err.println("Validacion fallida: Valor de descuento debe ser mayor a 0");
            return false;
        }

        String tipo = promocion.getTipoDescuento();
        if (tipo == null || (!tipo.equalsIgnoreCase("porcentaje") && !tipo.equalsIgnoreCase("fijo"))) {
            System.err.println("Validacion fallida: Tipo de descuento debe ser 'porcentaje' o 'fijo'");
            return false;
        }

        return true;
    }

    public int contarPromocionesActivas() {
        return listarActivas().size();
    }

}
