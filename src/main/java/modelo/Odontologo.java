package modelo;

/**
 * @author christian
 */
public class Odontologo extends Persona {

    private String password;

    public Odontologo() {
        super();
        this.password = null;
    }

    public Odontologo(int id, String nombres, String apellidos, String cedula,
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
