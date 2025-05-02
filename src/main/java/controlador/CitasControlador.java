package controlador;

import modelo.Citas;
import modelo.CitasJson;
import modelo.PersonaJson;
import org.json.JSONArray;
import org.json.JSONObject;
import vista.usuario.citasCrear;
import vista.usuario.citasGestionar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.usuario.CitasEditar;

public class CitasControlador implements ActionListener {

    private citasCrear vistaCrear;
    private citasGestionar vistaMostrar;
    private CitasJson modeloCita;
    private PersonaJson modeloPersona;

    // Constructor solo para crear citas
    public CitasControlador(citasCrear vistaCrear, PersonaJson modeloPersona) {
        this(vistaCrear, null, modeloPersona);
    }

    // Constructor solo para gestionar citas
    public CitasControlador(citasGestionar vistaMostrar, PersonaJson modeloPersona) {
        this(null, vistaMostrar, modeloPersona);
    }

    // Constructor completo
    public CitasControlador(citasCrear vistaCrear, citasGestionar vistaMostrar, PersonaJson modeloPersona) {
        this.vistaCrear = vistaCrear;
        this.vistaMostrar = vistaMostrar;
        this.modeloPersona = modeloPersona;
        this.modeloCita = new CitasJson(modeloPersona);

        if (vistaCrear != null) {
            this.vistaCrear.getButtonCrear().addActionListener(this);
        }

        if (vistaMostrar != null) {
            cargarCitas(modeloCita.obtenerTodasLasCitas());
            configurarBusquedaEnTiempoReal();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vistaCrear != null && e.getSource() == vistaCrear.getButtonCrear()) {
            String documentoPaciente = vistaCrear.getTxtPaciente().getText();
            String documentoOdontologo = vistaCrear.getTxtOdontologo().getText();
            String fechaCita = vistaCrear.getTxtFecha().getText();
            String horaCita = vistaCrear.getTxtHora().getText();
            String descripcion = vistaCrear.getTxtDescripcion().getText();

            if (documentoPaciente.isEmpty() || documentoOdontologo.isEmpty()
                    || fechaCita.isEmpty() || horaCita.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(vistaCrear, "Todos los campos son obligatorios.");
                return;
            }

            String fechaHora = fechaCita + " " + horaCita;

            Citas nuevaCita = new Citas(documentoPaciente, documentoOdontologo, fechaHora, descripcion);
            if (modeloCita.agregarCita(nuevaCita)) {
                limpiarFormulario();
                if (vistaMostrar != null) {
                    cargarCitas(modeloCita.obtenerTodasLasCitas());
                }
            }
        }
    }

    public void cargarCitas(JSONArray citas) {
        JPanel panel = vistaMostrar.getPanelCitas();
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240)); // Color de fondo gris claro para el panel

        Font fuenteBoton = new Font("Arial", Font.BOLD, 14);
        Dimension tamañoBoton = new Dimension(100, 30);
        Color colorTextoBoton = Color.WHITE;
        Color colorFondoEliminar = new Color(220, 53, 69); // Rojo suave
        Color colorFondoEditar = new Color(0, 123, 255);   // Azul

        for (int i = 0; i < citas.length(); i++) {
            JSONObject cita = citas.getJSONObject(i);
            String id = cita.optString("id");
            String descripcion = cita.optString("descripcion", "");
            String fecha = cita.optString("fecha", "");
            String docPaciente = cita.optString("documentoPaciente", "");
            String docOdontologo = cita.optString("documentoOdontologo", "");

            JSONObject paciente = modeloPersona.buscarPorCedula(docPaciente);
            JSONObject odontologo = modeloPersona.buscarPorCedula(docOdontologo);

            String nombrePaciente = paciente.optString("nombres", "") + " " + paciente.optString("apellidos", "");
            String nombreOdontologo = odontologo.optString("nombres", "") + " " + odontologo.optString("apellidos", "");

            JPanel tarjeta = new JPanel(new GridLayout(0, 1));
            tarjeta.setBorder(BorderFactory.createTitledBorder("Cita"));
            tarjeta.add(new JLabel("Paciente: " + nombrePaciente + " - " + docPaciente));
            tarjeta.add(new JLabel("Odontólogo: " + nombreOdontologo + " - " + docOdontologo));
            tarjeta.add(new JLabel("Fecha: " + fecha));
            tarjeta.add(new JLabel("Descripción: " + descripcion));

            JButton eliminarBtn = new JButton("Eliminar");
            eliminarBtn.setFont(fuenteBoton);
            eliminarBtn.setPreferredSize(tamañoBoton);
            eliminarBtn.setBackground(colorFondoEliminar);
            eliminarBtn.setForeground(colorTextoBoton);
            eliminarBtn.addActionListener(ev -> {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar esta cita?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    modeloCita.eliminarCita(id);
                    cargarCitas(modeloCita.obtenerTodasLasCitas());
                }
            });

            JButton editarBtn = new JButton("Editar");
            editarBtn.setFont(fuenteBoton);
            editarBtn.setPreferredSize(tamañoBoton);
            editarBtn.setBackground(colorFondoEditar);
            editarBtn.setForeground(colorTextoBoton);
            editarBtn.addActionListener(ev -> {

                String hora = "";
                int ultimaPos = fecha.lastIndexOf(" ");

                if (ultimaPos > 0 && ultimaPos < fecha.length() - 1) {
                    String fechaSinHora = fecha.substring(0, ultimaPos);
                    hora = fecha.substring(ultimaPos + 1);

                    CitasEditar editor = new CitasEditar(vistaCrear, true, id, docPaciente, docOdontologo, fechaSinHora, hora, descripcion, modeloPersona, modeloCita, this);
                    editor.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se pudo separar la fecha y la hora.");
                }

            });

            JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Panel para alinear los botones a la derecha
            botonesPanel.add(eliminarBtn);
            botonesPanel.add(editarBtn);
            tarjeta.add(botonesPanel);

            panel.add(tarjeta);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void limpiarFormulario() {
        vistaCrear.getTxtPaciente().setText("");
        vistaCrear.getTxtOdontologo().setText("");
        vistaCrear.getTxtFecha().setText("");
        vistaCrear.getTxtHora().setText("");
        vistaCrear.getTxtDescripcion().setText("");
    }

    private void configurarBusquedaEnTiempoReal() {
        vistaMostrar.getTxtBuscar().getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }

            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            private void buscar() {
                String texto = vistaMostrar.getTxtBuscar().getText();
                JSONArray resultados = modeloCita.buscarCitasPorTexto(texto);
                cargarCitas(resultados);
            }
        });
    }
}
