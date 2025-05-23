package modelo;

/**
 * @author christian
 */
public class Paciente extends Persona {

   // private String historialMedico;

   
    public Paciente() {
        super(); 
        //this.historialMedico = null;
    }

   
    public Paciente(int id, String nombres, String apellidos, String cedula, 
                   String fechaNacimiento, String direccion, String telefono, 
                   String correo, String rol /*, String historialMedico*/) {
      
        super(id, nombres, apellidos, cedula, fechaNacimiento, direccion, telefono, correo, rol);
        //this.historialMedico = historialMedico;
    }
    
    // (Opcional) Sobrescribir toString() para mostrar información del paciente
    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", cedula='" + cedula + '\'' +
                //", historialMedico='" + historialMedico + '\'' +
                '}';
    }
    

}