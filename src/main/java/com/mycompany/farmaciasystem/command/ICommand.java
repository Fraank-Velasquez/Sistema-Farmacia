/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.command;

/**
 *
 * @author Frank
 */
public interface ICommand {

    public boolean ejecutar();

    public boolean deshacer();

    public String obtenerDescripcion();

    public String obtenerTipo();

}
