package com.mycompany.farmaciasystem.controladores;

import com.mycompany.farmaciasystem.modelo.entidades.Promocion;
import com.mycompany.farmaciasystem.repository.Implementaciones.PromocionRepositoryImpl;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PromocionController {

    private PromocionRepositoryImpl promocionRepo = new PromocionRepositoryImpl();

    public List<Promocion> listarTodas() {
        return promocionRepo.listarTodos();
    }

    public List<Promocion> buscar(String texto) {
        return promocionRepo.buscarPorNombre(texto);
    }

    public Promocion buscarPorId(int id) {
        return promocionRepo.buscarPorID(id);
    }

    public boolean guardar(Promocion p) {
        if (p.getValorDescuento() < 0) {
            return false;
        }
        if (p.getFechaInicio().isAfter(p.getFechaFin())) {
            return false;
        }
        return promocionRepo.insertar(p);
    }

    public boolean actualizar(Promocion p) {
        if (p.getFechaInicio().isAfter(p.getFechaFin())) {
            return false;
        }
        return promocionRepo.actualizar(p.getIdPromocion(), p);
    }

    public boolean eliminar(int id) {
        return promocionRepo.eliminar(id);
    }

    public void cargarTabla(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        List<Promocion> lista = listarTodas();
        for (Promocion p : lista) {
            Object[] fila = {
                p.getIdPromocion(),
                p.getNombre(),
                p.getDescripcion(),
                p.getTipoDescuento(),
                p.getValorDescuento(),
                p.getFechaInicio(),
                p.getFechaFin()
            };
            modelo.addRow(fila);
        }
    }
}
