package modelo;

import com.google.gson.Gson;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONObject;
import util.JsonManager;
import javax.swing.JOptionPane;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con las citas.
 * 
 * Funcionalidad:
 * - Agregar, eliminar, actualizar y consultar citas desde un archivo JSON.
 * - Validar que los participantes (paciente y odontólogo) existan antes de registrar una cita.
 * 
 * Dependencias:
 * - Usa `JsonManager` para manejar el archivo JSON.
 * - Utiliza `PersonaJson` para verificar la existencia de pacientes y odontólogos.
 * 
 * Estructura esperada del JSON:
 * {
 *   "citas": [
 *     {
 *       "documentoPaciente": "...",
 *       "documentoOdontologo": "...",
 *       "fecha": "...",
 *       "descripcion": "..."
 *     }
 *   ]
 * }
 */
public class CitasJson {

    private JSONObject database;           // Representa el archivo completo de datos (JSON)
    private PersonaJson personaJson;       // Objeto para validar existencia de pacientes/odontólogos

    /**
     * Constructor que inicializa el archivo JSON y verifica la existencia del arreglo "citas".
     */
    public CitasJson(PersonaJson personaJson) {
        this.database = JsonManager.loadOrCreateJson();
        this.personaJson = personaJson;

        if (!database.has("citas")) {
            database.put("citas", new JSONArray()); // Crea el arreglo si no existe
            JsonManager.saveJson(database);
        }
    }

    /**
     * Agrega una nueva cita al archivo JSON, validando previamente la existencia del paciente y odontólogo.
     * 
     * @param nuevaCita Cita a registrar
     * @return true si se registró correctamente, false si hubo error de validación
     */
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

    /**
     * Retorna todas las citas asociadas a un paciente específico.
     * 
     * @param documentoPaciente Cédula del paciente
     * @return JSONArray con citas filtradas
     */
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

    /**
     * Retorna todas las citas asociadas a un odontólogo específico.
     * 
     * @param documentoOdontologo Cédula del odontólogo
     * @return JSONArray con citas filtradas
     */
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

    /**
     * Retorna todas las citas ordenadas por fecha y hora (campos: "fecha" y "hora").
     * 
     * @return JSONArray con citas ordenadas cronológicamente
     */
    public JSONArray obtenerTodasCitasOrdenadas() {
        JSONArray citas = database.getJSONArray("citas");
        JSONArray citasOrdenadas = new JSONArray();

        citas.toList().stream()
                .map(o -> new JSONObject((java.util.Map<?, ?>) o))
                .sorted(Comparator.comparing(c -> c.getString("fecha") + c.getString("hora")))
                .forEachOrdered(citasOrdenadas::put);

        return citasOrdenadas;
    }

    /**
     * Elimina una cita según documento del paciente y fecha.
     * 
     * @param documentoPaciente Cédula del paciente
     * @param fechaCita Fecha de la cita
     * @return true si fue eliminada, false si no se encontró
     */
    public boolean eliminarCita(String documentoPaciente, String fechaCita) {
        JSONArray citas = database.getJSONArray("citas");

        for (int i = 0; i < citas.length(); i++) {
            JSONObject cita = citas.getJSONObject(i);
            String documentoPacienteGuardado = cita.getString("documentoPaciente");
            String fechaGuardada = cita.getString("fecha");

            if (documentoPacienteGuardado.equals(documentoPaciente) && fechaGuardada.equals(fechaCita)) {
                citas.remove(i);
                JsonManager.saveJson(database);
                JOptionPane.showMessageDialog(null,
                        "Cita eliminada exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }

        JOptionPane.showMessageDialog(null,
                "Cita no encontrada.",
                "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    /**
     * Actualiza los datos de una cita existente.
     * 
     * @param documentoPaciente Cédula del paciente
     * @param fechaCitaOriginal Fecha original de la cita
     * @param citaActualizada Objeto JSON con los nuevos datos
     * @return true si se actualizó exitosamente, false si no se encontró la cita
     */
    public boolean actualizarCita(String documentoPaciente, String fechaCitaOriginal, JSONObject citaActualizada) {
        JSONArray citas = database.getJSONArray("citas");

        for (int i = 0; i < citas.length(); i++) {
            JSONObject cita = citas.getJSONObject(i);
            String documentoPacienteGuardado = cita.getString("documentoPaciente");
            String fechaGuardada = cita.getString("fecha");

            if (documentoPacienteGuardado.equals(documentoPaciente) && fechaGuardada.equals(fechaCitaOriginal)) {
                cita.put("documentoPaciente", citaActualizada.getString("documentoPaciente"));
                cita.put("documentoOdontologo", citaActualizada.getString("documentoOdontologo"));
                cita.put("descripcion", citaActualizada.getString("descripcion"));
                cita.put("fecha", citaActualizada.getString("fecha"));

                JsonManager.saveJson(database);
                return true;
            }
        }

        JOptionPane.showMessageDialog(null,
                "Cita no encontrada.",
                "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    /**
     * Obtiene todas las citas registradas sin modificar el original.
     * 
     * @return Copia de todas las citas almacenadas en formato JSONArray
     */
    public JSONArray obtenerTodasCitas() {
        if (!database.has("citas")) {
            return new JSONArray();
        }

        JSONArray citas = database.getJSONArray("citas");
        JSONArray copiaCitas = new JSONArray();

        for (int i = 0; i < citas.length(); i++) {
            copiaCitas.put(new JSONObject(citas.getJSONObject(i).toString()));
        }

        return copiaCitas;
    }
}
