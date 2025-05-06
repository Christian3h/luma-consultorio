package modelo;

import org.json.JSONArray;
import org.json.JSONObject;
import util.JsonManager;

import java.util.ArrayList;
import java.util.List;

public class AdministrarPersonaJson {

    private JSONObject database;

    public AdministrarPersonaJson() {
        this.database = JsonManager.loadOrCreateJson();
    }

    public List<JSONObject> listarPorRol(String rol) {
        JSONArray personas = database.getJSONArray("personas");
        List<JSONObject> resultado = new ArrayList<>();
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.getString("rol").equalsIgnoreCase(rol)) {
                resultado.add(persona);
            }
        }
        return resultado;
    }

    public void eliminarPorCedula(String cedula) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.getString("cedula").equals(cedula)) {
                personas.remove(i);
                break;
            }
        }
        JsonManager.saveJson(database);
    }

    public void actualizarPersona(JSONObject personaActualizada) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.getString("cedula").equals(personaActualizada.getString("cedula"))) {
                personas.put(i, personaActualizada);
                break;
            }
        }
        JsonManager.saveJson(database);
    }

    public List<JSONObject> buscarPorCedulaONombre(String filtro, String rol) {
        filtro = filtro.toLowerCase();
        List<JSONObject> resultado = new ArrayList<>();
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (!persona.getString("rol").equalsIgnoreCase(rol)) continue;

            String nombre = persona.getString("nombres").toLowerCase();
            String cedula = persona.getString("cedula").toLowerCase();

            if (nombre.contains(filtro) || cedula.contains(filtro)) {
                resultado.add(persona);
            }
        }
        return resultado;
    }
    
    
}
