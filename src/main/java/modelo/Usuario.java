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

    public Usuario(int id, String nombres, String apellidos, String cedula,
            String fechaNacimiento, String direccion, String telefono,
            String correo, String rol, String password) {

        super(id, nombres, apellidos, cedula, fechaNacimiento, direccion, telefono, correo, rol);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

}
