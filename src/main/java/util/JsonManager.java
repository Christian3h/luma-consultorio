package util;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.nio.file.*;

public class JsonManager {
    private static final String JSON_FILE = "luma.json";

    public static JSONObject loadOrCreateJson() {
        try {
            if (!Files.exists(Paths.get(JSON_FILE))) {
                // Crear estructura inicial con usuario admin
                JSONObject initialStructure = new JSONObject();
                JSONArray personas = new JSONArray();
                
                // Crear usuario admin
                JSONObject Odontologo = new JSONObject();
                Odontologo.put("id", 1);
                Odontologo.put("nombres", "adminO");
                Odontologo.put("cedula", "123");
                Odontologo.put("password", "adminO"); 
                Odontologo.put("rol", "Odontologo");
                
                personas.put(Odontologo);
                
                JSONObject Usuario = new JSONObject();
                Usuario.put("id", 2);
                Usuario.put("nombres", "adminU");
                Usuario.put("cedula", "123");
                Usuario.put("password", "adminU"); 
                Usuario.put("rol", "Usuario");
                
                personas.put(Usuario);
                
                initialStructure.put("personas", personas);
                
                Files.write(Paths.get(JSON_FILE), 
                           initialStructure.toString(2).getBytes(),
                           StandardOpenOption.CREATE);
                return initialStructure;
            } else {
                String content = new String(Files.readAllBytes(Paths.get(JSON_FILE)));
                return new JSONObject(content);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error con el archivo JSON: " + e.getMessage());
        }
    }

    public static void saveJson(JSONObject data) {
        try {
            Files.write(Paths.get(JSON_FILE),
                    data.toString(2).getBytes(), 
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar JSON: " + e.getMessage());
        }
    }

        //funcion para crear id que no se repitan 
    public static int generarNuevoId(String tipo) {
        
    JSONObject data = loadOrCreateJson(); // Carga el JSON actual
    JSONArray personas = data.getJSONArray(tipo.toLowerCase());
    
    int maxId = 0;
    for (int i = 0; i < personas.length(); i++) {
        JSONObject persona = personas.getJSONObject(i);
        int currentId = persona.getInt("id");
        if (currentId > maxId) {
            maxId = currentId;
        }
    }
    
    return maxId + 1; // Retorna el siguiente ID disponible
}

    
    
}
