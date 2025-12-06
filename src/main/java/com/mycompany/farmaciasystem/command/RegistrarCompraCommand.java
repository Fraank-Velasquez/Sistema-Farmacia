/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.command;

import com.mycompany.farmaciasystem.modelo.entidades.Compra;
import com.mycompany.farmaciasystem.modelo.entidades.DetalleCompra;
import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import com.mycompany.farmaciasystem.repository.Implementaciones.CompraRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.DetalleCompraRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.LoteRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.ProductoRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.ICompraRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.IDetalleCompraRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.ILoteRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.IProductoRepository;
import java.util.List;

/**
 * Comando transaccional para registrar una compra completa.
 *
 * @author Frank
 */
public class RegistrarCompraCommand implements ICommand {

    private Compra compra;
    private List<Lote> nuevosLotes;

    private ICompraRepository compraRepository;
    private ILoteRepository loteRepository;
    private IProductoRepository productoRepository;
    private IDetalleCompraRepository detalleCompraRepository;

    private int idCompraGenerada;

    public RegistrarCompraCommand(Compra compra, List<Lote> nuevosLotes) {
        this.compra = compra;
        this.nuevosLotes = nuevosLotes;

        this.compraRepository = new CompraRepositoryImpl();
        this.loteRepository = new LoteRepositoryImpl();
        this.productoRepository = new ProductoRepositoryImpl();
        this.detalleCompraRepository = new DetalleCompraRepositoryImpl();

        this.idCompraGenerada = -1;
    }

    @Override
    public boolean ejecutar() {
        if (nuevosLotes == null || nuevosLotes.isEmpty()) {
            System.err.println("Command Error: No hay productos/lotes en la compra.");
            return false;
        }

        try {
            int idGenerado = compraRepository.insertarYRetornarId(compra);

            if (idGenerado == -1) {
                System.err.println("Command: Error al registrar la cabecera de compra.");
                return false;
            }
            this.idCompraGenerada = idGenerado;

            //  Procesar cada Lote (Item de la compra)
            for (Lote lote : nuevosLotes) {

                // Insertar el Lote en la BD
                if (!loteRepository.insertar(lote)) {
                    System.err.println("Command: Error al insertar lote " + lote.getNumeroLote());
                    return false;
                }

                // Obtener el ID del lote recién creado 
                int idLote = loteRepository.obtenerIdPorNumeroLote(lote.getNumeroLote());
                if (idLote == -1) {
                    System.err.println("Command: No se pudo recuperar el ID del lote insertado.");
                    return false;
                }

                // Actualizar Stock Global del Producto
                productoRepository.actualizarStock(lote.getIdProducto(), lote.getCantidadInicial());

                // Insertar Detalle de Compra 
                DetalleCompra detalle = new DetalleCompra(idGenerado, idLote, lote.getCantidadInicial());
                detalleCompraRepository.insertar(detalle);
            }

            System.out.println("Command: Compra registrada exitosamente - ID: " + idCompraGenerada);
            return true;

        } catch (Exception e) {
            System.err.println("Command: Excepcion crítica - " + e.getMessage());
            e.toString();
            return false;
        }
    }

    @Override
    public boolean deshacer() {
        if (idCompraGenerada == -1) {
            return false;
        }
        return compraRepository.eliminar(idCompraGenerada);
    }

    @Override
    public String obtenerDescripcion() {
        return "Registro de Compra ID: " + idCompraGenerada;
    }

    @Override
    public String obtenerTipo() {
        return "REGISTRAR_COMPRA";
    }

    public int getIdCompraGenerada() {
        return idCompraGenerada;
    }
}
