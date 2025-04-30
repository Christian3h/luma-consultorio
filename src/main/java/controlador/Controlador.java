package controlador;

import com.google.gson.Gson;
import javax.swing.*;
import java.awt.event.*;
import vista.login;
import vista.odontologo.odontologCrearOdontologo;
import vista.usuario.usuarioCrearPaciente;
import vista.usuario.usuarioCrearUsuario;
import modelo.Odontologo;
import modelo.Paciente;
import modelo.Usuario;
import modelo.PersonaJson;
import org.json.JSONObject;

public class Controlador implements ActionListener {

    private login vistaLogin;
    private usuarioCrearPaciente vistaUsuarioPaciente;
    private usuarioCrearUsuario vistaUsuarioCrear;
    private odontologCrearOdontologo vistaOdontologo;
    private PersonaJson personaModel;
    private final Gson gson = new Gson();

    public Controlador() {
        this.personaModel = new PersonaJson();
        this.vistaLogin = new login();
        this.vistaUsuarioPaciente = new usuarioCrearPaciente();
        this.vistaUsuarioCrear = new usuarioCrearUsuario(); // Inicializa la vista de creación de usuarios
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
        vistaUsuarioPaciente.getBtnGuardar().addActionListener(this);
        vistaUsuarioPaciente.getjButton1().addActionListener(this);
        vistaUsuarioCrear.getBtnGuardar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.getButtonUser()) {
            validarLogin();
        } else if (e.getSource() == vistaOdontologo.getBtnGuardar()) {
            crearOdontologo();
        } else if (e.getSource() == vistaUsuarioPaciente.getBtnGuardar()) {
            crearPaciente();
        } else if (e.getSource() == vistaUsuarioCrear.getBtnGuardar()) {
            crearUsuario();
        } else if (e.getSource() == vistaUsuarioPaciente.getjButton1()) {
            vistaUsuarioCrear.setLocationRelativeTo(vistaUsuarioPaciente); // Centrar la ventana de creación relativa a la ventana del paciente
            vistaUsuarioCrear.setVisible(true);
        }
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
                vistaUsuarioPaciente.setLocationRelativeTo(null);
                vistaUsuarioPaciente.setVisible(true);
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
        String correo = vistaOdontologo.getTxtCorreo().getText();
        String identificacion = vistaOdontologo.getTxtIdentificacion().getText();

        if (personaModel.existeCorreo(correo)) {
            mostrarMensajeError("El correo electrónico ya está registrado.", vistaOdontologo);
            return;
        }

        if (personaModel.existeIdentificacion(identificacion)) {
            mostrarMensajeError("La cédula ya está registrada.", vistaOdontologo);
            return;
        }

