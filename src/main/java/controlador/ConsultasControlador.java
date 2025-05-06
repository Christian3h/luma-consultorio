package controlador;

import modelo.PersonaJson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ConsultasJson;
import vista.Odontologo.Consulta;

public class ConsultasControlador implements ActionListener {

    private Consulta vistaconsulta;
    private ConsultasJson modeloconsulta;
    private PersonaJson modeloPersona;

    public ConsultasControlador(Consulta vista, PersonaJson modeloPersona) {
        this.vistaconsulta = vista;
        this.modeloPersona = modeloPersona;
        this.modeloconsulta = new ConsultasJson(modeloPersona);
        configurarBotones();
    }

    private void configurarBotones() {
        // Elimina listeners existentes y añade este controlador
        vistaconsulta.getBtnFinalizar().removeActionListener(this);
        vistaconsulta.getBtnFinalizar().addActionListener(this);
    }

    public void inicarVentana() {
        vistaconsulta.setLocationRelativeTo(null);
        vistaconsulta.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaconsulta.getBtnFinalizar()) {
            guardarConsulta();
        }
    }

    private void guardarConsulta() {
        System.out.println("Botón Finalizar presionado - Inicio del proceso");

        String procedimiento = vistaconsulta.getTxtProcedimiento().getText().trim();
        String medicamentos = vistaconsulta.getTxtMedicamentos().getText().trim();
        String valor = vistaconsulta.getTxtValor().getText().trim();
        String documentoPaciente = vistaconsulta.getDocumentoPaciente(); // Asume que tienes este método en Consulta
        String documentoOdontologo = vistaconsulta.getDocumentoOdontologo(); // Asume que tienes este método en Consulta

        // Validar campos obligatorios
        if (procedimiento.isEmpty() || valor.isEmpty()) {
            JOptionPane.showMessageDialog(vistaconsulta,
                    "Procedimiento y Valor son campos obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Validar que el valor sea numérico
            Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaconsulta,
                    "El valor debe ser un número válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean resultado = modeloconsulta.guardarConsulta(procedimiento, medicamentos, valor, documentoPaciente, documentoOdontologo);
        System.out.println("Resultado del guardado: " + resultado);

        if (resultado) {
            JOptionPane.showMessageDialog(vistaconsulta, "✅ Consulta guardada exitosamente");
            vistaconsulta.dispose(); // Cierra la ventana
        } else {
            JOptionPane.showMessageDialog(vistaconsulta,
                    "❌ Error al guardar la consulta",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
