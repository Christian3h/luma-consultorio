package modelo;

/**
 * @author christian
 */
public class Usuario extends Persona {

    private String password;

    public Usuario() {
        super();
        this.password = null;
    }

    public Usuario(int id, String nombre, String apellidos, String cedula,
            String fechaNacimiento, String direccion, String telefono,
            String correo, String rol, String historialMedico) {

        super(id, nombre, apellidos, cedula, fechaNacimiento, direccion, telefono, correo, rol);
        this.password = password;
    }

}
