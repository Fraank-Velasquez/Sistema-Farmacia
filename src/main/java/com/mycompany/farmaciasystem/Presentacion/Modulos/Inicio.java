/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.farmaciasystem.Presentacion.Modulos;

import com.mycompany.farmaciasystem.controladores.DashboardController;
import com.mycompany.farmaciasystem.controladores.ProductoController;
import com.mycompany.farmaciasystem.modelo.entidades.Producto;
import com.mycompany.farmaciasystem.observer.NotificacionDashboardObserver;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Frank
 */
public class Inicio extends javax.swing.JPanel {

    private final DashboardController dashboardController;
    private final ProductoController productoController;
    private NotificacionDashboardObserver notificacionObserver;

    /**
     * Creates new form NewJPanel
     */
    public Inicio() {
        initComponents();
        this.dashboardController = new DashboardController();
        this.productoController = new ProductoController();
        configurarTablas();
        cargarDashboard();
    }

    private void configurarTablas() {
        // tabla de productos con bajo stock
        DefaultTableModel modeloBajoStock = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre", "Stock Actual", "Stock Mínimo", "Estado"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaBajoStock.setModel(modeloBajoStock);

        //tabla de productos más vendidos
        DefaultTableModel modeloMasVendidos = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Producto", "Cantidad Vendida", "Total Ventas"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaMasVendidos.setModel(modeloMasVendidos);
    }

    private void cargarDashboard() {
        cargarEstadisticas();
        cargarProductosBajoStock();
        cargarProductosMasVendidos();
        verificarAlertas();
    }

    private void cargarEstadisticas() {
        Map<String, Object> estadisticas = dashboardController.obtenerEstadisticasDashboard();

        lblTotalProductos.setText(String.valueOf(estadisticas.get("totalProductos")));
        lblProductosBajoStock.setText(String.valueOf(estadisticas.get("productosBajoStock")));
        lblVentasDelDia.setText(String.valueOf(estadisticas.get("ventasDelDia")));
        lblTotalClientes.setText(String.valueOf(estadisticas.get("totalClientes")));
        lblTotalProveedores.setText(String.valueOf(estadisticas.get("totalProveedores")));
        lblLotesVencidos.setText(String.valueOf(estadisticas.get("lotesVencidos")));
    }

    private void cargarProductosBajoStock() {
        DefaultTableModel modelo = (DefaultTableModel) tablaBajoStock.getModel();
        modelo.setRowCount(0);

        List<Producto> productosBajoStock = productoController.obtenerProductosBajoStock();

        for (Producto producto : productosBajoStock) {
            Object[] fila = {
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getStockActual(),
                producto.getStockMinimo(),
                "ALERTA"
            };
            modelo.addRow(fila);
        }

        if (productosBajoStock.isEmpty()) {
            Object[] fila = {"No hay ", "productos", "bajos", "en stock", ""};
            modelo.addRow(fila);
        }
    }

    private void cargarProductosMasVendidos() {
        DefaultTableModel modelo = (DefaultTableModel) tablaMasVendidos.getModel();
        modelo.setRowCount(0);

        // productos de ejemplo
        Object[] fila1 = {"Paracetamol 500mg", "120", "S/. 360.00"};
        Object[] fila2 = {"Ibuprofeno 400mg", "95", "S/. 475.00"};
        Object[] fila3 = {"Amoxicilina 500mg", "80", "S/. 640.00"};

        modelo.addRow(fila1);
        modelo.addRow(fila2);
        modelo.addRow(fila3);
    }

    private void verificarAlertas() {
        notificacionObserver = dashboardController.verificarAlertas();

        if (notificacionObserver.hayAlertas()) {
            mostrarNotificacionAlertas();
        }
    }

