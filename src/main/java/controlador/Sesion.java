
package controlador;

/**
 *
 * @author christian
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
