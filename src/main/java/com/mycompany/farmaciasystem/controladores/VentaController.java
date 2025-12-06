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
import com.mycompany.farmaciasystem.modelo.entidades.Usuario;
import com.mycompany.farmaciasystem.modelo.entidades.Venta;
import com.mycompany.farmaciasystem.repository.Implementaciones.PromocionRepositoryImpl;
import com.mycompany.farmaciasystem.repository.Interfaces.IPromocionRepository;
import com.mycompany.farmaciasystem.servicios.ProductoService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Frank
 */
public class VentaController {

    private final ProductoService productoService;
    private final IPromocionRepository promocionRepository;
    private final List<ItemVentaDTO> carritoCompras;
    private final List<Producto> listaProductosBusqueda;

    // Repositorios y servicios  para reportes
    private final com.mycompany.farmaciasystem.repository.Interfaces.IVentaRepository ventaRepository = new com.mycompany.farmaciasystem.repository.Implementaciones.VentaRepositoryImpl();
    private final com.mycompany.farmaciasystem.repository.Interfaces.IDetalleVentaRepository detalleRepository = new com.mycompany.farmaciasystem.repository.Implementaciones.DetalleVentaRepositoryImpl();
    private final com.mycompany.farmaciasystem.servicios.ClienteService clienteService = new com.mycompany.farmaciasystem.servicios.ClienteService();
    private final com.mycompany.farmaciasystem.servicios.UsuarioService usuarioService = new com.mycompany.farmaciasystem.servicios.UsuarioService();

    public VentaController() {
        this.productoService = new ProductoService();
        this.promocionRepository = new PromocionRepositoryImpl();
        this.carritoCompras = new ArrayList<>();
        this.listaProductosBusqueda = new ArrayList<>();
    }

