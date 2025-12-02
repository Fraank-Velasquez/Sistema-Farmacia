/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciasystem.factory;

/**
 *
 * @author Frank
 */
public interface IReporte {

    public void generarReporte();

    public byte[] obtenerBytes();

    public String obtenerNombreArchivo();

    public String obenerTipoReporte();

}
