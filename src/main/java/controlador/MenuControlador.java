package controlador;

/**
 * Controlador para el menú principal de usuarios
 * 
 * Funcionalidades:
 * - Navegación entre módulos
 * - Gestión de eventos de menú
 * - Coordinación entre vistas
 * 
 * Componentes asociados:
 * - menu (vista principal)
 * - Varias vistas secundarias
 * 
 * Modelos utilizados:
 * - PersonaJson (gestión usuarios)
 */
import vista.components.menu;
import modelo.PersonaJson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import vista.usuario.citasPanel;
import vista.usuario.usuarioGestionarOdontologo;

public class MenuControlador {

    private menu vistaMenu;
    private final PersonaJson modeloPersona;
    private final String rol;
    private Controlador controlador;
    private CitasControlador citasControlador;
    private citasPanel vistaCitasPanel;
    private final JPanel contentPanel;

    public MenuControlador(menu vista, PersonaJson modelo, String rol, Controlador controlador, javax.swing.JPanel contentPanel) {
        this.vistaMenu = vista;
        this.modeloPersona = modelo;
        this.rol = rol;
        this.controlador = controlador;
        inicializarListeners();
        this.contentPanel = contentPanel;
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
                        "Odontologo"
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
                        "Usuario"
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
                        "paciente"
                );
                vista.setLocationRelativeTo(null);
                vista.setVisible(true);
            }
        });

        vistaMenu.getBtnGestionarCitas().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                MenuControlador.this.vistaCitasPanel = new citasPanel(rol);
                new CitasPanelControlador(MenuControlador.this.vistaCitasPanel, modeloPersona);
                MenuControlador.this.vistaCitasPanel.setLocationRelativeTo(null);
                MenuControlador.this.vistaCitasPanel.setVisible(true);
            }
        });

        vistaMenu.getBtnCerrarSesion().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                LoginControlador objC = new LoginControlador();
                objC.iniciar();
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
