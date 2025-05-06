
package modelo;

public class Consultas {
    
    private String Procedimiento;
    private String Medicamentos;
    private String ValorConsulta;

    public Consultas(String Procedimiento, String Medicamentos, String ValorConsulta) {
        this.Procedimiento = Procedimiento;
        this.Medicamentos = Medicamentos;
        this.ValorConsulta = ValorConsulta;
    }
    
      public Consultas() {
        this.Procedimiento = null;
        this.Medicamentos = null;
        this.ValorConsulta = null;
    }

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
