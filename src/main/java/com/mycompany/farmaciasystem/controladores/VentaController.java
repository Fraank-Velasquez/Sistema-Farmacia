/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.controladores;

import com.mycompany.farmaciasystem.command.CommandInvoker;
import com.mycompany.farmaciasystem.command.ICommand;
import com.mycompany.farmaciasystem.command.RegistrarVentaCommand;
import com.mycompany.farmaciasystem.facade.ResultadoVenta;
import com.mycompany.farmaciasystem.modelo.DTO.ItemVentaDTO;
import com.mycompany.farmaciasystem.modelo.DTO.VentaDTO;
import com.mycompany.farmaciasystem.modelo.entidades.DetalleVenta;
import com.mycompany.farmaciasystem.modelo.entidades.Venta;
import com.mycompany.farmaciasystem.servicios.VentaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Frank
 */
public class VentaController {

    private VentaService ventaService;
    private CommandInvoker commandInvoker;

    public VentaController() {
        this.ventaService = new VentaService();
        this.commandInvoker = new CommandInvoker();
    }

    public ResultadoVenta procesarVenta(VentaDTO ventaDTO) {
        ICommand comando = new RegistrarVentaCommand(ventaDTO);
        boolean exito = commandInvoker.ejecutarComando(comando);

        if (exito) {
            RegistrarVentaCommand cmd = (RegistrarVentaCommand) comando;
            return new ResultadoVenta(true, "Venta registrada", cmd.getIdVentaGenerada(), 0);
        } else {
            return new ResultadoVenta(false, "Error al registrar venta");
        }
    }

    public boolean anularUltimaVenta() {
        return commandInvoker.deshacerUltimoComando();
    }

    public List<Venta> obtenerTodasLasVentas() {
        return ventaService.listarTodas();
    }

    public Venta buscarVentaPorId(int id) {
        return ventaService.buscarPorId(id);
    }

    public List<Venta> obtenerVentasPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return ventaService.listarPorFecha(fechaInicio, fechaFin);
    }

    public List<Venta> obtenerVentasPorCliente(int idCliente) {
        return ventaService.listarPorCliente(idCliente);
    }

    public List<DetalleVenta> obtenerDetallesVenta(int idVenta) {
        return ventaService.obtenerDetallesVenta(idVenta);
    }

    public void cargarVentasEnTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        List<Venta> ventas = obtenerTodasLasVentas();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Venta venta : ventas) {
            Object[] fila = {
                venta.getIdVenta(),
                venta.getIdCliente(),
                String.format("S/. %.2f", venta.getSubtotal()),
                String.format("S/. %.2f", venta.getDescuento()),
                String.format("S/. %.2f", venta.getTotal()),
                venta.getFechaVenta().format(formatter)
            };
            modeloTabla.addRow(fila);
        }
    }

    public void cargarDetallesVentaEnTabla(int idVenta, DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        List<DetalleVenta> detalles = obtenerDetallesVenta(idVenta);

        for (DetalleVenta detalle : detalles) {
            Object[] fila = {
                detalle.getIdLote(),
                detalle.getCantidad(),
                String.format("S/. %.2f", detalle.getPrecioUnitario()),
                String.format("S/. %.2f", detalle.getDescuentoAplicado()),
                String.format("S/. %.2f", detalle.getSubtotal())
            };
            modeloTabla.addRow(fila);
        }
    }

    public double calcularTotalVentasDelDia() {
        return ventaService.calcularTotalVentasDelDia();
    }

    public int contarVentasDelDia() {
        return ventaService.contarVentasDelDia();
    }

    public VentaDTO crearVentaDTO(int idCliente, int idUsuario, List<ItemVentaDTO> items, double descuentoGeneral) {
        VentaDTO ventaDTO = new VentaDTO(idCliente, idUsuario);
        ventaDTO.setItems(items);
        ventaDTO.setDescuentoGeneral(descuentoGeneral);
        return ventaDTO;
    }

}
