/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciasystem.controladores;

import com.mycompany.farmaciasystem.command.CommandInvoker;
import com.mycompany.farmaciasystem.command.RegistrarVentaCommand;
import com.mycompany.farmaciasystem.decorator.IPrecio;
import com.mycompany.farmaciasystem.decorator.PrecioBase;
import com.mycompany.farmaciasystem.decorator.PromocionDecorator;
import com.mycompany.farmaciasystem.modelo.DTO.ItemVentaDTO;
import com.mycompany.farmaciasystem.modelo.DTO.VentaDTO;
import com.mycompany.farmaciasystem.modelo.entidades.Cliente;
import com.mycompany.farmaciasystem.modelo.entidades.DetalleVenta;
import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.modelo.entidades.Promocion;
import com.mycompany.farmaciasystem.modelo.entidades.Venta;
import com.mycompany.farmaciasystem.repository.Implementaciones.DetalleVentaRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.PromocionRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Implementaciones.VentaRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IDetalleVentaRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.IPromocionRepository;
import com.mycompany.farmaciasystem.repository.Interfaces.IVentaRepository;
import com.mycompany.farmaciasystem.servicios.ClienteService;
import com.mycompany.farmaciasystem.servicios.ProductoService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador actualizado para calcular descuentos en tiempo real.
 *
 * @author Frank
 */
public class VentaController {
    
    private final ProductoService productoService;
    private final IPromocionRepository promocionRepository;
    private final List<ItemVentaDTO> carritoCompras;
    private final List<Producto> listaProductosBusqueda;
    private final IVentaRepository ventaRepository = new VentaRepositoryImpl();
    private final IDetalleVentaRepository detalleRepository = new DetalleVentaRepositoryImpl();
    private final ClienteService clienteService = new ClienteService();
    
    public VentaController() {
        this.productoService = new ProductoService();
        this.promocionRepository = new PromocionRepositoryImpl(); // Inicializamos el repo
        this.carritoCompras = new ArrayList<>();
        this.listaProductosBusqueda = new ArrayList<>();
    }

    // ... (Métodos buscarProductos y obtenerProductoDeBusqueda se mantienen IGUAL) ...
    public void buscarProductos(String criterio, DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        listaProductosBusqueda.clear();
        List<Producto> productos = productoService.buscarPorNombre(criterio);
        for (Producto p : productos) {
            if (p.isActivo()) {
                listaProductosBusqueda.add(p);
                modeloTabla.addRow(new Object[]{
                    p.getIdProducto(), p.getNombre(), p.getDescripcion(),
                    String.format("%.2f", p.getPrecioVenta()), p.getStockActual()
                });
            }
        }
    }
    
    public Producto obtenerProductoDeBusqueda(int fila) {
        if (fila >= 0 && fila < listaProductosBusqueda.size()) {
            return listaProductosBusqueda.get(fila);
        }
        return null;
    }

    // --- MÉTODOS DEL CARRITO CON CÁLCULO DE PROMOCIONES ---
    public void agregarAlCarrito(Producto producto, int cantidad, DefaultTableModel modeloCarrito,
            JTextField txtSubtotal, JTextField txtDescuento, JTextField txtTotal) {

        // 1. Validaciones básicas
        if (cantidad <= 0) {
            return;
        }

        // 2. Lógica de Agregado / Actualizado en lista
        boolean existe = false;
        for (ItemVentaDTO item : carritoCompras) {
            if (item.getIdProducto() == producto.getIdProducto()) {
                item.setCantidad(item.getCantidad() + cantidad);
                existe = true;
                break;
            }
        }
        if (!existe) {
            ItemVentaDTO nuevoItem = new ItemVentaDTO();
            nuevoItem.setIdProducto(producto.getIdProducto());
            nuevoItem.setCantidad(cantidad);
            carritoCompras.add(nuevoItem);
        }

        // 3. Refrescar Tabla Visual
        refrescarTablaCarrito(modeloCarrito);

        // 4. CALCULAR TOTALES Y DESCUENTOS (Aquí está la magia)
        calcularYMostrarTotales(txtSubtotal, txtDescuento, txtTotal);
    }
    
