
// LoginControlador.java (Versión simplificada o puede eliminarse si prefieres)
package controlador;

import vista.login;

/**
 * @author christian
 */
public class LoginControlador {
  
    
    private login vista;
    
    public LoginControlador(login vista) {
        this.vista = vista;
        
    }
    
    // Método para acceder a la vista si es necesario
    public login getVista() {
        return vista;
    }
}