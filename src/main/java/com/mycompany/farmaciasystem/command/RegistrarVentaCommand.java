/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.command;

import com.mycompany.farmaciasystem.facade.ResultadoVenta;
import com.mycompany.farmaciasystem.facade.VentaFacade;
import com.mycompany.farmaciasystem.modelo.DTO.VentaDTO;

/**
 * Comando que encapsula la solicitud de registrar una venta
 *
 * * @author Frank
 */
public class RegistrarVentaCommand implements ICommand {

    private VentaDTO ventaDTO;
    private VentaFacade ventaFacade;
    private ResultadoVenta resultado;

    public RegistrarVentaCommand(VentaDTO ventaDTO) {
        this.ventaDTO = ventaDTO;
        this.ventaFacade = new VentaFacade();
    }

    @Override
    public boolean ejecutar() {
        
        this.resultado = ventaFacade.procesarVenta(ventaDTO);

        if (resultado.isExitoso()) {
            System.out.println("Command: Venta registrada exitosamente. ID: " + resultado.getIdVenta());
            return true;
        } else {
            System.err.println("Command: Error al registrar venta. " + resultado.getMensaje());
            return false;
        }
    }

    @Override
    public boolean deshacer() {
        // La reversión de una venta implica reingresar stock 
        // Por ahora, retornamos false ya que requiere una implementación compleja de devoluciones.
        return false;
    }

    @Override
    public String obtenerDescripcion() {
        return "Registro de Venta para Cliente ID: " + ventaDTO.getIdCliente();
    }

    @Override
    public String obtenerTipo() {
        return "REGISTRAR_VENTA";
    }

    public ResultadoVenta getResultado() {
        return resultado;
    }
}
