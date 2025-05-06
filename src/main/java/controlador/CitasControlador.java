package controlador;

import modelo.Citas;
import modelo.CitasJson;
import modelo.PersonaJson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import vista.usuario.citasCrear;

public class CitasControlador implements ActionListener {

    private citasCrear vistaCita;
    private CitasJson modeloCita;
    private PersonaJson modeloPersona;

    public CitasControlador(citasCrear vista, PersonaJson modeloPersona) {
        this.vistaCita = vista;
        this.modeloPersona = modeloPersona;
        this.modeloCita = new CitasJson(modeloPersona);
        this.vistaCita.getButtonCrear().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCita.getButtonCrear()) {
            String documentoPaciente = vistaCita.getTxtPaciente().getText();
            String documentoOdontologo = vistaCita.getTxtOdontologo().getText();
            String fechaCita = vistaCita.getTxtFecha().getText();
            String horaCitaTexto = vistaCita.getTxtHora().getText();
            String descripcionCita = vistaCita.getTxtDescripcion().getText();

            if (documentoPaciente.isEmpty() || documentoOdontologo.isEmpty() || 
                fechaCita == null || horaCitaTexto.isEmpty() || descripcionCita.isEmpty()) {
                JOptionPane.showMessageDialog(vistaCita, "Todos los campos son obligatorios.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalTime horaFormateada;
            try {
                horaFormateada = LocalTime.parse(horaCitaTexto, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vistaCita, "Formato de hora incorrecto (HH:mm).", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String fechaHoraCita = fechaCita.toString() + " " + horaFormateada.toString();

            Citas nuevaCita = new Citas(documentoPaciente, documentoOdontologo, 
                fechaHoraCita, descripcionCita);

            if (modeloCita.agregarCita(nuevaCita)) {
                vistaCita.getTxtPaciente().setText("");
                vistaCita.getTxtOdontologo().setText("");
                vistaCita.getTxtFecha().setText("");
                vistaCita.getTxtHora().setText("");
                vistaCita.getTxtDescripcion().setText("");
            }
        }
    }
}