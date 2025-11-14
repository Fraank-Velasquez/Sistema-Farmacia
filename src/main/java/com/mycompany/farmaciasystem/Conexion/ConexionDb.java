package com.mycompany.farmaciasystem.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase Singleton para gestionar la conexión a la base de datos
 *
 * @author Frank
 */
public class ConexionDb {

    // Instancia única de la clase
    private static ConexionDb instancia;

    // Conexión a la base de datos
    private Connection conexion;

    private final String user = "fvelasquezl";
    private final String password = "admin123";
    private final String ip = "localhost";
    private final String puerto = "1433";
    private final String database = "farmaciaDB";
    private final String url = "jdbc:sqlserver://" + ip + ":" + puerto
            + ";databaseName=" + database
            + ";encrypt=true;trustServerCertificate=true;";

    /**
     * Constructor privado para evitar instanciación directa
     */
    private ConexionDb() {
        // Constructor vacío privado
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
                JOptionPane.showMessageDialog(null,"Conexión establecida con la base de datos",
                        "Exitoso",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al conectar: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null,
                    "Error al cerrar conexión: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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

/* 
 * EJEMPLO DE USO:
 * 
 * // Obtener la instancia única
 * ConexionDb db = ConexionDb.getInstancia();
 * 
 * // Obtener la conexión
 * Connection conn = db.getConexion();
 * 
 * // Usar la conexión para ejecutar queries
 * Statement stmt = conn.createStatement();
 * ResultSet rs = stmt.executeQuery("SELECT * FROM tabla");
 * 
 * // Cerrar la conexión cuando ya no se necesite
 * db.cerrarConexion();
 */