    private void mostrarNotificacionAlertas() {
        List<String> notificaciones = notificacionObserver.obtenerNotificaciones();

        if (!notificaciones.isEmpty()) {
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("ALERTAS DEL SISTEMA:\n\n");

            int contador = 1;
            for (String notif : notificaciones) {
                mensaje.append(contador).append(". ").append(notif).append("\n");
                contador++;
            }

            JOptionPane.showMessageDialog(this,
                    mensaje.toString(),
                    "Alertas del Sistema",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblProdBajS = new javax.swing.JButton();
        lblProductosMasVendidos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMasVendidos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaBajoStock = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblTotalProductosl = new javax.swing.JLabel();
        lblProductosBajoStockl = new javax.swing.JLabel();
        lblLotesVencidosl = new javax.swing.JLabel();
        lblTotalClientesl = new javax.swing.JLabel();
        lblTotalProveedoresl = new javax.swing.JLabel();
        lblVentasDelDial = new javax.swing.JLabel();
        lblTotalProductos = new javax.swing.JLabel();
        lblProductosBajoStock = new javax.swing.JLabel();
        lblLotesVencidos = new javax.swing.JLabel();
        lblVentasDelDia = new javax.swing.JLabel();
        lblTotalClientes = new javax.swing.JLabel();
        lblTotalProveedores = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(232, 232, 245));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setBackground(new java.awt.Color(17, 53, 87));
        jButton1.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Actualizar datos");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblProdBajS.setBackground(new java.awt.Color(255, 255, 255));
        lblProdBajS.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        lblProdBajS.setForeground(new java.awt.Color(51, 51, 51));
        lblProdBajS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources_img/bajoStock.png"))); // NOI18N
        lblProdBajS.setText("Productos con bajo stock !!");
        lblProdBajS.setBorder(null);
        lblProdBajS.setIconTextGap(15);
        lblProdBajS.setMargin(new java.awt.Insets(10, 14, 10, 14));
        lblProdBajS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProdBajSMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblProdBajSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblProdBajSMouseExited(evt);
            }
        });
        lblProdBajS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblProdBajSActionPerformed(evt);
            }
        });

        lblProductosMasVendidos.setBackground(new java.awt.Color(255, 255, 255));
        lblProductosMasVendidos.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        lblProductosMasVendidos.setForeground(new java.awt.Color(51, 51, 51));
        lblProductosMasVendidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources_img/masVendidos.png"))); // NOI18N
        lblProductosMasVendidos.setText("Productos más vendidos");
        lblProductosMasVendidos.setBorder(null);
        lblProductosMasVendidos.setIconTextGap(15);
        lblProductosMasVendidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProductosMasVendidosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblProductosMasVendidosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblProductosMasVendidosMouseExited(evt);
            }
        });
        lblProductosMasVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblProductosMasVendidosActionPerformed(evt);
            }
        });

        tablaMasVendidos.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        tablaMasVendidos.setForeground(new java.awt.Color(51, 51, 51));
        tablaMasVendidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaMasVendidos);

        tablaBajoStock.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        tablaBajoStock.setForeground(new java.awt.Color(51, 51, 51));
        tablaBajoStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaBajoStock);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lblProdBajS, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProductosMasVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProdBajS)
                    .addComponent(lblProductosMasVendidos))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalProductosl.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblTotalProductosl.setForeground(new java.awt.Color(29, 82, 109));
        lblTotalProductosl.setText("Total de Productos:");

        lblProductosBajoStockl.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblProductosBajoStockl.setForeground(new java.awt.Color(29, 82, 109));
        lblProductosBajoStockl.setText("Bajo Stock:");

        lblLotesVencidosl.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblLotesVencidosl.setForeground(new java.awt.Color(29, 82, 109));
        lblLotesVencidosl.setText("Lotes Vencidos:");

        lblTotalClientesl.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblTotalClientesl.setForeground(new java.awt.Color(29, 82, 109));
        lblTotalClientesl.setText("Clientes:");

        lblTotalProveedoresl.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblTotalProveedoresl.setForeground(new java.awt.Color(29, 82, 109));
        lblTotalProveedoresl.setText("Proveedores:");

        lblVentasDelDial.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblVentasDelDial.setForeground(new java.awt.Color(29, 82, 109));
        lblVentasDelDial.setText("Ventas de Hoy: ");

        lblTotalProductos.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblTotalProductos.setForeground(new java.awt.Color(51, 51, 51));

        lblProductosBajoStock.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblProductosBajoStock.setForeground(new java.awt.Color(51, 51, 51));

        lblLotesVencidos.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblLotesVencidos.setForeground(new java.awt.Color(51, 51, 51));

        lblVentasDelDia.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblVentasDelDia.setForeground(new java.awt.Color(51, 51, 51));

        lblTotalClientes.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblTotalClientes.setForeground(new java.awt.Color(51, 51, 51));

        lblTotalProveedores.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        lblTotalProveedores.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotalProductosl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLotesVencidosl, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(lblProductosBajoStockl, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductosBajoStock, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLotesVencidos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblTotalClientesl, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblTotalProveedoresl, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblVentasDelDial, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVentasDelDia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalProductosl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVentasDelDial, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVentasDelDia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductosBajoStockl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalClientesl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductosBajoStock, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLotesVencidosl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalProveedoresl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLotesVencidos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblProdBajSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProdBajSMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblProdBajSMouseClicked

    private void lblProdBajSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProdBajSMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblProdBajSMouseEntered

    private void lblProdBajSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProdBajSMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblProdBajSMouseExited

    private void lblProdBajSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblProdBajSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblProdBajSActionPerformed

    private void lblProductosMasVendidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductosMasVendidosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblProductosMasVendidosMouseClicked

    private void lblProductosMasVendidosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductosMasVendidosMouseEntered

    }//GEN-LAST:event_lblProductosMasVendidosMouseEntered

    private void lblProductosMasVendidosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductosMasVendidosMouseExited

    }//GEN-LAST:event_lblProductosMasVendidosMouseExited

    private void lblProductosMasVendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblProductosMasVendidosActionPerformed

    }//GEN-LAST:event_lblProductosMasVendidosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        cargarDashboard();
        JOptionPane.showMessageDialog(this,
                "Dashboard actualizado correctamente",
                "Actualización Exitosa",
                JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblLotesVencidos;
    private javax.swing.JLabel lblLotesVencidosl;
    private javax.swing.JButton lblProdBajS;
    private javax.swing.JLabel lblProductosBajoStock;
    private javax.swing.JLabel lblProductosBajoStockl;
    private javax.swing.JButton lblProductosMasVendidos;
    private javax.swing.JLabel lblTotalClientes;
    private javax.swing.JLabel lblTotalClientesl;
    private javax.swing.JLabel lblTotalProductos;
    private javax.swing.JLabel lblTotalProductosl;
    private javax.swing.JLabel lblTotalProveedores;
    private javax.swing.JLabel lblTotalProveedoresl;
    private javax.swing.JLabel lblVentasDelDia;
    private javax.swing.JLabel lblVentasDelDial;
    private javax.swing.JTable tablaBajoStock;
    private javax.swing.JTable tablaMasVendidos;
    // End of variables declaration//GEN-END:variables
}
