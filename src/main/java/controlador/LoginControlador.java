package controlador;

import javax.swing.*;
import java.awt.event.*;
import vista.login;
import modelo.PersonaJson;
import vista.components.menu;

public class LoginControlador implements ActionListener {

    private final Controlador objC = new Controlador();
    private final login vistaLogin;
    private final PersonaJson personaModel;

    public LoginControlador() {
        this.personaModel = new PersonaJson();
        this.vistaLogin = new login();
        configurarActionListeners();
    }

    public void iniciar() {
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setVisible(true);
    }

    private void configurarActionListeners() {
        vistaLogin.getButtonUser().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.getButtonUser()) {
            validarLogin();
        }
    }

    private void mostrarMenu(String rol) {
        menu vistaMenu = new menu();
        new MenuControlador(vistaMenu, personaModel, rol, objC); // pasar objC
        vistaMenu.setVisible(true);
    }

    private void validarLogin() {
        String user = vistaLogin.getNameUser().getText();
        String pass = new String(vistaLogin.getPasswordUser().getPassword());
        String rol = personaModel.validarUsuario(user, pass).toLowerCase();

        if (rol.equals("odontologo")) {
            mostrarMenu(rol);
            objC.iniciarOdontologo();
            vistaLogin.setVisible(false);
        } else if (rol.equals("usuario")) {
            mostrarMenu(rol);
            objC.iniciarUsuario();
            vistaLogin.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(vistaLogin, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