    public void quitarDelCarrito(int fila, DefaultTableModel modelo,
            JTextField txtSubtotal, JTextField txtDescuento, JTextField txtTotal) {
        if (fila >= 0) {
            carritoCompras.remove(fila);
            refrescarTablaCarrito(modelo);
            calcularYMostrarTotales(txtSubtotal, txtDescuento, txtTotal);
        }
    }
    
    private void refrescarTablaCarrito(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (ItemVentaDTO item : carritoCompras) {
            Producto p = productoService.buscarPorId(item.getIdProducto());

            // Calculamos precio unitario con descuento para mostrar en tabla (opcional)
            double precioFinal = calcularPrecioConPromocion(p, item.getCantidad()) / item.getCantidad();
            
            modelo.addRow(new Object[]{
                p.getIdProducto(),
                p.getNombre(),
                item.getCantidad(),
                String.format("%.2f", p.getPrecioVenta()), // Precio Lista
                String.format("%.2f", precioFinal * item.getCantidad()) // Subtotal con descuento
            });
        }
    }

    // --- NUEVO MÉTODO CENTRAL DE CÁLCULO ---
    public void calcularYMostrarTotales(JTextField txtSubtotal, JTextField txtDescuento, JTextField txtTotal) {
        double subtotalBruto = 0;
        double totalNeto = 0;
        
        for (ItemVentaDTO item : carritoCompras) {
            Producto p = productoService.buscarPorId(item.getIdProducto());

            // 1. Precio sin descuento
            double precioLista = p.getPrecioVenta() * item.getCantidad();
            subtotalBruto += precioLista;

            // 2. Precio con descuento (Usando Decorators igual que el Facade)
            double precioReal = calcularPrecioConPromocion(p, item.getCantidad());
            totalNeto += precioReal;

            // Guardamos el descuento aplicado en el DTO por si se necesita al procesar
            item.setDescuentoAplicado(precioLista - precioReal);
        }
        
        double totalDescuento = subtotalBruto - totalNeto;

        // Actualizar los textfields de la interfaz
        if (txtSubtotal != null) {
            txtSubtotal.setText(String.format("%.2f", subtotalBruto));
        }
        if (txtDescuento != null) {
            txtDescuento.setText(String.format("%.2f", totalDescuento));
        }
        if (txtTotal != null) {
            txtTotal.setText(String.format("%.2f", totalNeto));
        }
    }

    // Helper que reutiliza tu lógica de Patrones (Decorator)
    private double calcularPrecioConPromocion(Producto p, int cantidad) {
        IPrecio precio = new PrecioBase(p, cantidad);

        // Buscar si hay promoción activa en la BD
        List<Promocion> promociones = promocionRepository.listarPorProductos(p.getIdProducto());
        
        if (!promociones.isEmpty()) {
            // Aplicar el decorador si existe promo (ej: ID 8 con 10 soles de descuento)
            precio = new PromocionDecorator(precio, promociones.get(0));
        }
        
        return precio.calcularPrecio();
    }

