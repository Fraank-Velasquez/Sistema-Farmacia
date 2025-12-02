package com.mycompany.farmaciasystem.configuración;


/**
 *
 * @author Frank
 */
public class PruebaConexion {
    public static void main(String[] args) {
        
        ConexionDb db = ConexionDb.getInstancia();
        db.establecerConexion();
        
    }
    
}
