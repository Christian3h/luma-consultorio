package controlador;

import vista.components.menu;
import vista.usuario.citasCrear;
import vista.usuario.usuarioCrearPaciente;
import vista.usuario.usuarioCrearUsuario;
import modelo.PersonaJson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window;
import javax.swing.SwingUtilities;

public class MenuControlador {

    private menu vistaMenu;
    private PersonaJson modeloPersona;
    private String rol;

    public MenuControlador(menu vista, PersonaJson modelo, String rol) {
        this.vistaMenu = vista;
        this.modeloPersona = modelo;
        this.rol = rol;
        inicializarListeners();
    }

    private void inicializarListeners() {

        vistaMenu.getBtnCrearCitas().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Window window = SwingUtilities.getWindowAncestor(vistaMenu);
                if (window != null) {
                    window.dispose(); // Cierra la ventana actual
                }

                citasCrear ventanaCita = new citasCrear();
                ventanaCita.setLocationRelativeTo(null);
                ventanaCita.setVisible(true);
            }
        });

        vistaMenu.getBtnCrearPacentes().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Window window = SwingUtilities.getWindowAncestor(vistaMenu);
                if (window != null) {
                    window.dispose(); // Cierra la ventana actual
                }

                usuarioCrearPaciente ventanaPaciente = new usuarioCrearPaciente();
                ventanaPaciente.setLocationRelativeTo(null);
                ventanaPaciente.setVisible(true);
            }
        });

        vistaMenu.getBtnCrearUsuarios().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Window window = SwingUtilities.getWindowAncestor(vistaMenu);
                if (window != null) {
                    window.dispose(); // Cierra la ventana actual
                }

                usuarioCrearUsuario ventanaUsuario = new usuarioCrearUsuario();
                ventanaUsuario.setLocationRelativeTo(null);
                ventanaUsuario.setVisible(true);
            }
        });
    }

}
