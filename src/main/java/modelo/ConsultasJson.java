package modelo;

import org.json.JSONArray;
import org.json.JSONObject;
import util.JsonManager;

/**
 * Clase encargada de gestionar las consultas en formato JSON.
 * 
 * Esta clase actúa como una capa de persistencia que permite guardar, limpiar
 * y estructurar la información de las consultas médicas u odontológicas
 * en un archivo JSON.
 * 
 * Funciones principales:
 * - Guardar una nueva consulta
 * - Limpiar todas las consultas existentes
 * 
 * Requiere una instancia de {@link PersonaJson} para validar relaciones con pacientes y odontólogos.
 */
public class ConsultasJson {

    // Base de datos en formato JSON cargada desde archivo
    private JSONObject database;

    // Referencia al modelo de personas para validar identidad
    private PersonaJson modeloPersona;

    /**
     * Constructor que inicializa la base de datos y garantiza que exista la estructura de "consultas".
     *
     * @param modeloPersona Instancia del modelo PersonaJson para futuras validaciones
     */
    public ConsultasJson(PersonaJson modeloPersona) {
        this.modeloPersona = modeloPersona;

        try {
            this.database = JsonManager.loadOrCreateJson();

            // Si no existe la clave "consultas", se crea como un arreglo vacío
            if (!database.has("consultas")) {
                database.put("consultas", new JSONArray());
                JsonManager.saveJson(database);
            }
        } catch (Exception e) {
            System.err.println("Error crítico al inicializar:");
            e.printStackTrace();
        }
    }

    /**
     * Guarda una nueva consulta en el archivo JSON.
     *
     * @param procedimiento Procedimiento realizado
     * @param medicamentos Medicamentos administrados o recetados
     * @param valor Valor económico de la consulta
     * @param documentoPaciente Documento de identidad del paciente
     * @param documentoOdontologo Documento de identidad del odontólogo
     * @return true si el guardado fue exitoso, false si ocurrió un error
     */
    public boolean guardarConsulta(String procedimiento, String medicamentos, String valor,
                                   String documentoPaciente, String documentoOdontologo) {

        try {
            // Asegura trabajar con la última versión del archivo
            this.database = JsonManager.loadOrCreateJson();

            JSONArray consultas = database.getJSONArray("consultas");

            // Crear y estructurar la nueva consulta como JSONObject
            JSONObject nuevaConsulta = new JSONObject();
            nuevaConsulta.put("procedimiento", procedimiento);
            nuevaConsulta.put("medicamentos", medicamentos);
            nuevaConsulta.put("valor", valor);
            nuevaConsulta.put("fecha", java.time.LocalDate.now().toString()); // Fecha actual
            nuevaConsulta.put("documentoPaciente", documentoPaciente);
            nuevaConsulta.put("documentoOdontologo", documentoOdontologo);

            consultas.put(nuevaConsulta);

            // Guardar en archivo
            JsonManager.saveJson(database);

            return true;

        } catch (Exception e) {
            System.err.println("Error durante el guardado:");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina todas las consultas previamente registradas.
     * 
     * Esta función es útil para pruebas, limpieza del sistema o reinicios de base.
     */
    public void limpiarConsultasAnteriores() {
        try {
            this.database = JsonManager.loadOrCreateJson();
            database.put("consultas", new JSONArray()); // Reemplaza con arreglo vacío
            JsonManager.saveJson(database);
        } catch (Exception e) {
            System.err.println("Error al limpiar consultas:");
            e.printStackTrace();
        }
    }
}
