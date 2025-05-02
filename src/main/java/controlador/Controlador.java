package controlador;

import com.github.lgooddatepicker.components.DatePicker;
import com.google.gson.Gson;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import vista.usuario.*;
import modelo.*;
import org.json.JSONObject;
import vista.components.menu;
import java.time.format.DateTimeFormatter;
import vista.usuario.citasGestionar;

public class Controlador implements ActionListener {

    // Constantes para validación
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // Vistas
    private citasGestionar vistaGestion;
    private citasCrear vistaCitas;
    private usuarioCrearPaciente vistaUsuarioPaciente;
    private usuarioCrearUsuario vistaUsuarioCrear;
    private odontologCrearOdontologo vistaOdontologo;
    private menu vistaMenu;

    // Modelos y controladores
    private final PersonaJson personaModel;
    private final Gson gson = new Gson();
    private MenuControlador controladorMenu;
    private CitasControlador citasControlador;

    public Controlador() {
        this.personaModel = new PersonaJson();
        inicializarVistas();
        configurarActionListeners();
    }

    private void inicializarVistas() {
        this.vistaUsuarioPaciente = new usuarioCrearPaciente(this);
        this.vistaUsuarioCrear = new usuarioCrearUsuario(this);
        this.vistaOdontologo = new odontologCrearOdontologo(this);
    }

    private void configurarActionListeners() {
        vistaOdontologo.getBtnGuardar().addActionListener(this);
        vistaUsuarioPaciente.getBtnGuardar().addActionListener(this);
        vistaUsuarioCrear.getBtnGuardar().addActionListener(this);
    }

    // Métodos para iniciar vistas
    public void iniciarOdontologoCrear() {
        mostrarVista(vistaOdontologo);
    }

    public void iniciarUsuario() {
        mostrarVista(vistaUsuarioPaciente);
    }

    public void iniciarUsuarioCrear() {
        mostrarVista(vistaUsuarioCrear);
    }

    public void iniciarCitas() {
        vistaCitas = new citasCrear();
        citasControlador = new CitasControlador(vistaCitas, personaModel);
        mostrarVista(vistaCitas);
    }

    public void iniciarGestionCitas() {
        vistaGestion = new citasGestionar();
        citasControlador = new CitasControlador(vistaGestion, personaModel);
        mostrarVista(vistaGestion);
    }

