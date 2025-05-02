/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.usuario;

import controlador.CitasControlador;
import javax.swing.JOptionPane;
import modelo.Citas;
import modelo.CitasJson;
import modelo.PersonaJson;

/**
 *
 * @author christian
 */
public class CitasEditar extends javax.swing.JFrame {

    private String idCita;
    private PersonaJson modeloPersona;
    private CitasJson modeloCita;
    private CitasControlador controlador;

    public CitasEditar() {
        initComponents();
    }

    public CitasEditar(java.awt.Frame parent, boolean modal, String id, String paciente, String odontologo, String fecha, String hora, String descripcion, PersonaJson personaModel, CitasJson citaModel, CitasControlador ctrl) {
       
        this.idCita = id;
        this.modeloPersona = personaModel;
        this.modeloCita = citaModel;
        this.controlador = ctrl;
        initComponents(); 
        cargarDatosCita(paciente, odontologo, fecha, hora, descripcion);
        setLocationRelativeTo(parent);
    }

    private void cargarDatosCita(String paciente, String odontologo, String fecha, String hora, String descripcion) {
        txtPaciente.setText(paciente);
        txtOdontologo.setText(odontologo);
        txtFecha.setText(fecha);
        txtHora.setText(hora);
        txtDescripcion.setText(descripcion);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPaciente = new javax.swing.JTextField();
        txtOdontologo = new javax.swing.JTextField();
        txtHora = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtFecha = new com.github.lgooddatepicker.components.DatePicker();
        consultarPacientes = new javax.swing.JButton();
        consultarOdontologo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGuardar.setText("Guardar");
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
        });

        jLabel1.setText("Paciente");

        jLabel2.setText("Odontólogo");

        jLabel3.setText("Fecha");

        jLabel4.setText("Hora");

        jLabel5.setText("Descripción");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(137, 137, 137)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3))
                                    .addGap(172, 172, 172)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(155, 155, 155)
                                .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(consultarPacientes))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(137, 137, 137)
                                .addComponent(txtOdontologo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(consultarOdontologo))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(btnGuardar)))
                .addGap(94, 94, 94))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(consultarPacientes)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOdontologo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(consultarOdontologo)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
  String nuevoPaciente = txtPaciente.getText().trim();
    String nuevoOdontologo = txtOdontologo.getText().trim();
    String nuevaFecha = txtFecha.getText().trim();
    String nuevaHora = txtHora.getText().trim();
    String nuevaDescripcion = txtDescripcion.getText().trim();

    // Validaciones
    if (nuevoPaciente.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El nombre del paciente no puede estar vacío.");
        return;
    }

    if (nuevoOdontologo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El nombre del odontólogo no puede estar vacío.");
        return;
    }

    if (nuevaFecha.isEmpty()) {
        JOptionPane.showMessageDialog(this, "La fecha no puede estar vacía.");
        return;
    }

    if (!nuevaFecha.matches("^\\d{1,2} de [a-zA-Z]+ de \\d{4}$")) {
        JOptionPane.showMessageDialog(this, "La fecha debe tener el formato: '1 de mayo de 2025'.");
        return;
    }

    if (nuevaHora.isEmpty()) {
        JOptionPane.showMessageDialog(this, "La hora no puede estar vacía.");
        return;
    }

    if (!nuevaHora.matches("^\\d{2}:\\d{2}$")) {
        JOptionPane.showMessageDialog(this, "La hora debe tener el formato HH:mm, por ejemplo: 14:30.");
        return;
    }

    if (nuevaDescripcion.isEmpty()) {
        JOptionPane.showMessageDialog(this, "La descripción no puede estar vacía.");
        return;
    }

    String nuevaFechaHora = nuevaFecha + " " + nuevaHora;
    Citas citaEditada = new Citas(nuevoPaciente, nuevoOdontologo, nuevaFechaHora, nuevaDescripcion);

    if (modeloCita.editarCita(idCita, citaEditada)) {
        JOptionPane.showMessageDialog(this, "Cita actualizada correctamente.");
        controlador.cargarCitas(modeloCita.obtenerTodasLasCitas());
        dispose(); // cerrar ventana
    } else {
        JOptionPane.showMessageDialog(this, "Error al actualizar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void consultarPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarPacientesMouseClicked

        seleccionarParsona dialog = new seleccionarParsona(this, true, "paciente"); //paciente o dontolog
        dialog.setVisible(true);

        String pacienteSeleccionado = dialog.getDatoSeleccionado();
        if (pacienteSeleccionado != null) {
            txtPaciente.setText(pacienteSeleccionado);
        }
    }//GEN-LAST:event_consultarPacientesMouseClicked

    private void consultarPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarPacientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consultarPacientesActionPerformed

    private void consultarOdontologoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarOdontologoMouseClicked

        seleccionarParsona dialog = new seleccionarParsona(this, true, "odontologo"); //paciente o dontolog
        dialog.setVisible(true);

        String pacienteSeleccionado = dialog.getDatoSeleccionado();
        if (pacienteSeleccionado != null) {
            txtOdontologo.setText(pacienteSeleccionado);
        }

    }//GEN-LAST:event_consultarOdontologoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton consultarOdontologo;
    private javax.swing.JButton consultarPacientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDescripcion;
    private com.github.lgooddatepicker.components.DatePicker txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtOdontologo;
    private javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}
