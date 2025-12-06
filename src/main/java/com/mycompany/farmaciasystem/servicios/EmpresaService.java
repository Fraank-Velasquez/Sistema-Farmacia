/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.servicios;

import com.mycompany.farmaciasystem.modelo.entidades.Empresa;
import com.mycompany.farmaciasystem.repository.Implementaciones.EmpresaRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IEmpresaRepository;
import java.util.List;

/**
 *
 * @author Frank
 */
public class EmpresaService {

    private IEmpresaRepository empresaRepository;

    public EmpresaService() {
        this.empresaRepository = new EmpresaRepositoryImpl();
    }

    public List<Empresa> listarTodas() {
        return empresaRepository.listarTodos();
    }

    public Empresa buscarPorId(int id) {
        return empresaRepository.buscarPorID(id);
    }

    public List<Empresa> listarProveedores() {
        return empresaRepository.listarProveedores();
    }

    public List<Empresa> listarLaboratorios() {
        return empresaRepository.listarLaboratorios();
    }

    public boolean guardarEmpresa(Empresa empresa) {
        if (!validarEmpresa(empresa)) {
            return false;
        }

        return empresaRepository.insertar(empresa);
    }

    public boolean actualizarEmpresa(Empresa empresa) {
        if (!validarEmpresa(empresa)) {
            return false;
        }

        return empresaRepository.actualizar(empresa.getIdEmpresa(), empresa);
    }

    public boolean eliminarEmpresa(int id) {
        return empresaRepository.eliminar(id);
    }

    private boolean validarEmpresa(Empresa empresa) {
        if (empresa.getNombre() == null || empresa.getNombre().trim().isEmpty()) {
            System.err.println("Validacion fallida: Nombre de empresa es requerido");
            return false;
        }

        if (empresa.getRuc() == null || empresa.getRuc().trim().isEmpty()) {
            System.err.println("Validacion fallida: RUC es requerido");
            return false;
        }

        if (empresa.getRuc().length() != 11) {
            System.err.println("Validacion fallida: RUC debe tener 11 digitos");
            return false;
        }

        if (empresa.getTipoEmpresa() == null || empresa.getTipoEmpresa().trim().isEmpty()) {
            System.err.println("Validacion fallida: Tipo de empresa es requerido");
            return false;
        }

        String tipo = empresa.getTipoEmpresa().toLowerCase();
        if (!tipo.equals("proveedor") && !tipo.equals("laboratorio") && !tipo.equals("ambos")) {
            System.err.println("Validacion fallida: Tipo de empresa debe ser 'proveedor', 'laboratorio' o 'ambos'");
            return false;
        }

        return true;
    }

    public int contarProveedores() {
        return listarProveedores().size();
    }

}
