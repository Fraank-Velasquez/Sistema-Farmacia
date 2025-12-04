package com.mycompany.farmaciasystem.configuración;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Frank
 */
public class ConexionDb {

    // Instancia única de la clase
    private static ConexionDb instancia;

    private Connection conexion;

    private final String user = "fvelasquezl";
    private final String password = "140120";
    private final String url = "jdbc:postgresql://localhost/farmacia_db";

    /**
     * Constructor privado para evitar instanciación directa
     */
    private ConexionDb() {
    }

    /**
     * Método sincronizado para obtener la instancia única
     *
     * @return Instancia única de ConexionDb
     */
    public static synchronized ConexionDb getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDb();
        }
        return instancia;
    }

    /**
     * Establecer y retornar la conexión a la base de datos Si ya existe una
     * conexión activa, la retorna sin crear una nueva
     *
     * @return Conexión a la base de datos
     */
    public Connection establecerConexion() {
        try {
            // Verifica si la conexión existe y está activa
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(url, user, password);
                System.out.println("Conexion establecida con la base de datos");
//                JOptionPane.showMessageDialog(null,"Conexión establecida con la base de datos",
//                        "Exitoso",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());

//            JOptionPane.showMessageDialog(null,
//                    "Error al conectar: " + e.getMessage(),
//                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return conexion;
    }

    /**
     * Cerrar la conexión a la base de datos
     */
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada exitosamente");
            }
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null,
//                    "Error al cerrar conexión: " + e.getMessage(),
//                    "Error",
//                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Verifica si la conexión está activa
     *
     * @return true si la conexión está activa, false en caso contrario
     */
    public boolean isConexionActiva() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
