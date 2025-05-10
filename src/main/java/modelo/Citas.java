package modelo;

/**
 * Clase modelo que representa una cita médica entre un paciente y un odontólogo.
 * 
 * Funcionalidad:
 * - Almacena los datos clave de una cita: paciente, odontólogo, fecha y descripción
 * 
 * Uso típico:
 * - Se utiliza para registrar y gestionar citas dentro del sistema odontológico
 * - Se puede instanciar desde interfaces gráficas o lógicas de negocio para guardar, mostrar o modificar citas
 * 
 * Notas:
 * - Es un POJO (Plain Old Java Object) sin lógica adicional
 * - Se espera que los documentos (paciente y odontólogo) estén previamente validados
 */
public class Citas {
    
    // Cédula del paciente que asiste a la cita
    private String documentoPaciente;

    // Cédula del odontólogo que atiende la cita
    private String documentoOdontologo;

    // Fecha de la cita (formato esperado: dd/MM/yyyy o similar)
    private String fecha;

    // Descripción breve del motivo de la cita o notas
    private String descripcion;

    // Campo extra sin uso actual (posible uso futuro para fecha con hora exacta)
    private String fechaHora; 

    /**
     * Constructor que permite crear una cita con los datos esenciales.
     *
     * @param documentoPaciente Cédula del paciente
     * @param documentoOdontologo Cédula del odontólogo
     * @param fecha Fecha de la cita
     * @param descripcion Motivo o detalles de la cita
     */
    public Citas(String documentoPaciente, String documentoOdontologo, String fecha, String descripcion) {
        this.documentoPaciente = documentoPaciente;
        this.documentoOdontologo = documentoOdontologo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
    
    /**
     * Constructor vacío que inicializa todos los atributos como null.
     * Útil para frameworks o procesos de carga desde JSON.
     */
    public Citas() {
        this.documentoPaciente = null;
        this.documentoOdontologo = null;
        this.fecha = null;
        this.descripcion = null;
    }

    // Métodos getter y setter

    public String getDocumentoPaciente() {
        return documentoPaciente;
    }

    public void setDocumentoPaciente(String documentoPaciente) {
        this.documentoPaciente = documentoPaciente;
    }

    public String getDocumentoOdontologo() {
        return documentoOdontologo;
    }

    public void setDocumentoOdontologo(String documentoOdontologo) {
        this.documentoOdontologo = documentoOdontologo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
