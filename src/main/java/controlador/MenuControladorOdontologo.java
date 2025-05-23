package controlador;

/**
 * Controlador para el menú de odontólogos
 * 
 * Funcionalidades:
 * - Navegación específica para odontólogos
 * - Acceso a información profesional
 * - Gestión de consultas
 * 
 * Componentes asociados:
 * - menuOdont (vista principal)
 * - InfoOdontologo (formulario información)
 * 
 * Modelos utilizados:
 * - PersonaJson (gestión datos)
 */
import vista.components.menuOdont;
import modelo.PersonaJson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window;
import javax.swing.SwingUtilities;
import vista.Odontologo.InfoOdontologo;
import vista.components.menu;
import vista.usuario.citasPanel;

public class MenuControladorOdontologo {

    private menuOdont vistaMenu;
    private final PersonaJson modeloPersona;
    private final String rol;
    private final ControladorOdontologo controlador;
    private CitasControlador citasControlador;
    private citasPanel vistaCitasPanel;

    public MenuControladorOdontologo(menuOdont vista, PersonaJson modelo, String rol, ControladorOdontologo controlador) {
        this.vistaMenu = vista;
        this.modeloPersona = modelo;
        this.rol = rol;
        this.controlador = controlador;

        inicializarListeners();
    }

    private void inicializarListeners() {

        vistaMenu.getBtnSobreOdont().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                // Crear la vista del menú
                menuOdont menuVista = new menuOdont();
                // Crear el ControladorOdontologo, pasando la vista del menú
                ControladorOdontologo controladorOdontologo = new ControladorOdontologo(menuVista);
                // El controlador ahora se encarga de crear y mostrar InfoOdontologo
                controladorOdontologo.mostrarVistaInfo();
            }
        });

        vistaMenu.getBtnCitas().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cerrarVentanaActual();
                citasPanel ventanaCitasSinMenu = new citasPanel("paciente");
                ventanaCitasSinMenu.setVisible(true);
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