    private void mostrarVista(JFrame vista) {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
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

    // Métodos para creación de entidades
    private void crearOdontologo() {
        if (!validarCamposOdontologo()) {
            return;
        }

        String correo = vistaOdontologo.getTxtCorreo().getText();
        String identificacion = vistaOdontologo.getTxtIdentificacion().getText();

        if (!validarDatosUnicos(correo, identificacion, vistaOdontologo)) {
            return;
        }

        procesarGuardado(crearObjetoOdontologo(), "odontologo", vistaOdontologo);
    }

    private void crearUsuario() {
        if (!validarCamposUsuario()) {
            return;
        }

        String correo = vistaUsuarioCrear.getTxtCorreo().getText();
        String identificacion = vistaUsuarioCrear.getTxtIdentificacion().getText();

        if (!validarDatosUnicos(correo, identificacion, vistaUsuarioCrear)) {
            return;
        }

        procesarGuardado(crearObjetoUsuario(), "usuario", vistaUsuarioCrear);
    }

    private void crearPaciente() {
        if (!validarCamposPaciente()) {
            return;
        }

        String correo = vistaUsuarioPaciente.getTxtCorreo().getText();
        String identificacion = vistaUsuarioPaciente.getTxtIdentificacion().getText();

        if (!validarDatosUnicos(correo, identificacion, vistaUsuarioPaciente)) {
            return;
        }

        procesarGuardado(crearObjetoPaciente(), "paciente", vistaUsuarioPaciente);
    }

    // Métodos de validación mejorados
    private boolean validarCamposOdontologo() {
        return validarCampoTexto(vistaOdontologo.getTxtNombre(), "nombre", vistaOdontologo)
                && validarCampoTexto(vistaOdontologo.getTxtApellido(), "apellido", vistaOdontologo)
                && validarIdentificacion(vistaOdontologo.getTxtIdentificacion(), vistaOdontologo)
                && validarFechaNacimiento(vistaOdontologo.getTxtFechaNacimiento(), vistaOdontologo)
                && validarCampoTexto(vistaOdontologo.getTxtDireccion(), "dirección", vistaOdontologo)
                && validarTelefono(vistaOdontologo.getTxtTelefono(), vistaOdontologo)
                && validarCorreo(vistaOdontologo.getTxtCorreo(), vistaOdontologo)
                && validarPassword(vistaOdontologo.getTxtPassword(), vistaOdontologo);
    }

    private boolean validarCamposUsuario() {
        return validarCampoTexto(vistaUsuarioCrear.getTxtNombre(), "nombre", vistaUsuarioCrear)
                && validarCampoTexto(vistaUsuarioCrear.getTxtApellido(), "apellido", vistaUsuarioCrear)
                && validarIdentificacion(vistaUsuarioCrear.getTxtIdentificacion(), vistaUsuarioCrear)
                && validarFechaNacimiento(vistaUsuarioCrear.getTxtFechaNacimiento(), vistaUsuarioCrear)
                && validarCampoTexto(vistaUsuarioCrear.getTxtDireccion(), "dirección", vistaUsuarioCrear)
                && validarTelefono(vistaUsuarioCrear.getTxtTelefono(), vistaUsuarioCrear)
                && validarCorreo(vistaUsuarioCrear.getTxtCorreo(), vistaUsuarioCrear)
                && validarPassword(vistaUsuarioCrear.getTxtPassword(), vistaUsuarioCrear);
    }

    private boolean validarCamposPaciente() {
        return validarCampoTexto(vistaUsuarioPaciente.getTxtNombre(), "nombre", vistaUsuarioPaciente)
                && validarCampoTexto(vistaUsuarioPaciente.getTxtApellido(), "apellido", vistaUsuarioPaciente)
                && validarIdentificacion(vistaUsuarioPaciente.getTxtIdentificacion(), vistaUsuarioPaciente)
                && validarFechaNacimiento(vistaUsuarioPaciente.getTxtFechaNacimiento(), vistaUsuarioPaciente)
                && validarCampoTexto(vistaUsuarioPaciente.getTxtDireccion(), "dirección", vistaUsuarioPaciente)
                && validarTelefono(vistaUsuarioPaciente.getTxtTelefono(), vistaUsuarioPaciente)
                && validarCorreo(vistaUsuarioPaciente.getTxtCorreo(), vistaUsuarioPaciente);
    }

    // Métodos de validación específicos
    private boolean validarCampoTexto(JTextField campo, String nombreCampo, JFrame parent) {
        if (campo.getText().trim().isEmpty()) {
            mostrarMensajeError("El " + nombreCampo + " no puede estar vacío.", parent);
            return false;
        }
        return true;
    }

    private boolean validarIdentificacion(JTextField campo, JFrame parent) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            mostrarMensajeError("La identificación no puede estar vacía.", parent);
            return false;
        }
        if (!NUMERIC_PATTERN.matcher(texto).matches()) {
            mostrarMensajeError("La identificación debe contener solo números.", parent);
            return false;
        }
        return true;
    }

    private boolean validarFechaNacimiento(DatePicker campo, JFrame parent) {
        if (campo.getDate() == null) {
            mostrarMensajeError("La fecha de nacimiento no puede estar vacía.", parent);
            return false;
        }
        return true;
    }

    private boolean validarTelefono(JTextField campo, JFrame parent) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            mostrarMensajeError("El teléfono no puede estar vacío.", parent);
            return false;
        }
        if (!NUMERIC_PATTERN.matcher(texto).matches()) {
            mostrarMensajeError("El teléfono debe contener solo números.", parent);
            return false;
        }
        return true;
    }

    private boolean validarCorreo(JTextField campo, JFrame parent) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            mostrarMensajeError("El correo electrónico no puede estar vacío.", parent);
            return false;
        }
        if (!EMAIL_PATTERN.matcher(texto).matches()) {
            mostrarMensajeError("Por favor, ingrese un correo electrónico válido (ejemplo: usuario@dominio.com).", parent);
            return false;
        }
        return true;
    }

    private boolean validarPassword(JPasswordField campo, JFrame parent) {
        if (campo.getPassword().length == 0) {
            mostrarMensajeError("La contraseña no puede estar vacía.", parent);
            return false;
        }
        return true;
    }

    private boolean validarDatosUnicos(String correo, String identificacion, JFrame parent) {
        if (personaModel.existeCorreo(correo)) {
            mostrarMensajeError("El correo electrónico ya está registrado.", parent);
            return false;
        }
        if (personaModel.existeIdentificacion(identificacion)) {
            mostrarMensajeError("La cédula ya está registrada.", parent);
            return false;
        }
        return true;
    }

    // Métodos para creación de objetos del modelo
    private Odontologo crearObjetoOdontologo() {
        Odontologo o = new Odontologo();
        setDatosPersonaBasicos(o, vistaOdontologo);
        o.setPassword(new String(vistaOdontologo.getTxtPassword().getPassword()));
        o.setRol("odontologo");
        return o;
    }

    private Usuario crearObjetoUsuario() {
        Usuario u = new Usuario();
        setDatosPersonaBasicos(u, vistaUsuarioCrear);
        u.setPassword(new String(vistaUsuarioCrear.getTxtPassword().getPassword()));
        u.setRol("usuario");
        return u;
    }

    private Paciente crearObjetoPaciente() {
        Paciente p = new Paciente();
        setDatosPersonaBasicos(p, vistaUsuarioPaciente);
        p.setRol("paciente");
        return p;
    }

    private void setDatosPersonaBasicos(Persona persona, JFrame vista) {
        if (vista instanceof odontologCrearOdontologo) {
            odontologCrearOdontologo v = (odontologCrearOdontologo) vista;
            persona.setNombres(v.getTxtNombre().getText());
            persona.setApellidos(v.getTxtApellido().getText());
            persona.setCedula(v.getTxtIdentificacion().getText());
            persona.setFechaNacimiento(v.getTxtFechaNacimiento().getDate().format(DATE_FORMATTER));
            persona.setDireccion(v.getTxtDireccion().getText());
            persona.setTelefono(v.getTxtTelefono().getText());
            persona.setCorreo(v.getTxtCorreo().getText());
        } else if (vista instanceof usuarioCrearUsuario) {
            usuarioCrearUsuario v = (usuarioCrearUsuario) vista;
            persona.setNombres(v.getTxtNombre().getText());
            persona.setApellidos(v.getTxtApellido().getText());
            persona.setCedula(v.getTxtIdentificacion().getText());
            persona.setFechaNacimiento(v.getTxtFechaNacimiento().getDate().format(DATE_FORMATTER));
            persona.setDireccion(v.getTxtDireccion().getText());
            persona.setTelefono(v.getTxtTelefono().getText());
            persona.setCorreo(v.getTxtCorreo().getText());
        } else if (vista instanceof usuarioCrearPaciente) {
            usuarioCrearPaciente v = (usuarioCrearPaciente) vista;
            persona.setNombres(v.getTxtNombre().getText());
            persona.setApellidos(v.getTxtApellido().getText());
            persona.setCedula(v.getTxtIdentificacion().getText());
            persona.setFechaNacimiento(v.getTxtFechaNacimiento().getDate().format(DATE_FORMATTER));
            persona.setDireccion(v.getTxtDireccion().getText());
            persona.setTelefono(v.getTxtTelefono().getText());
            persona.setCorreo(v.getTxtCorreo().getText());
        }
    }

    // Métodos utilitarios
    private void procesarGuardado(Object entidad, String rol, JFrame vista) {
        try {
            JSONObject jsonEntidad = new JSONObject(gson.toJson(entidad));
            boolean guardado = personaModel.crearPersona(jsonEntidad, rol);

            if (guardado) {
                mostrarMensajeExito(rol.substring(0, 1).toUpperCase() + rol.substring(1)
                        + " guardado correctamente", vista);
                limpiarFormulario(vista);
            } else {
                mostrarMensajeError("Error al guardar el " + rol, vista);
            }
        } catch (Exception ex) {
            manejarError(ex, vista);
        }
    }

    private void limpiarFormulario(JFrame vista) {
        if (vista instanceof odontologCrearOdontologo) {
            odontologCrearOdontologo v = (odontologCrearOdontologo) vista;
            v.getTxtNombre().setText("");
            v.getTxtApellido().setText("");
            v.getTxtIdentificacion().setText("");
            v.getTxtFechaNacimiento().setDate(null);
            v.getTxtDireccion().setText("");
            v.getTxtTelefono().setText("");
            v.getTxtCorreo().setText("");
            v.getTxtPassword().setText("");
        } else if (vista instanceof usuarioCrearUsuario) {
            usuarioCrearUsuario v = (usuarioCrearUsuario) vista;
            v.getTxtNombre().setText("");
            v.getTxtApellido().setText("");
            v.getTxtIdentificacion().setText("");
            v.getTxtFechaNacimiento().setDate(null);
            v.getTxtDireccion().setText("");
            v.getTxtTelefono().setText("");
            v.getTxtCorreo().setText("");
            v.getTxtPassword().setText("");
        } else if (vista instanceof usuarioCrearPaciente) {
            usuarioCrearPaciente v = (usuarioCrearPaciente) vista;
            v.getTxtNombre().setText("");
            v.getTxtApellido().setText("");
            v.getTxtIdentificacion().setText("");
            v.getTxtFechaNacimiento().setDate(null);
            v.getTxtDireccion().setText("");
            v.getTxtTelefono().setText("");
            v.getTxtCorreo().setText("");
        }
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
