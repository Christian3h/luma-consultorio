package modelo;

/**
 * Clase modelo que representa una consulta médica u odontológica.
 * 
 * Esta clase se utiliza para almacenar los detalles relacionados con una consulta realizada,
 * incluyendo el procedimiento aplicado, los medicamentos recetados y el valor monetario
 * asociado a la consulta.
 * 
 * Se espera que esta clase sea utilizada como parte de procesos de facturación,
 * historiales médicos o reportes dentro del sistema.
 */
public class Consultas {

    // Nombre del procedimiento realizado durante la consulta
    private String Procedimiento;

    // Medicamentos recetados o administrados al paciente
    private String Medicamentos;

    // Valor económico de la consulta (ej. "$50.000")
    private String ValorConsulta;

    /**
     * Constructor con parámetros. Se usa para crear objetos de tipo Consultas con información completa.
     * 
     * @param Procedimiento Procedimiento realizado en la consulta
     * @param Medicamentos Medicamentos recetados
     * @param ValorConsulta Valor monetario de la consulta
     */
    public Consultas(String Procedimiento, String Medicamentos, String ValorConsulta) {
        this.Procedimiento = Procedimiento;
        this.Medicamentos = Medicamentos;
        this.ValorConsulta = ValorConsulta;
    }

    /**
     * Constructor vacío que inicializa todos los campos como null.
     * Útil para estructuras de deserialización o instanciación previa al llenado.
     */
    public Consultas() {
        this.Procedimiento = null;
        this.Medicamentos = null;
        this.ValorConsulta = null;
    }

    // Getters y Setters

    public String getProcedimiento() {
        return Procedimiento;
    }

    public void setProcedimiento(String Procedimiento) {
        this.Procedimiento = Procedimiento;
    }

    public String getMedicamentos() {
        return Medicamentos;
    }

    public void setMedicamentos(String Medicamentos) {
        this.Medicamentos = Medicamentos;
    }

    public String getValorConsulta() {
        return ValorConsulta;
    }

    public void setValorConsulta(String ValorConsulta) {
        this.ValorConsulta = ValorConsulta;
    }
}
