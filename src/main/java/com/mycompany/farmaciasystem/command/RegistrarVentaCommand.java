/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.command;

import com.mycompany.farmaciasystem.facade.ResultadoVenta;
import com.mycompany.farmaciasystem.facade.VentaFacade;
import com.mycompany.farmaciasystem.modelo.DTO.VentaDTO;
import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.repository.Implementaciones.ProductoRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.VentaRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IProductoRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.IVentaRepository;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Frank
 */
public class RegistrarVentaCommand implements ICommand{

    private VentaDTO ventaDTO;
    private VentaFacade ventaFacade;
    private int idVentaGenerada;
    private Map<Integer, Integer> stockAnterior;
    private IProductoRepository productoRepository;
    private IVentaRepository ventaRepository;

    public RegistrarVentaCommand(VentaDTO ventaDTO) {
        this.ventaDTO = ventaDTO;
        this.ventaFacade = new VentaFacade();
        this.stockAnterior = new HashMap<>();
        this.productoRepository = new ProductoRepositoryImpl();
        this.ventaRepository = new VentaRepositoryImpl();
        this.idVentaGenerada = -1;
    }

    @Override
    public boolean ejecutar() {
        try {
            // Guardar stock anterior de cada producto
            ventaDTO.getItems().forEach(item -> {
                Producto producto = productoRepository.buscarPorID(item.getIdProducto());
                if (producto != null) {
                    stockAnterior.put(producto.getIdProducto(), producto.getStockActual());
                }
            });

            // Procesar venta
            ResultadoVenta resultado = ventaFacade.procesarVenta(ventaDTO);

            if (resultado.isExitoso()) {
                idVentaGenerada = resultado.getIdVenta();
                System.out.println("Command: Venta registrada exitosamente - ID: " + idVentaGenerada);
                return true;
            } else {
                System.err.println("Command: Error al registrar venta - " + resultado.getMensaje());
                return false;
            }

        } catch (Exception e) {
            System.err.println("Command: Excepcion al ejecutar - " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deshacer() {
        if (idVentaGenerada == -1) {
            System.err.println("Command: No hay venta para deshacer");
            return false;
        }

        try {
            // Restaurar stock anterior
            for (Map.Entry<Integer, Integer> entry : stockAnterior.entrySet()) {
                Producto producto = productoRepository.buscarPorID(entry.getKey());
                if (producto != null) {
                    producto.setStockActual(entry.getValue());
                    productoRepository.actualizar(producto.getIdProducto(),producto);
                }
            }

            // Eliminar venta de la base de datos
            boolean eliminada = ventaRepository.eliminar(idVentaGenerada);

            if (eliminada) {
                System.out.println("Command: Venta anulada exitosamente - ID: " + idVentaGenerada);
                return true;
            } else {
                System.err.println("Command: Error al anular venta");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Command: Excepcion al deshacer - " + e.getMessage());
            return false;
        }
    }

    @Override
    public String obtenerDescripcion() {
        return "Registrar venta para cliente ID: " + ventaDTO.getIdCliente()
                + " con " + ventaDTO.getItems().size() + " productos";
    }

    @Override
    public String obtenerTipo() {
        return "REGISTRAR_VENTA";
    }

    public int getIdVentaGenerada() {
        return idVentaGenerada;
    }
}
