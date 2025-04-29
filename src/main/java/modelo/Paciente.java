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

   
    public Paciente(int id, String nombre, String apellidos, String cedula, 
                   String fechaNacimiento, String direccion, String telefono, 
                   String correo, String rol /*, String historialMedico*/) {
      
        super(id, nombre, apellidos, cedula, fechaNacimiento, direccion, telefono, correo, rol);
        //this.historialMedico = historialMedico;
    }

    // Getter y Setter para historialMedico
//    public String getHistorialMedico() {
//        return historialMedico;
//    }

//    public void setHistorialMedico(String historialMedico) {
//        this.historialMedico = historialMedico;
//    }

    // (Opcional) Sobrescribir toString() para mostrar información del paciente
    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", cedula='" + cedula + '\'' +
                //", historialMedico='" + historialMedico + '\'' +
                '}';
    }
    

}