        try {
            if (validarCamposOdontologo()) {
                Odontologo odontologo = crearObjetoOdontologo();
                JSONObject jsonOdontologo = convertirAJsonObject(odontologo);

                System.out.println("JSON Odontologo a guardar: " + jsonOdontologo.toString(2));
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

    private void crearUsuario() {
        String correo = vistaUsuarioCrear.getTxtCorreo().getText();
        String identificacion = vistaUsuarioCrear.getTxtIdentificacion().getText();

        if (personaModel.existeCorreo(correo)) {
            mostrarMensajeError("El correo electrónico ya está registrado.", vistaUsuarioCrear);
            return;
        }

        if (personaModel.existeIdentificacion(identificacion)) {
            mostrarMensajeError("La cédula ya está registrada.", vistaUsuarioCrear);
            return;
        }

        try {
            if (validarCamposUsuario()) {
                Usuario usuario = crearObjetoUsuario();
                JSONObject jsonUsuario = convertirAJsonObject(usuario);

                System.out.println("JSON usuario a guardar: " + jsonUsuario.toString(2));
                boolean guardado = personaModel.crearPersona(jsonUsuario, "usuario");

                if (guardado) {
                    mostrarMensajeExito("Usuario guardado correctamente", vistaUsuarioCrear);
                    limpiarFormularioUsuario();
                } else {
                    mostrarMensajeError("Error al guardar el usuario", vistaUsuarioCrear);
                }
            }
        } catch (Exception ex) {
            manejarError(ex, vistaUsuarioCrear);
        }
    }

    private void crearPaciente() {
        String correo = vistaUsuarioPaciente.getTxtCorreo().getText();
        String identificacion = vistaUsuarioPaciente.getTxtIdentificacion().getText();

        if (personaModel.existeCorreo(correo)) {
            mostrarMensajeError("El correo electrónico ya está registrado.", vistaUsuarioPaciente);
            return;
        }

        if (personaModel.existeIdentificacion(identificacion)) {
            mostrarMensajeError("La cédula ya está registrada.", vistaUsuarioPaciente);
            return;
        }

        try {
            if (validarCamposPaciente()) {
                Paciente paciente = crearObjetoPaciente();
                JSONObject jsonPaciente = convertirAJsonObject(paciente);

                System.out.println("JSON Paciente a guardar: " + jsonPaciente.toString(2));
                boolean guardado = personaModel.crearPersona(jsonPaciente, "paciente");

                if (guardado) {
                    mostrarMensajeExito("Paciente guardado correctamente", vistaUsuarioPaciente);
                    limpiarFormularioPaciente();
                } else {
                    mostrarMensajeError("Error al guardar el paciente", vistaUsuarioPaciente);
                }
            }
        } catch (Exception ex) {
            manejarError(ex, vistaUsuarioPaciente);
        }
    }

    private boolean validarCamposOdontologo() {
        if (vistaOdontologo.getTxtNombre().getText().isEmpty()
                || vistaOdontologo.getTxtApellido().getText().isEmpty()
                || vistaOdontologo.getTxtIdentificacion().getText().isEmpty()
                || (vistaOdontologo.getTxtFechaNacimiento() != null && vistaOdontologo.getTxtFechaNacimiento().getDate() == null)
                || vistaOdontologo.getTxtDireccion().getText().isEmpty()
                || vistaOdontologo.getTxtTelefono().getText().isEmpty()
                || vistaOdontologo.getTxtCorreo().getText().isEmpty()
                || vistaOdontologo.getTxtPassword().getPassword().length == 0) {

            mostrarMensajeError("Todos los campos son obligatorios", vistaOdontologo);
            return false;
        }
        return true;
    }

    private boolean validarCamposUsuario() {
        if (vistaUsuarioCrear.getTxtNombre().getText().isEmpty()
                || vistaUsuarioCrear.getTxtApellido().getText().isEmpty()
                || vistaUsuarioCrear.getTxtIdentificacion().getText().isEmpty()
                || (vistaUsuarioCrear.getTxtFechaNacimiento() != null && vistaUsuarioCrear.getTxtFechaNacimiento().getDate() == null)
                || vistaUsuarioCrear.getTxtDireccion().getText().isEmpty()
                || vistaUsuarioCrear.getTxtTelefono().getText().isEmpty()
                || vistaUsuarioCrear.getTxtCorreo().getText().isEmpty()
                || vistaUsuarioCrear.getTxtPassword().getPassword().length == 0) {

            mostrarMensajeError("Todos los campos son obligatorios", vistaUsuarioCrear);
            return false;
        }
        return true;
    }

    private boolean validarCamposPaciente() {
        if (vistaUsuarioPaciente.getTxtNombre().getText().isEmpty()
                || vistaUsuarioPaciente.getTxtApellido().getText().isEmpty()
                || vistaUsuarioPaciente.getTxtIdentificacion().getText().isEmpty()
                || (vistaUsuarioPaciente.getTxtFechaNacimiento() != null && vistaUsuarioPaciente.getTxtFechaNacimiento().getDate() == null)
                || vistaUsuarioPaciente.getTxtDireccion().getText().isEmpty()
                || vistaUsuarioPaciente.getTxtTelefono().getText().isEmpty()
                || vistaUsuarioPaciente.getTxtCorreo().getText().isEmpty()) {

            mostrarMensajeError("Todos los campos son obligatorios", vistaUsuarioPaciente);
            return false;
        }
        return true;
    }

    private Odontologo crearObjetoOdontologo() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombres(vistaOdontologo.getTxtNombre().getText());
        odontologo.setApellidos(vistaOdontologo.getTxtApellido().getText());
        odontologo.setCedula(vistaOdontologo.getTxtIdentificacion().getText());
        if (vistaOdontologo.getTxtFechaNacimiento() != null && vistaOdontologo.getTxtFechaNacimiento().getDate() != null) {
            odontologo.setFechaNacimiento(vistaOdontologo.getTxtFechaNacimiento().getDate().toString());
        }
        odontologo.setDireccion(vistaOdontologo.getTxtDireccion().getText());
        odontologo.setTelefono(vistaOdontologo.getTxtTelefono().getText());
        odontologo.setCorreo(vistaOdontologo.getTxtCorreo().getText());
        odontologo.setPassword(new String(vistaOdontologo.getTxtPassword().getPassword()));
        odontologo.setRol("odontologo");

        return odontologo;
    }

    private Usuario crearObjetoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombres(vistaUsuarioCrear.getTxtNombre().getText());
        usuario.setApellidos(vistaUsuarioCrear.getTxtApellido().getText());
        usuario.setCedula(vistaUsuarioCrear.getTxtIdentificacion().getText());
        if (vistaUsuarioCrear.getTxtFechaNacimiento() != null && vistaUsuarioCrear.getTxtFechaNacimiento().getDate() != null) {
            usuario.setFechaNacimiento(vistaUsuarioCrear.getTxtFechaNacimiento().getDate().toString());
        }
        usuario.setDireccion(vistaUsuarioCrear.getTxtDireccion().getText());
        usuario.setTelefono(vistaUsuarioCrear.getTxtTelefono().getText());
        usuario.setCorreo(vistaUsuarioCrear.getTxtCorreo().getText());
        usuario.setPassword(new String(vistaUsuarioCrear.getTxtPassword().getPassword()));
        usuario.setRol("usuario");

        return usuario;
    }

