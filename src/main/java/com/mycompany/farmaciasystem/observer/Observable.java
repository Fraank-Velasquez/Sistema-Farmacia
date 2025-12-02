/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank
 */
public abstract class Observable {
    
    private List<IObserver> observadores;

    public Observable() {
        this.observadores = new ArrayList<>();
    }
    
    public void agregarObservador(IObserver observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }
    
    public void removerObservador(IObserver observador) {
        observadores.remove(observador);
    }
    
    public void notificarObservadores(String evento, Object datos) {
        for (IObserver observador : observadores) {
            observador.actualizar(evento, datos);
        }
    }
    
    public int contarObservadores() {
        return observadores.size();
    }
    
    
}
