package controlador;

import com.google.gson.Gson;
import javax.swing.*;
import java.awt.event.*;
import vista.usuario.odontologCrearOdontologo;
import vista.usuario.usuarioCrearPaciente;
import vista.usuario.usuarioCrearUsuario;
import modelo.Odontologo;
import modelo.Paciente;
import modelo.Usuario;
import modelo.PersonaJson;
import org.json.JSONObject;
import vista.components.menu;
import vista.usuario.citasCrear;

public class Controlador implements ActionListener {

    private citasCrear vistaCitas; // Nueva vista

    private usuarioCrearPaciente vistaUsuarioPaciente;
    private usuarioCrearUsuario vistaUsuarioCrear;
    private odontologCrearOdontologo vistaOdontologo;
    private PersonaJson personaModel;
    private final Gson gson = new Gson();
    private menu vistaMenu;
    private MenuControlador controladorMenu;
    private CitasControlador citasControlador;

    public Controlador() {
        this.personaModel = new PersonaJson();
        this.vistaUsuarioPaciente = new usuarioCrearPaciente(this);
        this.vistaUsuarioCrear = new usuarioCrearUsuario(this);
        this.vistaOdontologo = new odontologCrearOdontologo(this);
        configurarActionListeners();
        // mostrarMenuInicial();
    }

    private void configurarActionListeners() {
        vistaOdontologo.getBtnGuardar().addActionListener(this);
        vistaUsuarioPaciente.getBtnGuardar().addActionListener(this);
        vistaUsuarioCrear.getBtnGuardar().addActionListener(this);
    }

    public void iniciarUsuario() {
        vistaUsuarioPaciente.setLocationRelativeTo(null);
        vistaUsuarioPaciente.setVisible(true);
    }

    public void iniciarUsuarioCrear() {
        vistaUsuarioCrear.setLocationRelativeTo(null);
        vistaUsuarioCrear.setVisible(true);
    }

    public void iniciarCitas() {
        vistaCitas = new citasCrear();
        citasControlador = new CitasControlador(vistaCitas, personaModel);
        vistaCitas.setLocationRelativeTo(null);
        vistaCitas.setVisible(true);
    }

