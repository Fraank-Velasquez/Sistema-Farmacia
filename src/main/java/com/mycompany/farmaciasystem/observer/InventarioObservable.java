/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.observer;

import com.mycompany.farmaciasystem.modelo.entidades.Lote;
import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.repository.Implementaciones.LoteRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.ProductoRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.ILoteRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.IProductoRepository;
import java.util.List;

/**
 *
 * @author Frank
 */
public class InventarioObservable extends Observable {

    private IProductoRepository productoRepository;
    private ILoteRepository loteRepository;

    public InventarioObservable() {
        super();
        this.productoRepository = new ProductoRepositoryImpl();
        this.loteRepository = new LoteRepositoryImpl();
    }

    public void verificarStockBajo() {
        List<Producto> productosBajoStock = productoRepository.listarBajoStock();

        if (!productosBajoStock.isEmpty()) {
            notificarObservadores("STOCK_BAJO", productosBajoStock);
        }
    }

    public void verificarStockCritico() {
        List<Producto> todosProductos = productoRepository.listarTodos();
        int productosCriticos = 0;

        for (Producto producto : todosProductos) {
            if (producto.getStockActual() == 0) {
                productosCriticos++;
                notificarObservadores("STOCK_CRITICO", producto);
            }
        }

        if (productosCriticos > 0) {
            notificarObservadores("RESUMEN_STOCK_CRITICO", productosCriticos);
        }
    }

    public void verificarLotesProximosVencer(int dias) {
        List<Lote> lotesProximos = loteRepository.ListarProximosVencer(dias);

        if (!lotesProximos.isEmpty()) {
            notificarObservadores("LOTES_PROXIMOS_VENCER", lotesProximos);
        }
    }

    public void verificarLotesVencidos() {
        List<Lote> lotesVencidos = loteRepository.ListarVencidos();

        if (!lotesVencidos.isEmpty()) {
            notificarObservadores("LOTES_VENCIDOS", lotesVencidos);
        }
    }

    public void verificarInventarioCompleto() {
        verificarStockBajo();
        verificarStockCritico();
        verificarLotesProximosVencer(30);
        verificarLotesVencidos();
    }

    public void notificarActualizacionStock(Producto producto) {
        if (producto.getStockActual() <= producto.getStockMinimo()) {
            notificarObservadores("PRODUCTO_BAJO_STOCK", producto);
        }
    }

}
