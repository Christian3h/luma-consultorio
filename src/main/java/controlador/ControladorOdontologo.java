package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import org.json.JSONObject;
import vista.components.menuOdont;
import vista.Odontologo.InfoOdontologo;
import modelo.PersonaJson;
import javax.swing.JOptionPane;
import vista.usuario.citasPanel;

public class ControladorOdontologo implements ActionListener {

    private menuOdont vista;
    private InfoOdontologo vistaInfo;
    private PersonaJson personaModel;

    public ControladorOdontologo(menuOdont vistaMenu) {
        this.vista = vistaMenu;
        this.personaModel = new PersonaJson();
        this.vistaInfo = new InfoOdontologo();
        this.vistaInfo.getBtnActualizar().addActionListener(this);
        this.vistaInfo.getBtnEliminar().addActionListener(this);
    }

    public void citasConsulta() {
            
    }
    
    public void mostrarVistaInfo() {
        String idStr = Sesion.getInstancia().getIdUsuario();
        int id = Integer.parseInt(idStr);
        JSONObject persona = personaModel.consultarPersonaPorId(id);
        if (persona != null) {
            vistaInfo.getTxtNombre().setText(persona.optString("nombres", ""));
            vistaInfo.getTxtApellido().setText(persona.optString("apellidos", ""));
            vistaInfo.getTxtCorreo().setText(persona.optString("correo", ""));
            vistaInfo.getTxtDireccion().setText(persona.optString("direccion", ""));
            vistaInfo.getTxtIdentificacion().setText(persona.optString("cedula", ""));
            vistaInfo.getTxtTelefono().setText(persona.optString("telefono", ""));
            vistaInfo.getTxtPassword().setText(persona.optString("password", "")); // Mostrar password en texto plano
            vistaInfo.setPacienteId(id);
        } else {
            System.err.println("No se encontró persona con id " + id);
        }
        vistaInfo.setLocationRelativeTo(null);
        vistaInfo.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaInfo.getBtnActualizar()) {
            actualizarOdontologo();
        } else if (e.getSource() == vistaInfo.getBtnEliminar()) {
            eliminarOdontologo();
        }
    }

    private void actualizarOdontologo() {
        int id = vistaInfo.getPacienteId();
        JSONObject nuevosDatos = new JSONObject();
        nuevosDatos.put("nombres", vistaInfo.getTxtNombre().getText());
        nuevosDatos.put("apellidos", vistaInfo.getTxtApellido().getText());
        nuevosDatos.put("correo", vistaInfo.getTxtCorreo().getText());
        nuevosDatos.put("direccion", vistaInfo.getTxtDireccion().getText());
        nuevosDatos.put("cedula", vistaInfo.getTxtIdentificacion().getText());
        nuevosDatos.put("telefono", vistaInfo.getTxtTelefono().getText());
        nuevosDatos.put("password", vistaInfo.getTxtPassword().getText()); // Password en texto plano

        JSONObject original = personaModel.consultarPersonaPorId(id);

        if (validarCamposObligatorios(nuevosDatos, "nombres", "apellidos", "cedula", "correo", "telefono", "direccion", "password")
                && validarCambios(original, nuevosDatos)) {

            if (personaModel.actualizarPersona(id, nuevosDatos)) {
                JOptionPane.showMessageDialog(vistaInfo, "Información actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vistaInfo, "Error al actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarOdontologo() {
        int id = vistaInfo.getPacienteId();
        int confirmacion = JOptionPane.showConfirmDialog(vistaInfo, "¿Está seguro de eliminar este odontólogo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (personaModel.eliminarPersona(id)) {
                JOptionPane.showMessageDialog(vistaInfo, "Odontólogo eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                vistaInfo.dispose();
                LoginControlador objC = new LoginControlador();
                objC.iniciar();
            } else {
                JOptionPane.showMessageDialog(vistaInfo, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validarCambios(JSONObject original, JSONObject actualizado) {
        // Validación de campos obligatorios
        if (actualizado.optString("nombres").isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
            return false;
        }

        if (!Pattern.matches("\\d+", actualizado.optString("cedula"))) {
            JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
            return false;
        }

        if (actualizado.optString("password").isEmpty()) {
            JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía.");
            return false;
        }

        if (!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$", actualizado.optString("correo"))) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo válido.");
            return false;
        }

        return true;
    }

    private boolean validarCamposObligatorios(JSONObject datos, String... campos) {
        for (String campo : campos) {
            if (!datos.has(campo) || datos.optString(campo).trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo '" + campo + "' es obligatorio.");
                return false;
            }
        }
        return true;
    }
}
