
package controlador;


/**
 * Clase para gestionar la sesión de usuario en la aplicación.
 * 
 * Funcionalidades:
 * - Control de sesión única (patrón Singleton)
 * - Almacena y recupera el ID del usuario activo
 * - Permite iniciar y cerrar sesión
 * 
 * Uso típico:
 * - Se accede a través de `Sesion.getInstancia()` para obtener o modificar la sesión actual
 * - Ideal para compartir datos del usuario entre diferentes vistas/controladores
 * 
 * Diseño:
 * - Implementa el patrón Singleton para asegurar que sólo exista una sesión activa a la vez
 * - El constructor es privado para evitar instanciación externa
 * 
 * Autor: christian
 */
public class Sesion {
    private static Sesion instancia;
    private String idUsuario;

    private Sesion() {} // constructor privado

    public static Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public void iniciarSesion(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void cerrarSesion() {
        idUsuario = null;
        instancia = null;
    }
}