    // --- PROCESAR VENTA (Igual que antes) ---
    public boolean procesarVenta(int idCliente, int idUsuario, double descuentoGeneral,
            JTextField txtSubtotal, JTextField txtDescuento, JTextField txtTotal, DefaultTableModel modelo) {
        if (carritoCompras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El carrito está vacío.");
            return false;
        }
        
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setIdCliente(idCliente);
        ventaDTO.setIdUsuario(idUsuario);
        ventaDTO.setItems(new ArrayList<>(carritoCompras));
        ventaDTO.setDescuentoGeneral(descuentoGeneral);
        
        RegistrarVentaCommand comando = new RegistrarVentaCommand(ventaDTO);
        CommandInvoker.ejecutar(comando);
        
        if (comando.getResultado() != null && comando.getResultado().isExitoso()) {
            JOptionPane.showMessageDialog(null, "Venta Procesada! ID: " + comando.getResultado().getIdVenta());
            limpiarCarrito(modelo, txtSubtotal, txtDescuento, txtTotal);
            return true;
        } else {
            String msg = comando.getResultado() != null ? comando.getResultado().getMensaje() : "Error";
            JOptionPane.showMessageDialog(null, "Error: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void limpiarCarrito(DefaultTableModel modelo, JTextField txtSub, JTextField txtDesc, JTextField txtTot) {
        carritoCompras.clear();
        refrescarTablaCarrito(modelo);
        if (txtSub != null) {
            txtSub.setText("0.00");
        }
        if (txtDesc != null) {
            txtDesc.setText("0.00");
        }
        if (txtTot != null) {
            txtTot.setText("0.00");
        }
    }
    
    public void cargarHistorialVentas(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        // Asumimos que ventaRepository tiene listarTodos()
        List<com.mycompany.farmaciasystem.modelo.entidades.Venta> ventas = ventaRepository.listarTodos();
        
        for (Venta v : ventas) {
            // Obtener nombre cliente para mostrar (Opcional, si tienes el servicio)
            String nombreCliente = "Cliente ID " + v.getIdCliente();
            Cliente c = clienteService.buscarPorId(v.getIdCliente());
            if (c != null) {
                nombreCliente = c.getNombres() + " " + c.getApellidos();
            }
            
            Object[] fila = {
                v.getIdVenta(),
                v.getFechaVenta(),
                nombreCliente,
                String.format("S/. %.2f", v.getTotal())
            };
            modelo.addRow(fila);
        }
    }
    
    public void cargarDetallesDeVenta(int idVenta, DefaultTableModel modeloDetalle) {
        modeloDetalle.setRowCount(0);
        List<DetalleVenta> detalles = detalleRepository.listarPorVenta(idVenta);
        
        for (DetalleVenta d : detalles) {
            // Buscar nombre del producto usando el lote
            // Esto requiere una pequeña "trampa" si no tienes un join directo: 
            // Buscar Lote -> Buscar Producto. O mostrar solo ID Lote por ahora.
            // Para hacerlo bien rápido:
            String descripcion = "Lote: " + d.getIdLote();
            
            double subtotal = (d.getCantidad() * d.getPrecioUnitario()) - d.getDescuentoAplicado();
            
            Object[] fila = {
                d.getIdLote(),
                d.getCantidad(),
                String.format("%.2f", d.getPrecioUnitario()),
                String.format("%.2f", d.getDescuentoAplicado()),
                String.format("%.2f", subtotal)
            };
            modeloDetalle.addRow(fila);
        }
    }
    
    public Venta buscarVentaPorId(int id) {
        return ventaRepository.buscarPorID(id);
    }
    
    public void cargarDatosCabeceraVenta(int idVenta,
            JTextField txtId,
            JTextField txtFecha,
            JTextField txtCliente,
            JTextField txtDni,
            JTextField txtTotal
    ) {

        // 1. Buscar la Venta
        Venta venta = ventaRepository.buscarPorID(idVenta);
        
        if (venta != null) {
            // Llenar datos básicos
            if (txtId != null) {
                txtId.setText(String.valueOf(venta.getIdVenta()));
            }
            if (txtFecha != null) {
                txtFecha.setText(venta.getFechaVenta().toString());
            }
            if (txtTotal != null) {
                txtTotal.setText(String.format("%.2f", venta.getTotal()));
            }

            // 2. Buscar y Llenar Cliente
            Cliente cliente = clienteService.buscarPorId(venta.getIdCliente());
            if (cliente != null && txtCliente != null) {
                txtCliente.setText(cliente.getNombres() + " " + cliente.getApellidos());
                txtDni.setText(cliente.getDni());
            } else if (txtCliente != null) {
                txtCliente.setText("Cliente General / No Encontrado");
            }
            
        }
    }
    
}
