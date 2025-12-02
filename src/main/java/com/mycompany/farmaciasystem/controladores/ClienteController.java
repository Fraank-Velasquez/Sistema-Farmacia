/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.controladores;

import com.mycompany.farmaciasystem.modelo.entidades.Cliente;
import com.mycompany.farmaciasystem.servicios.ClienteService;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Frank
 */
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteService.listarTodos();
    }

    public Cliente buscarClientePorId(int id) {
        return clienteService.buscarPorId(id);
    }

    public Cliente buscarClientePorDni(String dni) {
        return clienteService.buscarPorDni(dni);
    }

    public boolean guardarCliente(Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    public boolean actualizarCliente(Cliente cliente) {
        return clienteService.actualizarCliente(cliente);
    }

    public boolean eliminarCliente(int id) {
        return clienteService.eliminarCliente(id);
    }

    public List<Cliente> buscarClientesPorNombre(String nombre) {
        return clienteService.buscarPorNombre(nombre);
    }

    public void cargarClientesEnTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        List<Cliente> clientes = obtenerTodosLosClientes();

        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.getIdCliente(),
                cliente.getDni(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getTelefono(),
                cliente.getEmail()
            };
            modeloTabla.addRow(fila);
        }
    }

    public int contarClientesActivos() {
        return clienteService.contarClientesActivos();
    }

}
