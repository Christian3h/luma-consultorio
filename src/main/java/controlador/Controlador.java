package controlador;

import com.google.gson.Gson;
import javax.swing.*;
import java.awt.event.*;
import vista.login;
import vista.odontologo.odontologCrearOdontologo;
import vista.usuario.usuarioCrearPaciente;
import modelo.Odontologo;
import modelo.PersonaJson;
import org.json.JSONObject;

public class Controlador implements ActionListener {
    private login vistaLogin;
    private usuarioCrearPaciente vistaUsuario;
    private odontologCrearOdontologo vistaOdontologo;
    private PersonaJson personaModel;
    private final Gson gson = new Gson();

    public Controlador() {
        this.personaModel = new PersonaJson();
        this.vistaLogin = new login();
        this.vistaUsuario = new usuarioCrearPaciente();
        this.vistaOdontologo = new odontologCrearOdontologo();
        
        configurarActionListeners();
    }

    public void iniciar() {
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setVisible(true);
    }

    private void configurarActionListeners() {
        vistaLogin.getButtonUser().addActionListener(this);
        vistaOdontologo.getBtnGuardar().addActionListener(this);
        //vistaUsuario.getBtnGuardar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.getButtonUser()) {
            validarLogin();
        } else if (e.getSource() == vistaOdontologo.getBtnGuardar()) {
            crearOdontologo();
        }
//         else if (e.getSource() == vistaUsuario.getBtnGuardar()) {
//            crearPaciente();
//        }
    }

    private void validarLogin() {
        String user = vistaLogin.getNameUser().getText();
        char[] pass = vistaLogin.getPasswordUser().getPassword();
        String rol = personaModel.validarUsuario(user, new String(pass)).toLowerCase();

        switch (rol) {
            case "odontologo":
                vistaOdontologo.setLocationRelativeTo(null);
                vistaOdontologo.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            case "usuario":
                vistaUsuario.setLocationRelativeTo(null);
                vistaUsuario.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            default:
                JOptionPane.showMessageDialog(vistaLogin,
                        "Credenciales incorrectas",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearOdontologo() {
        try {
            if (validarCamposOdontologo()) {
                Odontologo odontologo = crearObjetoOdontologo();
                JSONObject jsonOdontologo = convertirAJsonObject(odontologo);
                System.out.println("JSON Odontologo a guardar: " + jsonOdontologo.toString(2)); // El '2' es para una impresión formateada
                boolean guardado = personaModel.crearPersona(jsonOdontologo, "odontologo");
                
                if (guardado) {
                    mostrarMensajeExito("Odontólogo guardado correctamente", vistaOdontologo);
                    limpiarFormularioOdontologo();
                } else {
                    mostrarMensajeError("Error al guardar el odontólogo", vistaOdontologo);
                }
            }
        } catch (Exception ex) {
            manejarError(ex, vistaOdontologo);
        }
    }

    private void crearPaciente() {
        try {
            if (validarCamposPaciente()) {
                // Implementa lógica similar a crearOdontologo() para pacientes
                // usando vistaUsuario en lugar de vistaOdontologo
            }
        } catch (Exception ex) {
            manejarError(ex, vistaUsuario);
        }
    }

    private boolean validarCamposOdontologo() {
        if (vistaOdontologo.getTxtNombre().getText().isEmpty() ||
            vistaOdontologo.getTxtApellido().getText().isEmpty() ||
            vistaOdontologo.getTxtIdentificacion().getText().isEmpty() ||
            vistaOdontologo.getTxtDireccion().getText().isEmpty() ||
            vistaOdontologo.getTxtTelefono().getText().isEmpty() ||
            vistaOdontologo.getTxtCorreo().getText().isEmpty() ||
            vistaOdontologo.getTxtPassword().getPassword().length == 0) {
            
            mostrarMensajeError("Todos los campos son obligatorios", vistaOdontologo);
            return false;
        }
        return true;
    }

    private boolean validarCamposPaciente() {
        // Implementa validación similar para paciente usando vistaUsuario
        return true;
    }

    private Odontologo crearObjetoOdontologo() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre(vistaOdontologo.getTxtNombre().getText());
        odontologo.setApellidos(vistaOdontologo.getTxtApellido().getText());
        odontologo.setCedula(vistaOdontologo.getTxtIdentificacion().getText());
        odontologo.setFechaNacimiento(vistaOdontologo.getTxtFechaNacimiento().getDate().toString());
        odontologo.setDireccion(vistaOdontologo.getTxtDireccion().getText());
        odontologo.setTelefono(vistaOdontologo.getTxtTelefono().getText());
        odontologo.setCorreo(vistaOdontologo.getTxtCorreo().getText());
        odontologo.setPassword(new String(vistaOdontologo.getTxtPassword().getPassword()));
        
        return odontologo;
    }

    private JSONObject convertirAJsonObject(Object objeto) {
        return new JSONObject(gson.toJson(objeto));
    }

    private void limpiarFormularioOdontologo() {
        vistaOdontologo.getTxtNombre().setText("");
        vistaOdontologo.getTxtApellido().setText("");
        vistaOdontologo.getTxtIdentificacion().setText("");
        vistaOdontologo.getTxtFechaNacimiento().setDate(null);
        vistaOdontologo.getTxtDireccion().setText("");
        vistaOdontologo.getTxtTelefono().setText("");
        vistaOdontologo.getTxtCorreo().setText("");
        vistaOdontologo.getTxtPassword().setText("");
    }

    private void mostrarMensajeExito(String mensaje, JFrame parent) {
        JOptionPane.showMessageDialog(parent, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarMensajeError(String mensaje, JFrame parent) {
        JOptionPane.showMessageDialog(parent, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void manejarError(Exception ex, JFrame parent) {
        JOptionPane.showMessageDialog(parent, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}