/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.usuario;

import com.github.lgooddatepicker.components.DatePicker;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modelo.PersonaJson;

/**
 *
 * @author christian
 */
public class EditarCitaFrame extends javax.swing.JFrame {

    public EditarCitaFrame() {
        initComponents();
        txtHora.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String texto = txtHora.getText();
                if (!texto.matches("^\\d{0,2}:?\\d{0,2}$")) {
                    txtHora.setText(texto.replaceAll("[^\\d:]", ""));
                }

                // Auto-insertar ":" después de 2 dígitos
                if (texto.length() == 2 && !texto.contains(":")) {
                    txtHora.setText(texto + ":");
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        txtPaciente = new javax.swing.JTextField();
        txtOdontologo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtFecha = new com.github.lgooddatepicker.components.DatePicker();
        btnGuardar = new javax.swing.JButton();
        txtHora = new javax.swing.JTextField();
        consultarPacientes = new javax.swing.JButton();
        consultarOdontologo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(242, 242, 242));
        setMaximumSize(new java.awt.Dimension(489, 501));
        setMinimumSize(new java.awt.Dimension(489, 501));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        txtPaciente.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtPaciente.setText(" ");
        txtPaciente.setBackground(new java.awt.Color(242, 242, 242));
        txtPaciente.setBorder(null);
        txtPaciente.setForeground(new java.awt.Color(60, 63, 65));
        txtPaciente.setSelectedTextColor(new java.awt.Color(242, 242, 242));
        txtPaciente.setSelectionColor(new java.awt.Color(83, 115, 112));

        txtOdontologo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtOdontologo.setActionCommand("<Not Set>");
        txtOdontologo.setBackground(new java.awt.Color(242, 242, 242));
        txtOdontologo.setBorder(null);
        txtOdontologo.setForeground(new java.awt.Color(60, 63, 65));
        txtOdontologo.setSelectedTextColor(new java.awt.Color(242, 242, 242));
        txtOdontologo.setSelectionColor(new java.awt.Color(83, 115, 112));
        txtOdontologo.setToolTipText("aqui va texto");
        txtOdontologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOdontologoActionPerformed(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtDescripcion.setRows(5);
        txtDescripcion.setBackground(new java.awt.Color(242, 242, 242));
        txtDescripcion.setForeground(new java.awt.Color(60, 63, 65));
        txtDescripcion.setSelectedTextColor(new java.awt.Color(242, 242, 242));
        txtDescripcion.setSelectionColor(new java.awt.Color(83, 115, 112));
        jScrollPane1.setViewportView(txtDescripcion);

        btnGuardar.setText("Guardar");
        btnGuardar.setBackground(new java.awt.Color(83, 115, 112));
        btnGuardar.setBorder(null);
        btnGuardar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(242, 242, 242));

        txtHora.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtHora.setBackground(new java.awt.Color(242, 242, 242));
        txtHora.setBorder(null);
        txtHora.setForeground(new java.awt.Color(60, 63, 65));
        txtHora.setSelectedTextColor(new java.awt.Color(242, 242, 242));
        txtHora.setSelectionColor(new java.awt.Color(83, 115, 112));

        consultarPacientes.setText("seleccionar paciente");
        consultarPacientes.setBackground(new java.awt.Color(83, 115, 112));
        consultarPacientes.setBorder(null);
        consultarPacientes.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        consultarPacientes.setForeground(new java.awt.Color(242, 242, 242));
        consultarPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consultarPacientesMouseClicked(evt);
            }
        });
        consultarPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarPacientesActionPerformed(evt);
            }
        });

        consultarOdontologo.setText("seleccinar Ocontologos");
        consultarOdontologo.setBackground(new java.awt.Color(83, 115, 112));
        consultarOdontologo.setBorder(null);
        consultarOdontologo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        consultarOdontologo.setForeground(new java.awt.Color(242, 242, 242));
        consultarOdontologo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consultarOdontologoMouseClicked(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.setBackground(new java.awt.Color(83, 115, 112));
        jButton1.setBorder(null);
        jButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(242, 242, 242));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(consultarPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtOdontologo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(consultarOdontologo, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(consultarPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtOdontologo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(consultarOdontologo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultarPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarPacientesMouseClicked

        DialogBuscarPersona dialog = new DialogBuscarPersona(
                this,
                true,
                "paciente",
                getTxtPaciente()
        );
        dialog.setVisible(true);
    }//GEN-LAST:event_consultarPacientesMouseClicked

    private void consultarPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarPacientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consultarPacientesActionPerformed

    private void consultarOdontologoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarOdontologoMouseClicked

        DialogBuscarPersona dialog = new DialogBuscarPersona(
                this,
                true,
                "odontologo",
                getTxtOdontologo()
        );
        dialog.setVisible(true);
    }//GEN-LAST:event_consultarOdontologoMouseClicked

    private void txtOdontologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOdontologoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOdontologoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JTextArea getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(JTextArea txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public DatePicker getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(DatePicker txtFecha) {
        this.txtFecha = txtFecha;
    }

    public JTextField getTxtOdontologo() {
        return txtOdontologo;
    }

    public void setTxtOdontologo(JTextField txtOdontologo) {
        this.txtOdontologo = txtOdontologo;
    }

    public JTextField getTxtPaciente() {
        return txtPaciente;
    }

    public void setTxtPaciente(JTextField txtPaciente) {
        this.txtPaciente = txtPaciente;
    }

    public JTextField getTxtHora() {
        return txtHora;
    }

    public void setTxtHora(JTextField txtHora) {
        this.txtHora = txtHora;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton consultarOdontologo;
    private javax.swing.JButton consultarPacientes;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextArea txtDescripcion;
    private com.github.lgooddatepicker.components.DatePicker txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtOdontologo;
    private javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}
