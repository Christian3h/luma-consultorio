package vista.components;

import java.awt.Color;
import javax.swing.JButton;
import vista.usuario.citasPanel;

/**
 * @author christian
 */
public class menuOdont extends javax.swing.JPanel {

    /**
     * Creates new form menu
     */
    public menuOdont() {
        initComponents();
    }

    // Función reutilizable para el evento MouseEntered
    public void cambiarColorEntrada(JButton boton) {
        boton.setBackground(new Color(83, 115, 112));
        boton.setForeground(Color.white);
    }

    // Función reutilizable para el evento MouseExited
    public void cambiarColorSalida(JButton boton) {
        boton.setBackground(new Color(143, 166, 143));
        boton.setForeground(new Color(242, 242, 242));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSobreOdont = new javax.swing.JButton();
        btnCitas = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(143, 166, 143));
        jPanel1.setForeground(new java.awt.Color(242, 242, 242));
        jPanel1.setToolTipText("");
        jPanel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(36, 38, 37));
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 38, 37));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/path1.png"))); // NOI18N

        btnSobreOdont.setBackground(new java.awt.Color(143, 166, 143));
        btnSobreOdont.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnSobreOdont.setForeground(new java.awt.Color(242, 242, 242));
        btnSobreOdont.setText("Sobre la cuenta");
        btnSobreOdont.setBorder(null);
        btnSobreOdont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSobreOdontMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSobreOdontMouseExited(evt);
            }
        });
        btnSobreOdont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSobreOdontActionPerformed(evt);
            }
        });

        btnCitas.setBackground(new java.awt.Color(143, 166, 143));
        btnCitas.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnCitas.setForeground(new java.awt.Color(242, 242, 242));
        btnCitas.setText("Citas");
        btnCitas.setBorder(null);
        btnCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCitasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCitasMouseExited(evt);
            }
        });
        btnCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCitasActionPerformed(evt);
            }
        });

        btnCerrarSesion.setBackground(new java.awt.Color(143, 166, 143));
        btnCerrarSesion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(242, 242, 242));
        btnCerrarSesion.setText("Cerrar sesion");
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseExited(evt);
            }
        });
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSobreOdont, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSobreOdont, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(350, 350, 350)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCitasActionPerformed

    }//GEN-LAST:event_btnCitasActionPerformed

    private void btnSobreOdontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSobreOdontActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSobreOdontActionPerformed

    private void btnSobreOdontMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSobreOdontMouseEntered
        cambiarColorEntrada(btnSobreOdont);
    }//GEN-LAST:event_btnSobreOdontMouseEntered

    private void btnSobreOdontMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSobreOdontMouseExited
        cambiarColorSalida(btnSobreOdont);
    }//GEN-LAST:event_btnSobreOdontMouseExited

    private void btnCitasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCitasMouseEntered
        cambiarColorEntrada(btnCitas);
    }//GEN-LAST:event_btnCitasMouseEntered

    private void btnCitasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCitasMouseExited
        cambiarColorSalida(btnCitas);
    }//GEN-LAST:event_btnCitasMouseExited

    private void btnCerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseEntered
        cambiarColorEntrada(btnCerrarSesion);
    }//GEN-LAST:event_btnCerrarSesionMouseEntered

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited
        cambiarColorSalida(btnCerrarSesion);
    }//GEN-LAST:event_btnCerrarSesionMouseExited

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public void setBtnCerrarSesion(JButton btnCerrarSesion) {
        this.btnCerrarSesion = btnCerrarSesion;
    }

    public JButton getBtnSobreOdont() {
        return btnSobreOdont;
    }

    public void setBtnSobreOdont(JButton btnSobreOdont) {
        this.btnSobreOdont = btnSobreOdont;
    }

    public JButton getBtnCitas() {
        return btnCitas;
    }

    public void setBtnCitas(JButton btnCitas) {
        this.btnCitas = btnCitas;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCitas;
    private javax.swing.JButton btnSobreOdont;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
