/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.controladores;

import com.mycompany.farmaciasystem.modelo.entidades.Empresa;
import com.mycompany.farmaciasystem.servicios.EmpresaService;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Frank
 */
public class EmpresaController {
    
    private EmpresaService empresaService;
    
    public EmpresaController() {
        this.empresaService = new EmpresaService();
    }
    
    public List<Empresa> obtenerTodasLasEmpresas() {
        return empresaService.listarTodas();
    }
    
    public Empresa buscarEmpresaPorId(int id) {
        return empresaService.buscarPorId(id);
    }
    
    public List<Empresa> obtenerProveedores() {
        return empresaService.listarProveedores();
    }
    
    public List<Empresa> obtenerLaboratorios() {
        return empresaService.listarLaboratorios();
    }
    
    public boolean guardarEmpresa(Empresa empresa) {
        return empresaService.guardarEmpresa(empresa);
    }
    
    public boolean actualizarEmpresa(Empresa empresa) {
        return empresaService.actualizarEmpresa(empresa);
    }
    
    public boolean eliminarEmpresa(int id) {
        return empresaService.eliminarEmpresa(id);
    }
    
    public void cargarProveedoresEnTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        List<Empresa> proveedores = obtenerProveedores();
        
        for (Empresa empresa : proveedores) {
            Object[] fila = {
                empresa.getIdEmpresa(),
                empresa.getNombre(),
                empresa.getRuc(),
                empresa.getTelefono(),
                empresa.getEmail(),
                empresa.getCiudad(),
                empresa.getTipoEmpresa()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    public void cargarLaboratoriosEnTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        List<Empresa> laboratorios = obtenerLaboratorios();
        
        for (Empresa empresa : laboratorios) {
            Object[] fila = {
                empresa.getIdEmpresa(),
                empresa.getNombre(),
                empresa.getRuc(),
                empresa.getTelefono(),
                empresa.getEmail()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    public int contarProveedores() {
        return empresaService.contarProveedores();
    }
    
}