    // Buscar productos para llenar la tabla superior
    public void buscarProductos(String criterio, DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        listaProductosBusqueda.clear();

        List<Producto> productos = productoService.buscarPorNombre(criterio);

        for (Producto p : productos) {
            // Solo listar si está activo
            if (p.isActivo()) {
                listaProductosBusqueda.add(p);
                modeloTabla.addRow(new Object[]{
                    p.getIdProducto(),
                    p.getNombre(),
                    p.getDescripcion(),
                    String.format("%.2f", p.getPrecioVenta()),
                    p.getStockActual()
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

    // Agregar un producto a la lista temporal (carrito)
    public void agregarAlCarrito(Producto producto, int cantidad, DefaultTableModel modeloCarrito,
            JTextField txtSubtotal, JTextField txtDescuento, JTextField txtTotal) {

        if (cantidad <= 0) {
            return;
        }

        // Verificar si ya existe para sumar la cantidad
        boolean existe = false;
        for (ItemVentaDTO item : carritoCompras) {
            if (item.getIdProducto() == producto.getIdProducto()) {
                item.setCantidad(item.getCantidad() + cantidad);
                existe = true;
                break;
            }
        }
        // Si no existe, se crea
        if (!existe) {
            ItemVentaDTO nuevoItem = new ItemVentaDTO();
            nuevoItem.setIdProducto(producto.getIdProducto());
            nuevoItem.setCantidad(cantidad);
            carritoCompras.add(nuevoItem);
        }

        refrescarTablaCarrito(modeloCarrito);
        calcularYMostrarTotales(txtSubtotal, txtDescuento, txtTotal);
    }

    // Elimina un item seleccionado del carrito
    public void quitarDelCarrito(int fila, DefaultTableModel modelo,
            JTextField txtSubtotal, JTextField txtDescuento, JTextField txtTotal) {
        if (fila >= 0) {
            carritoCompras.remove(fila);
            refrescarTablaCarrito(modelo);
            calcularYMostrarTotales(txtSubtotal, txtDescuento, txtTotal);
        }
    }

    // Actualiza visualmente la tabla del carrito
    private void refrescarTablaCarrito(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (ItemVentaDTO item : carritoCompras) {
            Producto p = productoService.buscarPorId(item.getIdProducto());

            double precioFinal = calcularPrecioConPromocion(p, item.getCantidad()) / item.getCantidad();

            modelo.addRow(new Object[]{
                p.getIdProducto(),
                p.getNombre(),
                item.getCantidad(),
                String.format("%.2f", p.getPrecioVenta()),
                String.format("%.2f", precioFinal * item.getCantidad())
            });
        }
    }

    // Calcula los totales y aplica descuentos si hay promociones activas
    public void calcularYMostrarTotales(JTextField txtSubtotal, JTextField txtDescuento, JTextField txtTotal) {
        double subtotalBruto = 0;
        double totalNeto = 0;

        for (ItemVentaDTO item : carritoCompras) {
            Producto p = productoService.buscarPorId(item.getIdProducto());

            double precioLista = p.getPrecioVenta() * item.getCantidad();
            subtotalBruto += precioLista;

            double precioReal = calcularPrecioConPromocion(p, item.getCantidad());
            totalNeto += precioReal;

            item.setDescuentoAplicado(precioLista - precioReal);
        }

        double totalDescuento = subtotalBruto - totalNeto;

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

    // Verifica en base de datos si hay descuento para el producto
    private double calcularPrecioConPromocion(Producto p, int cantidad) {
        IPrecio precio = new PrecioBase(p, cantidad);

        List<Promocion> promociones = promocionRepository.listarPorProductos(p.getIdProducto());

        if (!promociones.isEmpty()) {
            precio = new PromocionDecorator(precio, promociones.get(0));
        }

        return precio.calcularPrecio();
    }

    // Envía la venta para ser procesada y guardada
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
            JOptionPane.showMessageDialog(null, "Venta registrada! ID: " + comando.getResultado().getIdVenta());
            limpiarCarrito(modelo, txtSubtotal, txtDescuento, txtTotal);
            return true;
        } else {
            String msg = comando.getResultado() != null ? comando.getResultado().getMensaje() : "Error desconocido";
            JOptionPane.showMessageDialog(null, "No se pudo registrar: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Reinicia el carrito visual y la lista
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

    // Carga todas las ventas en la tabla de historial
    public void cargarHistorialVentas(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        List<Venta> ventas = ventaRepository.listarTodos();

        for (Venta v : ventas) {
            String nombreCliente = "ID " + v.getIdCliente();
            Cliente c = clienteService.buscarPorId(v.getIdCliente());
            if (c != null) {
                nombreCliente = c.getNombres() + " " + c.getApellidos();
            }

            modelo.addRow(new Object[]{
                v.getIdVenta(),
                v.getFechaVenta(),
                nombreCliente,
                String.format("S/. %.2f", v.getTotal())
            });
        }
    }

    // Carga los productos de una venta específica
    public void cargarDetallesDeVenta(int idVenta, DefaultTableModel modeloDetalle) {
        modeloDetalle.setRowCount(0);
        List<DetalleVenta> detalles = detalleRepository.listarPorVenta(idVenta);

        for (DetalleVenta d : detalles) {
            String descripcion = "Lote: " + d.getIdLote();
            double subtotal = (d.getCantidad() * d.getPrecioUnitario()) - d.getDescuentoAplicado();

            modeloDetalle.addRow(new Object[]{
                d.getIdLote(),
                d.getCantidad(),
                String.format("%.2f", d.getPrecioUnitario()),
                String.format("%.2f", d.getDescuentoAplicado()),
                String.format("%.2f", subtotal)
            });
        }
    }

    // Llena los datos de cabecera en la ventana de detalle
    public void cargarDatosCabeceraVenta(int idVenta,
            JTextField txtId, JTextField txtFecha,
            JTextField txtCliente, JTextField txtTotal) {

        Venta venta = ventaRepository.buscarPorID(idVenta);

        if (venta != null) {
            if (txtId != null) {
                txtId.setText(String.valueOf(venta.getIdVenta()));
            }
            if (txtFecha != null) {
                txtFecha.setText(venta.getFechaVenta().toString());
            }
            if (txtTotal != null) {
                txtTotal.setText(String.format("%.2f", venta.getTotal()));
            }

            Cliente cliente = clienteService.buscarPorId(venta.getIdCliente());
            if (cliente != null && txtCliente != null) {
                txtCliente.setText(cliente.getNombres() + " " + cliente.getApellidos());
            }
        }
    }

    public Venta buscarVentaPorId(int id) {
        return ventaRepository.buscarPorID(id);
    }
}
