/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Frank
 */
public class CommandInvoker {

    private Stack<ICommand> historialEjecutados;
    private Stack<ICommand> historialDeshechos;
    private List<RegistroComando> registroAuditoria;

    public CommandInvoker() {
        this.historialEjecutados = new Stack<>();
        this.historialDeshechos = new Stack<>();
        this.registroAuditoria = new ArrayList<>();
    }

    public boolean ejecutarComando(ICommand comando) {
        boolean resultado = comando.ejecutar();

        if (resultado) {
            historialEjecutados.push(comando);
            historialDeshechos.clear();

            // Registrar en auditoria
            registrarEnAuditoria(comando, "EJECUTADO");
        }

        return resultado;
    }

    public boolean deshacerUltimoComando() {
        if (historialEjecutados.isEmpty()) {
            System.out.println("No hay comandos para deshacer");
            return false;
        }

        ICommand comando = historialEjecutados.pop();
        boolean resultado = comando.deshacer();

        if (resultado) {
            historialDeshechos.push(comando);
            registrarEnAuditoria(comando, "DESHECHO");
        } else {
            historialEjecutados.push(comando);
        }

        return resultado;
    }

    public boolean rehacerUltimoComando() {
        if (historialDeshechos.isEmpty()) {
            System.out.println("No hay comandos para rehacer");
            return false;
        }

        ICommand comando = historialDeshechos.pop();
        boolean resultado = comando.ejecutar();

        if (resultado) {
            historialEjecutados.push(comando);
            registrarEnAuditoria(comando, "REHECHO");
        } else {
            historialDeshechos.push(comando);
        }

        return resultado;
    }

    public void limpiarHistorial() {
        historialEjecutados.clear();
        historialDeshechos.clear();
    }

    public int cantidadComandosEjecutados() {
        return historialEjecutados.size();
    }

    public int cantidadComandosDeshechos() {
        return historialDeshechos.size();
    }

    public List<String> obtenerHistorialDescripciones() {
        List<String> descripciones = new ArrayList<>();
        for (ICommand comando : historialEjecutados) {
            descripciones.add(comando.obtenerDescripcion());
        }
        return descripciones;
    }

    public List<RegistroComando> obtenerRegistroAuditoria() {
        return new ArrayList<>(registroAuditoria);
    }

    private void registrarEnAuditoria(ICommand comando, String accion) {
        RegistroComando registro = new RegistroComando(
                LocalDateTime.now(),
                comando.obtenerTipo(),
                comando.obtenerDescripcion(),
                accion
        );
        registroAuditoria.add(registro);
    }

    public void mostrarHistorial() {
        System.out.println("----------------------------------------");
        System.out.println("HISTORIAL DE COMANDOS");
        System.out.println("----------------------------------------");

        if (historialEjecutados.isEmpty()) {
            System.out.println("No hay comandos ejecutados");
        } else {
            int contador = 1;
            for (ICommand comando : historialEjecutados) {
                System.out.println(contador + ". " + comando.obtenerDescripcion());
                contador++;
            }
        }

        System.out.println("----------------------------------------");
    }

    public void mostrarAuditoria() {
        System.out.println("----------------------------------------");
        System.out.println("REGISTRO DE AUDITORIA");
        System.out.println("----------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        for (RegistroComando registro : registroAuditoria) {
            System.out.println("[" + registro.getFechaHora().format(formatter) + "]");
            System.out.println("Tipo: " + registro.getTipo());
            System.out.println("Accion: " + registro.getAccion());
            System.out.println("Descripcion: " + registro.getDescripcion());
            System.out.println("----------------------------------------");
        }

        System.out.println("----------------------------------------");
    }

    public class RegistroComando {

        private LocalDateTime fechaHora;
        private String tipo;
        private String descripcion;
        private String accion;

        public RegistroComando(LocalDateTime fechaHora, String tipo, String descripcion, String accion) {
            this.fechaHora = fechaHora;
            this.tipo = tipo;
            this.descripcion = descripcion;
            this.accion = accion;
        }

        public LocalDateTime getFechaHora() {
            return fechaHora;
        }

        public String getTipo() {
            return tipo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public String getAccion() {
            return accion;
        }
    }

}
