
// LoginControlador.java (Versión simplificada o puede eliminarse si prefieres)
package controlador;

import vista.login;

/**
 * @author christian
 */
public class LoginControlador {
    // Esta clase puede tener funcionalidad mínima o incluso eliminarse 
    // si prefieres manejar todo desde el Controlador principal
    
    private login vista;
    
    public LoginControlador(login vista) {
        this.vista = vista;
        // Este constructor podría quedarse solo para mantener compatibilidad
        // con código existente si es necesario
    }
    
    // Método para acceder a la vista si es necesario
    public login getVista() {
        return vista;
    }
}