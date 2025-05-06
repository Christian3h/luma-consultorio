package modelo;

import com.google.gson.Gson;
import java.util.Comparator;
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

    public JSONArray obtenerTodasCitasOrdenadas() {
        JSONArray citas = database.getJSONArray("citas");
        JSONArray citasOrdenadas = new JSONArray();

        citas.toList().stream()
                .map(o -> new JSONObject((java.util.Map<?, ?>) o))
                .sorted(Comparator.comparing(c -> c.getString("fecha") + c.getString("hora")))
                .forEachOrdered(citasOrdenadas::put);

        return citasOrdenadas;
    }

    // Método para eliminar una cita
    public boolean eliminarCita(String documentoPaciente, String fechaCita) {
        JSONArray citas = database.getJSONArray("citas");

        // Buscar la cita que coincida con el documento del paciente y la fecha
        for (int i = 0; i < citas.length(); i++) {
            JSONObject cita = citas.getJSONObject(i);
            String documentoPacienteGuardado = cita.getString("documentoPaciente");
            String fechaGuardada = cita.getString("fecha");

            if (documentoPacienteGuardado.equals(documentoPaciente) && fechaGuardada.equals(fechaCita)) {
                // Eliminar la cita encontrada
                citas.remove(i);
                JsonManager.saveJson(database);
                JOptionPane.showMessageDialog(null,
                        "Cita eliminada exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }

        // Si no se encontró la cita, mostrar mensaje de error
        JOptionPane.showMessageDialog(null,
                "Cita no encontrada.",
                "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean actualizarCita(String documentoPaciente, String fechaCitaOriginal, JSONObject citaActualizada) {
        JSONArray citas = database.getJSONArray("citas");

        // Buscar la cita que coincida con el documento del paciente y la fecha
        for (int i = 0; i < citas.length(); i++) {
            JSONObject cita = citas.getJSONObject(i);
            String documentoPacienteGuardado = cita.getString("documentoPaciente");
            String fechaGuardada = cita.getString("fecha");

            // Verificamos si encontramos la cita que corresponde al paciente y la fecha
            if (documentoPacienteGuardado.equals(documentoPaciente) && fechaGuardada.equals(fechaCitaOriginal)) {
                // Actualizamos los datos de la cita
                cita.put("documentoPaciente", citaActualizada.getString("documentoPaciente"));
                cita.put("documentoOdontologo", citaActualizada.getString("documentoOdontologo"));
                cita.put("descripcion", citaActualizada.getString("descripcion"));
                cita.put("fecha", citaActualizada.getString("fecha"));

                // Guardamos el archivo JSON con los cambios
                JsonManager.saveJson(database);
                return true; // La cita fue actualizada correctamente
            }
        }

        // Si no se encontró la cita
        JOptionPane.showMessageDialog(null,
                "Cita no encontrada.",
                "Error", JOptionPane.ERROR_MESSAGE);
        return false; // No se pudo actualizar la cita
    }

    public JSONArray obtenerTodasCitas() {
        if (!database.has("citas")) {
            return new JSONArray();  // Retorna una lista vacía si no hay citas
        }

        JSONArray citas = database.getJSONArray("citas");
        JSONArray copiaCitas = new JSONArray();

        // Crear una copia segura de las citas para evitar modificar el original
        for (int i = 0; i < citas.length(); i++) {
            copiaCitas.put(new JSONObject(citas.getJSONObject(i).toString()));
        }

        return copiaCitas;
    }
}
