package controlador;

import vista.components.menu;
import modelo.PersonaJson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window;
import javax.swing.SwingUtilities;

public class MenuControlador {

    private final menu vistaMenu;
    private final PersonaJson modeloPersona;
    private final String rol;
    private final Controlador controlador;

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
                // Aquí puedes implementar controlador.iniciarCitas(); si lo agregas
                controlador.iniciarCitas();
            }
        });

        vistaMenu.getBtnCrearPacentes().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                controlador.iniciarUsuario(); // ← se reutiliza la vista y sus listeners
            }
        });

        vistaMenu.getBtnCrearUsuarios().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                controlador.iniciarUsuarioCrear(); // ← debes agregar este método
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