    private Paciente crearObjetoPaciente() {
        Paciente paciente = new Paciente();
        paciente.setNombres(vistaUsuarioPaciente.getTxtNombre().getText());
        paciente.setApellidos(vistaUsuarioPaciente.getTxtApellido().getText());
        paciente.setCedula(vistaUsuarioPaciente.getTxtIdentificacion().getText());
        if (vistaUsuarioPaciente.getTxtFechaNacimiento() != null && vistaUsuarioPaciente.getTxtFechaNacimiento().getDate() != null) {
            paciente.setFechaNacimiento(vistaUsuarioPaciente.getTxtFechaNacimiento().getDate().toString());
        }
        paciente.setDireccion(vistaUsuarioPaciente.getTxtDireccion().getText());
        paciente.setTelefono(vistaUsuarioPaciente.getTxtTelefono().getText());
        paciente.setCorreo(vistaUsuarioPaciente.getTxtCorreo().getText());
        paciente.setRol("paciente");

        return paciente;
    }

    private JSONObject convertirAJsonObject(Object objeto) {
        return new JSONObject(gson.toJson(objeto));
    }

    private void limpiarFormularioOdontologo() {
        vistaOdontologo.getTxtNombre().setText("");
        vistaOdontologo.getTxtApellido().setText("");
        vistaOdontologo.getTxtIdentificacion().setText("");
        if (vistaOdontologo.getTxtFechaNacimiento() != null) {
            vistaOdontologo.getTxtFechaNacimiento().setDate(null);
        }
        vistaOdontologo.getTxtDireccion().setText("");
        vistaOdontologo.getTxtTelefono().setText("");
        vistaOdontologo.getTxtCorreo().setText("");
        vistaOdontologo.getTxtPassword().setText("");
    }

    private void limpiarFormularioUsuario() {
        vistaUsuarioCrear.getTxtNombre().setText("");
        vistaUsuarioCrear.getTxtApellido().setText("");
        vistaUsuarioCrear.getTxtIdentificacion().setText("");
        if (vistaUsuarioCrear.getTxtFechaNacimiento() != null) {
            vistaUsuarioCrear.getTxtFechaNacimiento().setDate(null);
        }
        vistaUsuarioCrear.getTxtDireccion().setText("");
        vistaUsuarioCrear.getTxtTelefono().setText("");
        vistaUsuarioCrear.getTxtCorreo().setText("");
        vistaUsuarioCrear.getTxtPassword().setText("");
    }

    private void limpiarFormularioPaciente() {
        vistaUsuarioPaciente.getTxtNombre().setText("");
        vistaUsuarioPaciente.getTxtApellido().setText("");
        vistaUsuarioPaciente.getTxtIdentificacion().setText("");
        if (vistaUsuarioPaciente.getTxtFechaNacimiento() != null) {
            vistaUsuarioPaciente.getTxtFechaNacimiento().setDate(null);
        }
        vistaUsuarioPaciente.getTxtDireccion().setText("");
        vistaUsuarioPaciente.getTxtTelefono().setText("");
        vistaUsuarioPaciente.getTxtCorreo().setText("");
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