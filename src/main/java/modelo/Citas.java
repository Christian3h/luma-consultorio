
package modelo;

public class Citas {
    private String documentoPaciente;
    private String documentoOdontologo;
    private String fecha;
    private String descripcion;

    public Citas(String documentoPaciente, String documentoOdontologo, String fecha, String descripcion) {
        this.documentoPaciente = documentoPaciente;
        this.documentoOdontologo = documentoOdontologo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
    
    public Citas() {
        this.documentoPaciente = null;
        this.documentoOdontologo = null;
        this.fecha = null;
        this.descripcion = null;
    }

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
