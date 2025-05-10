package modelo;

import org.json.JSONArray;
import org.json.JSONObject;
import util.JsonManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase del modelo encargada de gestionar operaciones CRUD sobre personas almacenadas en un archivo JSON.
 * 
 * Funcionalidades:
 * - Listar personas por rol (paciente, odontólogo, etc.)
 * - Eliminar personas por cédula
 * - Actualizar registros de personas
 * - Buscar personas por cédula o nombre, filtrando por rol
 * 
 * Almacenamiento:
 * - Utiliza un objeto `JSONObject` como base de datos en memoria
 * - Persiste los cambios utilizando `JsonManager` para leer y guardar en archivo
 * 
 * Uso típico:
 * - Se utiliza desde controladores o vistas administrativas para mostrar o modificar datos de usuarios
 * 
 * Componentes relacionados:
 * - `JsonManager`: clase utilitaria para cargar y guardar el archivo JSON
 * 
 * Notas:
 * - Cada persona en el archivo debe tener los campos: `cedula`, `nombres`, `rol`, entre otros
 */
public class AdministrarPersonaJson {

    // Representa la base de datos de personas cargada desde JSON
    private JSONObject database;

    /**
     * Constructor que inicializa el objeto cargando el JSON desde archivo o creándolo si no existe.
     */
    public AdministrarPersonaJson() {
        this.database = JsonManager.loadOrCreateJson();
    }

    /**
     * Lista todas las personas que tienen un rol específico (ej: "paciente", "odontólogo").
     *
     * @param rol Rol a filtrar
     * @return Lista de objetos JSONObject que representan personas con ese rol
     */
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

    /**
     * Elimina una persona de la base de datos según su número de cédula.
     *
     * @param cedula Cédula de la persona a eliminar
     */
    public void eliminarPorCedula(String cedula) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.getString("cedula").equals(cedula)) {
                personas.remove(i);
                break;
            }
        }
        JsonManager.saveJson(database); // Persistir los cambios
    }

    /**
     * Actualiza los datos de una persona existente (según su cédula).
     *
     * @param personaActualizada Objeto JSONObject con los nuevos datos de la persona
     */
    public void actualizarPersona(JSONObject personaActualizada) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.getString("cedula").equals(personaActualizada.getString("cedula"))) {
                personas.put(i, personaActualizada);
                break;
            }
        }
        JsonManager.saveJson(database); // Persistir los cambios
    }

    /**
     * Busca personas por coincidencia parcial en el nombre o cédula, filtradas por rol.
     *
     * @param filtro Texto a buscar (se aplica a nombre y cédula, insensible a mayúsculas)
     * @param rol Rol que debe cumplir la persona para ser incluida en la búsqueda
     * @return Lista de personas que coinciden con el filtro y el rol
     */
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
