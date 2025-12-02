/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.controladores;

import com.mycompany.farmaciasystem.observer.AlertaBajoStockObserver;
import com.mycompany.farmaciasystem.observer.AlertaLoteVencidoObserver;
import com.mycompany.farmaciasystem.observer.InventarioObservable;
import com.mycompany.farmaciasystem.observer.NotificacionDashboardObserver;
import com.mycompany.farmaciasystem.servicios.ClienteService;
import com.mycompany.farmaciasystem.servicios.EmpresaService;
import com.mycompany.farmaciasystem.servicios.LoteService;
import com.mycompany.farmaciasystem.servicios.ProductoService;
import com.mycompany.farmaciasystem.servicios.VentaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Frank
 */
public class DashboardController {

    private ProductoService productoService;
    private VentaService ventaService;
    private ClienteService clienteService;
    private EmpresaService empresaService;
    private LoteService loteService;
    private NotificacionDashboardObserver notificacionObserver;

    public DashboardController() {
        this.productoService = new ProductoService();
        this.ventaService = new VentaService();
        this.clienteService = new ClienteService();
        this.empresaService = new EmpresaService();
        this.loteService = new LoteService();
    }

    public Map<String, Object> obtenerEstadisticasDashboard() {
        Map<String, Object> estadisticas = new HashMap<>();

        estadisticas.put("totalProductos", productoService.contarProductosActivos());
        estadisticas.put("productosBajoStock", productoService.contarProductosBajoStock());
        estadisticas.put("ventasDelDia", ventaService.contarVentasDelDia());
        estadisticas.put("totalVentasDelDia", ventaService.calcularTotalVentasDelDia());
        estadisticas.put("totalClientes", clienteService.contarClientesActivos());
        estadisticas.put("totalProveedores", empresaService.contarProveedores());
        estadisticas.put("lotesVencidos", loteService.contarLotesVencidos());
        estadisticas.put("lotesProximosVencer", loteService.contarLotesProximosVencer(30));

        return estadisticas;
    }

    public NotificacionDashboardObserver verificarAlertas() {
        InventarioObservable inventario = new InventarioObservable();

        AlertaBajoStockObserver alertaStock = new AlertaBajoStockObserver("Dashboard");
        AlertaLoteVencidoObserver alertaLotes = new AlertaLoteVencidoObserver("Dashboard");
        notificacionObserver = new NotificacionDashboardObserver();

        inventario.agregarObservador(alertaStock);
        inventario.agregarObservador(alertaLotes);
        inventario.agregarObservador(notificacionObserver);

        inventario.verificarInventarioCompleto();

        return notificacionObserver;
    }

    public List<String> obtenerNotificaciones() {
        if (notificacionObserver != null) {
            return notificacionObserver.obtenerNotificaciones();
        }
        return null;
    }

    public boolean hayAlertas() {
        if (notificacionObserver != null) {
            return notificacionObserver.hayAlertas();
        }
        return false;
    }

}