    // ventanas para el odontologo
    public void iniciarOdontologoCrear() {
        vistaOdontologo.setLocationRelativeTo(null);
        vistaOdontologo.setVisible(true);
        String id = Sesion.getInstancia().getIdUsuario();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaOdontologo.getBtnGuardar()) {
            crearOdontologo();
        } else if (e.getSource() == vistaUsuarioPaciente.getBtnGuardar()) {
            crearPaciente();
        } else if (e.getSource() == vistaUsuarioCrear.getBtnGuardar()) {
            crearUsuario();
        }
    }

    private void mostrarMenuInicial() {
        vistaMenu = new menu();
        controladorMenu = new MenuControlador(vistaMenu, personaModel, "usuario", this, null);
        vistaMenu.setVisible(true);
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
                boolean guardado = personaModel.crearPersona(jsonOdontologo, "odontologo");

                if (guardado) {
                    mostrarMensajeExito("Odontólogo guardado correctamente", vistaOdontologo);
                    limpiarFormularioOdontologo();
                } else {
                    mostrarMensajeError("Error al guardar el odontólogo", vistaOdontologo);
                }
            } else {
                mostrarMensajeError("Todos los campos son obligatorios", vistaOdontologo);
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
                boolean guardado = personaModel.crearPersona(jsonUsuario, "usuario");

                if (guardado) {
                    mostrarMensajeExito("Usuario guardado correctamente", vistaUsuarioCrear);
                    limpiarFormularioUsuario();
                } else {
                    mostrarMensajeError("Error al guardar el usuario", vistaUsuarioCrear);
                }
            } else {
                mostrarMensajeError("Todos los campos son obligatorios", vistaUsuarioCrear);
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
                boolean guardado = personaModel.crearPersona(jsonPaciente, "paciente");

                if (guardado) {
                    mostrarMensajeExito("Paciente guardado correctamente", vistaUsuarioPaciente);
                    limpiarFormularioPaciente();
                } else {
                    mostrarMensajeError("Error al guardar el paciente", vistaUsuarioPaciente);
                }
            } else {
                mostrarMensajeError("Todos los campos son obligatorios", vistaUsuarioCrear);
            }
        } catch (Exception ex) {
            manejarError(ex, vistaUsuarioPaciente);
        }
    }

    private boolean validarCamposOdontologo() {
        return !vistaOdontologo.getTxtNombre().getText().isEmpty()
                && !vistaOdontologo.getTxtApellido().getText().isEmpty()
                && !vistaOdontologo.getTxtIdentificacion().getText().isEmpty()
                && (vistaOdontologo.getTxtFechaNacimiento() != null && vistaOdontologo.getTxtFechaNacimiento().getDate() != null)
                && !vistaOdontologo.getTxtDireccion().getText().isEmpty()
                && !vistaOdontologo.getTxtTelefono().getText().isEmpty()
                && !vistaOdontologo.getTxtCorreo().getText().isEmpty()
                && vistaOdontologo.getTxtPassword().getPassword().length > 0;
    }

    private boolean validarCamposUsuario() {
        return !vistaUsuarioCrear.getTxtNombre().getText().isEmpty()
                && !vistaUsuarioCrear.getTxtApellido().getText().isEmpty()
                && !vistaUsuarioCrear.getTxtIdentificacion().getText().isEmpty()
                && (vistaUsuarioCrear.getTxtFechaNacimiento() != null && vistaUsuarioCrear.getTxtFechaNacimiento().getDate() != null)
                && !vistaUsuarioCrear.getTxtDireccion().getText().isEmpty()
                && !vistaUsuarioCrear.getTxtTelefono().getText().isEmpty()
                && !vistaUsuarioCrear.getTxtCorreo().getText().isEmpty()
                && vistaUsuarioCrear.getTxtPassword().getPassword().length > 0;
    }

    private boolean validarCamposPaciente() {
        return !vistaUsuarioPaciente.getTxtNombre().getText().isEmpty()
                && !vistaUsuarioPaciente.getTxtApellido().getText().isEmpty()
                && !vistaUsuarioPaciente.getTxtIdentificacion().getText().isEmpty()
                && (vistaUsuarioPaciente.getTxtFechaNacimiento() != null && vistaUsuarioPaciente.getTxtFechaNacimiento().getDate() != null)
                && !vistaUsuarioPaciente.getTxtDireccion().getText().isEmpty()
                && !vistaUsuarioPaciente.getTxtTelefono().getText().isEmpty()
                && !vistaUsuarioPaciente.getTxtCorreo().getText().isEmpty();
    }

    private Odontologo crearObjetoOdontologo() {
        Odontologo o = new Odontologo();
        o.setNombres(vistaOdontologo.getTxtNombre().getText());
        o.setApellidos(vistaOdontologo.getTxtApellido().getText());
        o.setCedula(vistaOdontologo.getTxtIdentificacion().getText());
        o.setFechaNacimiento(vistaOdontologo.getTxtFechaNacimiento().getDate().toString());
        o.setDireccion(vistaOdontologo.getTxtDireccion().getText());
        o.setTelefono(vistaOdontologo.getTxtTelefono().getText());
        o.setCorreo(vistaOdontologo.getTxtCorreo().getText());
        o.setPassword(new String(vistaOdontologo.getTxtPassword().getPassword()));
        o.setRol("odontologo");
        return o;
    }

    private Usuario crearObjetoUsuario() {
        Usuario u = new Usuario();
        u.setNombres(vistaUsuarioCrear.getTxtNombre().getText());
        u.setApellidos(vistaUsuarioCrear.getTxtApellido().getText());
        u.setCedula(vistaUsuarioCrear.getTxtIdentificacion().getText());
        u.setFechaNacimiento(vistaUsuarioCrear.getTxtFechaNacimiento().getDate().toString());
        u.setDireccion(vistaUsuarioCrear.getTxtDireccion().getText());
        u.setTelefono(vistaUsuarioCrear.getTxtTelefono().getText());
        u.setCorreo(vistaUsuarioCrear.getTxtCorreo().getText());
        u.setPassword(new String(vistaUsuarioCrear.getTxtPassword().getPassword()));
        u.setRol("usuario");
        return u;
    }

    private Paciente crearObjetoPaciente() {
        Paciente p = new Paciente();
        p.setNombres(vistaUsuarioPaciente.getTxtNombre().getText());
        p.setApellidos(vistaUsuarioPaciente.getTxtApellido().getText());
        p.setCedula(vistaUsuarioPaciente.getTxtIdentificacion().getText());
        p.setFechaNacimiento(vistaUsuarioPaciente.getTxtFechaNacimiento().getDate().toString());
        p.setDireccion(vistaUsuarioPaciente.getTxtDireccion().getText());
        p.setTelefono(vistaUsuarioPaciente.getTxtTelefono().getText());
        p.setCorreo(vistaUsuarioPaciente.getTxtCorreo().getText());
        p.setRol("paciente");
        return p;
    }

    private JSONObject convertirAJsonObject(Object obj) {
        return new JSONObject(gson.toJson(obj));
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

    private void limpiarFormularioUsuario() {
        vistaUsuarioCrear.getTxtNombre().setText("");
        vistaUsuarioCrear.getTxtApellido().setText("");
        vistaUsuarioCrear.getTxtIdentificacion().setText("");
        vistaUsuarioCrear.getTxtFechaNacimiento().setDate(null);
        vistaUsuarioCrear.getTxtDireccion().setText("");
        vistaUsuarioCrear.getTxtTelefono().setText("");
        vistaUsuarioCrear.getTxtCorreo().setText("");
        vistaUsuarioCrear.getTxtPassword().setText("");
    }

    private void limpiarFormularioPaciente() {
        vistaUsuarioPaciente.getTxtNombre().setText("");
        vistaUsuarioPaciente.getTxtApellido().setText("");
        vistaUsuarioPaciente.getTxtIdentificacion().setText("");
        vistaUsuarioPaciente.getTxtFechaNacimiento().setDate(null);
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
        JOptionPane.showMessageDialog(parent, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}
