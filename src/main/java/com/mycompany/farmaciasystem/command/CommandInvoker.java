
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.command;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Frank
 */
public class CommandInvoker {

    // INSTANCIA SINGLETON Para que el historial sea compartido 
    private static final CommandInvoker INSTANCE = new CommandInvoker();

    private Stack<ICommand> historialEjecutados;
    private Stack<ICommand> historialDeshechos;
    private List<RegistroComando> registroAuditoria;

    
    public CommandInvoker() {
        this.historialEjecutados = new Stack<>();
        this.historialDeshechos = new Stack<>();
        this.registroAuditoria = new ArrayList<>();
    }

    // Método para obtener la instancia única
    public static CommandInvoker getInstance() {
        return INSTANCE;
    }

    public static void ejecutar(ICommand comando) {
        INSTANCE.ejecutarComando(comando);
    }

    // Lógica de instancia 
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

    private void registrarEnAuditoria(ICommand comando, String accion) {
        RegistroComando registro = new RegistroComando(
                LocalDateTime.now(),
                comando.obtenerTipo(),
                comando.obtenerDescripcion(),
                accion
        );
        registroAuditoria.add(registro);
    }

    // Clase interna para el registro 
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
