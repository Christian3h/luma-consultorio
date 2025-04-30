package modelo;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import util.JsonManager;
import javax.swing.JOptionPane;

public class CitasJson {

    private JSONObject database;
    private PersonaJson personaJson;

    public CitasJson(PersonaJson personaJson) {
        this.database = JsonManager.loadOrCreateJson();
        this.personaJson = personaJson;
        if (!database.has("citas")) {
            database.put("citas", new JSONArray());
            JsonManager.saveJson(database);
        }
    }

    public boolean agregarCita(Citas nuevaCita) {
        if (!personaJson.existePaciente(nuevaCita.getDocumentoPaciente())) {
            JOptionPane.showMessageDialog(null,
                    "El documento del paciente no está registrado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!personaJson.existeOdontologo(nuevaCita.getDocumentoOdontologo())) {
            JOptionPane.showMessageDialog(null,
                    "El documento del odontólogo no está registrado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        JSONArray citas = database.getJSONArray("citas");
        JSONObject jsonCita = new JSONObject(new Gson().toJson(nuevaCita));
        citas.put(jsonCita);
        JsonManager.saveJson(database);
        JOptionPane.showMessageDialog(null,
                "Cita creada exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public JSONArray obtenerCitasPorPaciente(String documentoPaciente) {
        JSONArray citasFiltradas = new JSONArray();
        JSONArray citas = database.getJSONArray("citas");

        for (int i = 0; i < citas.length(); i++) {
            JSONObject citaJson = citas.getJSONObject(i);
            Citas cita = new Gson().fromJson(citaJson.toString(), Citas.class);
            if (cita.getDocumentoPaciente().equals(documentoPaciente)) {
                citasFiltradas.put(citaJson);
            }
        }
        return citasFiltradas;
    }

    public JSONArray obtenerCitasPorOdontologo(String documentoOdontologo) {
        JSONArray citasFiltradas = new JSONArray();
        JSONArray citas = database.getJSONArray("citas");

        for (int i = 0; i < citas.length(); i++) {
            JSONObject citaJson = citas.getJSONObject(i);
            Citas cita = new Gson().fromJson(citaJson.toString(), Citas.class);
            if (cita.getDocumentoOdontologo().equals(documentoOdontologo)) {
                citasFiltradas.put(citaJson);
            }
        }
        return citasFiltradas;
    }

    // Puedes agregar más métodos para buscar, modificar o eliminar citas si lo necesitas
}