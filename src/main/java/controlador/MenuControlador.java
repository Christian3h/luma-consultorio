package controlador;

import vista.components.menu;
import modelo.PersonaJson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window;
import javax.swing.SwingUtilities;

public class MenuControlador {

    private menu vistaMenu;
    private final PersonaJson modeloPersona;
    private final String rol;
    private Controlador controlador;

    public MenuControlador(menu vista, PersonaJson modelo, String rol, Controlador controlador) {
        this.vistaMenu = vista;
        this.modeloPersona = modelo;
        this.rol = rol;
        this.controlador = controlador;
        inicializarListeners();
    }

    private void inicializarListeners() {
        vistaMenu.getBtnCrearCitas().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                if (controlador == null) {
                    controlador = new Controlador();
                }
                // Aquí puedes implementar controlador.iniciarCitas(); si lo agregas
                controlador.iniciarCitas();
            }
        });

        vistaMenu.getBtnCrearPacentes().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (controlador == null) {
                    controlador = new Controlador();
                }
                cerrarVentanaActual();
                controlador.iniciarUsuario();
            }
        });

        vistaMenu.getBtnCrearUsuarios().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                if (controlador == null) {
                    controlador = new Controlador();
                }
                controlador.iniciarUsuarioCrear(); // ← debes agregar este método
            }
        });

        vistaMenu.getBtnCrearOdontologo().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                if (controlador == null) {
                    controlador = new Controlador();
                }
                controlador.iniciarOdontologoCrear();
            }
        });

    }

    private void cerrarVentanaActual() {
        Window window = SwingUtilities.getWindowAncestor(vistaMenu);
        if (window != null) {
            window.dispose();
        }
    }
}
