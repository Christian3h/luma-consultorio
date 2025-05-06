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

        txtPaciente = new javax.swing.JTextField();
        txtOdontologo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtFecha = new com.github.lgooddatepicker.components.DatePicker();
        btnGuardar = new javax.swing.JButton();
        txtHora = new javax.swing.JTextField();
        consultarPacientes = new javax.swing.JButton();
        consultarOdontologo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 450));
        setMinimumSize(new java.awt.Dimension(700, 450));

        txtPaciente.setText(" ");

        txtOdontologo.setActionCommand("<Not Set>");
        txtOdontologo.setToolTipText("aqui va texto");
        txtOdontologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOdontologoActionPerformed(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        btnGuardar.setText("Guardar");

        txtHora.setText("jTextField1");

        consultarPacientes.setText("seleccionar paciente");
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
        consultarOdontologo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consultarOdontologoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addComponent(btnGuardar)
                .addContainerGap(313, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(198, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(txtOdontologo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(consultarPacientes)
                            .addComponent(consultarOdontologo))))
                .addGap(214, 214, 214))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consultarPacientes))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOdontologo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consultarOdontologo))
                .addGap(18, 18, 18)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnGuardar)
                .addGap(64, 64, 64))
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDescripcion;
    private com.github.lgooddatepicker.components.DatePicker txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtOdontologo;
    private javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}
