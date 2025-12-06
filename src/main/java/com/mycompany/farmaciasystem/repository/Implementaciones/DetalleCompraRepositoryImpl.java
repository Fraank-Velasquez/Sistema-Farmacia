package com.mycompany.farmaciasystem.repository.Implementaciones;

import com.mycompany.farmaciasystem.configuración.ConexionDb;
import com.mycompany.farmaciasystem.modelo.entidades.DetalleCompra;
import com.mycompany.farmaciasystem.repository.Interfaces.IDetalleCompraRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetalleCompraRepositoryImpl implements IDetalleCompraRepository {

    private final ConexionDb conectardb;

    public DetalleCompraRepositoryImpl() {
        this.conectardb = ConexionDb.getInstancia();
    }

    @Override
    public boolean insertar(DetalleCompra detalle) {
        String sql = "INSERT INTO detalle_compras (id_compra, id_lote, cantidad) VALUES (?, ?, ?)";

        try (Connection conn = conectardb.establecerConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, detalle.getIdCompra());
            pst.setInt(2, detalle.getIdLote());
            pst.setInt(3, detalle.getCantidad());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en DetalleCompraRepository: " + e.getMessage());
            return false;
        }
    }
}
