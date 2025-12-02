/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.servicios;

import com.mycompany.farmaciasystem.facade.ResultadoVenta;
import com.mycompany.farmaciasystem.facade.VentaFacade;
import com.mycompany.farmaciasystem.modelo.DTO.VentaDTO;
import com.mycompany.farmaciasystem.modelo.entidades.DetalleVenta;
import com.mycompany.farmaciasystem.modelo.entidades.Venta;
import com.mycompany.farmaciasystem.repository.Implementaciones.DetalleVentaRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.VentaRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IDetalleVentaRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.IVentaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Frank
 */
public class VentaService {

    private IVentaRepository ventaRepository;
    private IDetalleVentaRepository detalleVentaRepository;
    private VentaFacade ventaFacade;

    public VentaService() {
        this.ventaRepository = new VentaRepositoryImpl();
        this.detalleVentaRepository = new DetalleVentaRepositoryImpl();
        this.ventaFacade = new VentaFacade();
    }

    public ResultadoVenta procesarVenta(VentaDTO ventaDTO) {
        if (!validarVenta(ventaDTO)) {
            return new ResultadoVenta(false, "Datos de venta invalidos");
        }

        return ventaFacade.procesarVenta(ventaDTO);
    }

    public List<Venta> listarTodas() {
        return ventaRepository.listarTodos();
    }

    public Venta buscarPorId(int id) {
        return ventaRepository.buscarPorID(id);
    }

    public List<Venta> listarPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return ventaRepository.listarPorFecha(fechaInicio, fechaFin);
    }

    public List<Venta> listarPorCliente(int idCliente) {
        return ventaRepository.listarPorCliente(idCliente);
    }

    public List<DetalleVenta> obtenerDetallesVenta(int idVenta) {
        return detalleVentaRepository.listarPorVenta(idVenta);
    }

    public double calcularTotalVentasDelDia() {
        LocalDate hoy = LocalDate.now();
        List<Venta> ventasDelDia = listarPorFecha(hoy, hoy);

        return ventasDelDia.stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }

    public double calcularTotalVentasMes(int mes, int anio) {
        LocalDate inicioMes = LocalDate.of(anio, mes, 1);
        LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());

        List<Venta> ventasMes = listarPorFecha(inicioMes, finMes);

        return ventasMes.stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }

    public int contarVentasDelDia() {
        LocalDate hoy = LocalDate.now();
        return listarPorFecha(hoy, hoy).size();
    }

    private boolean validarVenta(VentaDTO ventaDTO) {
        if (ventaDTO.getIdCliente() <= 0) {
            System.err.println("Validacion fallida: Cliente no seleccionado");
            return false;
        }

        if (ventaDTO.getIdUsuario() <= 0) {
            System.err.println("Validacion fallida: Usuario no valido");
            return false;
        }

        if (ventaDTO.getItems() == null || ventaDTO.getItems().isEmpty()) {
            System.err.println("Validacion fallida: No hay productos en la venta");
            return false;
        }

        return true;
    }

}
