package controlador;

import vista.components.menu;
import modelo.PersonaJson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window;
import javax.swing.SwingUtilities;
import vista.usuario.usuarioGestionarOdontologo;

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
                controlador.iniciarCitas();
            }
        });

        vistaMenu.getBtnCrearPacentes().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                if (controlador == null) {
                    controlador = new Controlador();
                }
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
                controlador.iniciarUsuarioCrear();
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

        vistaMenu.getBtnGestionarOdontologos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                usuarioGestionarOdontologo vista = new usuarioGestionarOdontologo();
                AdministrarPersonasControlador controlador = new AdministrarPersonasControlador(
                        vista.getBtlAgenda(),
                        vista.getTxtBuscar(),
                        vista.getBtnEliminar(),
                        "Odontologo" // o "Odontologo" o "Usuario" o "paciente" según corresponda
                );
                vista.setLocationRelativeTo(null);
                vista.setVisible(true);
            }
        });

        vistaMenu.getBtnGestionarUsuarios().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                usuarioGestionarOdontologo vista = new usuarioGestionarOdontologo();
                AdministrarPersonasControlador controlador = new AdministrarPersonasControlador(
                        vista.getBtlAgenda(),
                        vista.getTxtBuscar(),
                        vista.getBtnEliminar(),
                        "Usuario" // o "Odontologo" o "Usuario" o "paciente" según corresponda
                );
                vista.setLocationRelativeTo(null);
                vista.setVisible(true);
            }
        });

        vistaMenu.getBtnGestionarPacientes().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                usuarioGestionarOdontologo vista = new usuarioGestionarOdontologo();
                AdministrarPersonasControlador controlador = new AdministrarPersonasControlador(
                        vista.getBtlAgenda(),
                        vista.getTxtBuscar(),
                        vista.getBtnEliminar(),
                        "paciente" // o "Odontologo" o "Usuario" o "paciente" según corresponda
                );
                vista.setLocationRelativeTo(null);
                vista.setVisible(true);
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
