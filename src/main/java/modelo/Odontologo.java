package modelo;

/**
 * Clase que representa a un odontólogo en el sistema.
 * 
 * Hereda de la clase {@link Persona}, por lo tanto, incluye atributos comunes como:
 * nombre, apellido, cédula, etc. Además, agrega un campo específico: contraseña.
 * 
 * Esta clase puede ser usada para autenticación, asignación de citas, 
 * o para registrar procedimientos realizados.
 * 
 * @author christian
 */
public class Odontologo extends Persona {

    // Contraseña de acceso del odontólogo al sistema
    private String password;

    /**
     * Constructor por defecto que inicializa todos los campos como null.
     * Utiliza el constructor por defecto de la clase base {@link Persona}.
     */
    public Odontologo() {
        super();  // Llama al constructor de Persona
        this.password = null;
    }

    /**
     * Constructor completo que permite instanciar un objeto Odontologo con todos sus datos.
     *
     * @param id ID único del odontólogo
     * @param nombres Nombres del odontólogo
     * @param apellidos Apellidos del odontólogo
     * @param cedula Cédula o número de identificación
     * @param fechaNacimiento Fecha de nacimiento
     * @param direccion Dirección de residencia
     * @param telefono Número de contacto
     * @param correo Correo electrónico
     * @param rol Rol del usuario (debe ser "odontologo" para este caso)
     * @param password Contraseña de acceso
     */
    public Odontologo(int id, String nombres, String apellidos, String cedula,
                      String fechaNacimiento, String direccion, String telefono,
                      String correo, String rol, String password) {

        super(id, nombres, apellidos, cedula, fechaNacimiento, direccion, telefono, correo, rol);
        this.password = password;
    }

    /**
     * Obtiene la contraseña del odontólogo.
     *
     * @return String con la contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del odontólogo.
     *
     * @param password Nueva contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
