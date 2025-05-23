package controlador;

/**
 * @author christian
 */
import javax.swing.JOptionPane;
import vista.usuario.odontologCrearOdontologo;
import vista.usuario.usuarioCrearPaciente;
import modelo.Paciente;
import modelo.Odontologo;
import util.JsonManager;

public interface personaCrear {
    // Método default que crea el objeto automáticamente

    default void crearPersona(String rol) {
        
    }

    // Método para guardar (puedes sobrescribirlo según necesidad)
    default void guardarPersona(Object persona) {
        // Implementación básica (sobrescribe este método)
        JOptionPane.showMessageDialog(null,
                "Registro exitoso!\n" + persona.getClass().getSimpleName(),
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

}
