package modelo;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import util.JsonManager;
import javax.swing.JOptionPane;
import java.util.UUID;

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

        // Generar ID único para la cita
        String idCita = UUID.randomUUID().toString();
        JSONObject jsonCita = new JSONObject(new Gson().toJson(nuevaCita));
        jsonCita.put("id", idCita);

        database.getJSONArray("citas").put(jsonCita);
        JsonManager.saveJson(database);
        JOptionPane.showMessageDialog(null,
                "Cita creada exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public JSONArray obtenerTodasLasCitas() {
        return database.getJSONArray("citas");
    }

    public boolean eliminarCita(String idCita) {
        JSONArray citas = database.getJSONArray("citas");

        for (int i = 0; i < citas.length(); i++) {
            JSONObject cita = citas.getJSONObject(i);
            if (idCita.equals(cita.optString("id"))) {
                citas.remove(i);
                JsonManager.saveJson(database);
                return true;
            }
        }
        return false;
    }

    public boolean editarCita(String idCita, Citas citaEditada) {
        JSONArray citas = database.getJSONArray("citas");

        for (int i = 0; i < citas.length(); i++) {
            JSONObject cita = citas.getJSONObject(i);
            if (idCita.equals(cita.optString("id"))) {
                JSONObject nuevaCita = new JSONObject(new Gson().toJson(citaEditada));
                nuevaCita.put("id", idCita); // mantener el mismo ID
                citas.put(i, nuevaCita);
                JsonManager.saveJson(database);
                return true;
            }
        }
        return false;
    }

    public JSONArray buscarCitasPorTexto(String filtro) {
        filtro = filtro.toLowerCase();
        JSONArray citasFiltradas = new JSONArray();
        JSONArray citas = database.getJSONArray("citas");

        for (int i = 0; i < citas.length(); i++) {
            JSONObject cita = citas.getJSONObject(i);
            String descripcion = cita.optString("documentoOdontologo", "").toLowerCase();
            String fecha = cita.optString("documentoPaciente", "").toLowerCase();

            if (descripcion.contains(filtro) || fecha.contains(filtro)) {
                citasFiltradas.put(cita);
            }
        }

        return citasFiltradas;
    }
}
