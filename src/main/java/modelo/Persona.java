package modelo;

/**
 * @author christian
 */
public class Persona {

    protected int id;
    protected String nombres;
    protected String apellidos;
    protected String cedula;
    protected String fechaNacimiento;
    protected String direccion;
    protected String telefono;
    protected String correo;
    protected String rol;

    public Persona(int id, String nombres, String apellidos, String cedula, String fechaNacimiento, String direccion,
            String telefono, String correo, String rol) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.rol = rol;
    }

    public Persona() {
        this.id = 0; // o -1 si prefieres
        this.nombres = null;
        this.apellidos = null;
        this.cedula = null;
        this.fechaNacimiento = null;
        this.direccion = null;
        this.telefono = null;
        this.correo = null;
        this.rol = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
    
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombre) {
        this.nombres = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



}
