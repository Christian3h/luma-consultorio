package modelo;

import org.json.JSONArray;
import org.json.JSONObject;
import util.JsonManager;

public class ConsultasJson {

    private JSONObject database;
    private PersonaJson modeloPersona;

    public ConsultasJson(PersonaJson modeloPersona) {
        this.modeloPersona = modeloPersona;
      
        try {
            this.database = JsonManager.loadOrCreateJson();
            
            if (!database.has("consultas")) {
                database.put("consultas", new JSONArray());
                JsonManager.saveJson(database);
            }
        } catch (Exception e) {
            System.err.println("Error crítico al inicializar:");
            e.printStackTrace();
        }
    }

    public boolean guardarConsulta(String procedimiento, String medicamentos, String valor, String documentoPaciente, String documentoOdontologo) {

        try {
            // Volver a cargar para asegurar datos frescos (opcional, depende de la concurrencia)
            this.database = JsonManager.loadOrCreateJson();

            JSONArray consultas = database.getJSONArray("consultas");
            
            JSONObject nuevaConsulta = new JSONObject();
            nuevaConsulta.put("procedimiento", procedimiento);
            nuevaConsulta.put("medicamentos", medicamentos);
            nuevaConsulta.put("valor", valor);
            nuevaConsulta.put("fecha", java.time.LocalDate.now().toString());
            nuevaConsulta.put("documentoPaciente", documentoPaciente); // Guardar documento del paciente
            nuevaConsulta.put("documentoOdontologo", documentoOdontologo); // Guardar documento del odontólogo

            consultas.put(nuevaConsulta);
            
            JsonManager.saveJson(database);

            // Verificación adicional (opcional)
            JSONObject verif = JsonManager.loadOrCreateJson();

            return true;
        } catch (Exception e) {
            System.err.println("Error durante el guardado:");
            e.printStackTrace();
            return false;
        }
    }

    public void limpiarConsultasAnteriores() {
        try {
            this.database = JsonManager.loadOrCreateJson();
            database.put("consultas", new JSONArray());
            JsonManager.saveJson(database);
        } catch (Exception e) {
            System.err.println("Error al limpiar consultas:");
            e.printStackTrace();
        }
    }
}