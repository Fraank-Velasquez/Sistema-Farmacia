/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.servicios;

import com.mycompany.farmaciasystem.modelo.entidades.Cliente;
import com.mycompany.farmaciasystem.repository.Implementaciones.ClienteRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IClienteRepository;
import java.util.List;

/**
 *
 * @author Frank
 */
public class ClienteService {

    private IClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = new ClienteRepositoryImpl();
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }

    public Cliente buscarPorId(int id) {
        return clienteRepository.buscarPorID(id);
    }

    public boolean guardarCliente(Cliente cliente) {
        if (!validarCliente(cliente)) {
            return false;
        }
        return clienteRepository.insertar(cliente);
    }

    public boolean actualizarCliente(Cliente cliente) {
        if (!validarCliente(cliente)) {
            return false;
        }

        return clienteRepository.actualizar(cliente.getIdCliente(),cliente);
    }

    public boolean eliminarCliente(int id) {
        return clienteRepository.eliminar(id);
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return listarTodos();
        }
        return clienteRepository.buscarPorNombre(nombre);
    }

    private boolean validarCliente(Cliente cliente) {
        if (cliente.getDni() == null || cliente.getDni().trim().isEmpty()) {
            System.err.println("Validacion fallida: DNI es requerido");
            return false;
        }

        if (cliente.getDni().length() != 8) {
            System.err.println("Validacion fallida: DNI debe tener 8 digitos");
            return false;
        }

        if (cliente.getNombres() == null || cliente.getNombres().trim().isEmpty()) {
            System.err.println("Validacion fallida: Nombres son requeridos");
            return false;
        }

        if (cliente.getApellidos() == null || cliente.getApellidos().trim().isEmpty()) {
            System.err.println("Validacion fallida: Apellidos son requeridos");
            return false;
        }

        return true;
    }

    public int contarClientesActivos() {
        return listarTodos().size();
    }
}
