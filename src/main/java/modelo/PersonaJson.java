package modelo;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import util.JsonManager;
import javax.swing.JOptionPane;

/**
 * Clase para gestionar las personas dentro del sistema. Esta clase permite
 * realizar operaciones CRUD sobre las personas, tales como: validación de
 * usuario, creación de nuevos usuarios, consulta de información de personas,
 * verificación de existencia de correos, identificaciones, roles, y más.
 *
 * Además, gestiona la carga y almacenamiento de los datos a través de un
 * archivo JSON utilizando la clase {@link JsonManager}.
 *
 * @author christian
 */
public class PersonaJson {

    // Base de datos que contiene las personas en formato JSON
    private JSONObject database;

    /**
     * Constructor que carga o crea la base de datos de personas desde un
     * archivo JSON.
     */
    public PersonaJson() {
        this.database = JsonManager.loadOrCreateJson();
    }

    /**
     * Método que valida si un usuario existe en el sistema y su rol asociado,
     * comparando la identificación y la contraseña.
     *
     * @param identificacion Identificación (cédula) del usuario
     * @param password Contraseña del usuario
     * @return Rol del usuario si se valida correctamente, "false" si no existe.
     */
    public String validarUsuario(String identificacion, String password) {
        JSONArray personas = database.getJSONArray("personas");

        for (int i = 0; i < personas.length(); i++) {
            JSONObject usuario = personas.getJSONObject(i);
            if (usuario.getString("cedula").equals(identificacion)
                    && usuario.getString("password").equals(password)) {
                String rol = usuario.getString("rol");
                return rol;
            }
        }
        return "false";
    }

    /**
     * Método que obtiene el ID de un usuario en base a su identificación y
     * contraseña.
     *
     * @param identificacion Identificación (cédula) del usuario
     * @param password Contraseña del usuario
     * @return ID del usuario si se encuentra, "false" si no existe.
     */
    public String obtenerId(String identificacion, String password) {
        JSONArray personas = database.getJSONArray("personas");

        for (int i = 0; i < personas.length(); i++) {
            JSONObject usuario = personas.getJSONObject(i);
            if (usuario.getString("cedula").equals(identificacion)
                    && usuario.getString("password").equals(password)) {
                String id = String.valueOf(usuario.get("id"));
                return id;
            }
        }
        return "false";
    }

    /**
     * Verifica si ya existe un correo en la base de datos de personas.
     *
     * @param correo Correo electrónico a verificar
     * @return true si el correo ya está registrado, false en caso contrario.
     */
    public boolean existeCorreo(String correo) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.has("correo") && persona.getString("correo").equals(correo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si un paciente existe en el sistema mediante su identificación.
     *
     * @param identificacion Identificación del paciente
     * @return true si el paciente existe, false en caso contrario.
     */
    public boolean existePaciente(String identificacion) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.has("cedula") && persona.getString("cedula").equals(identificacion) && persona.getString("rol").equalsIgnoreCase("paciente")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si un odontólogo existe en el sistema mediante su
     * identificación.
     *
     * @param identificacion Identificación del odontólogo
     * @return true si el odontólogo existe, false en caso contrario.
     */
    public boolean existeOdontologo(String identificacion) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.has("cedula") && persona.getString("cedula").equals(identificacion) && persona.getString("rol").equalsIgnoreCase("odontologo")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si una identificación ya está registrada en el sistema.
     *
     * @param identificacion Identificación a verificar
     * @return true si la identificación ya existe, false en caso contrario.
     */
    public boolean existeIdentificacion(String identificacion) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.has("cedula") && persona.getString("cedula").equals(identificacion)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega un nuevo usuario a la base de datos de personas.
     *
     * @param nuevoUsuario Objeto JSON que contiene los datos del nuevo usuario
     */
    public void agregarUsuario(JSONObject nuevoUsuario) {
        JSONArray usuarios = database.getJSONArray("usuarios");
        usuarios.put(nuevoUsuario);
        JsonManager.saveJson(database);
    }

    /**
     * Crea una nueva persona en el sistema según el rol especificado. Realiza
     * validaciones previas para evitar duplicados de correo y cédula.
     *
     * @param datosPersona Datos de la nueva persona en formato JSON
     * @param rol Rol de la persona ("paciente", "odontologo", "usuario")
     * @return true si la persona fue creada correctamente, false en caso de
     * error.
     */
    public boolean crearPersona(JSONObject datosPersona, String rol) {
        try {
            // Verificaciones previas
            if (existeCorreo(datosPersona.getString("correo"))) {
                JOptionPane.showMessageDialog(null,
                        "El correo electrónico ya está registrado.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (existeIdentificacion(datosPersona.getString("cedula"))) {
                JOptionPane.showMessageDialog(null,
                        "La cédula ya está registrada.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Generación del nuevo ID
            int nuevoId = JsonManager.generarNuevoId("personas");
            datosPersona.put("id", nuevoId); // Aseguramos que el ID esté en los datos

            // Creación según el rol
            if (rol.equalsIgnoreCase("paciente")) {
                Paciente paciente = new Paciente(
                        datosPersona.getInt("id"),
                        datosPersona.getString("nombres"),
                        datosPersona.getString("apellidos"),
                        datosPersona.getString("cedula"),
                        datosPersona.getString("fechaNacimiento"),
                        datosPersona.getString("direccion"),
                        datosPersona.getString("telefono"),
                        datosPersona.getString("correo"),
                        rol
                );
                guardarPersona(paciente);
                return true;
            } else if (rol.equalsIgnoreCase("odontologo")) {
                Odontologo odontologo = new Odontologo(
                        datosPersona.getInt("id"),
                        datosPersona.getString("nombres"),
                        datosPersona.getString("apellidos"),
                        datosPersona.getString("cedula"),
                        datosPersona.getString("fechaNacimiento"),
                        datosPersona.getString("direccion"),
                        datosPersona.getString("telefono"),
                        datosPersona.getString("correo"),
                        rol,
                        datosPersona.getString("password")
                );
                guardarPersona(odontologo);
                return true;
            } else if (rol.equalsIgnoreCase("usuario")) {
                Usuario usuario = new Usuario(
                        datosPersona.getInt("id"),
                        datosPersona.getString("nombres"),
                        datosPersona.getString("apellidos"),
                        datosPersona.getString("cedula"),
                        datosPersona.getString("fechaNacimiento"),
                        datosPersona.getString("direccion"),
                        datosPersona.getString("telefono"),
                        datosPersona.getString("correo"),
                        rol,
                        datosPersona.getString("password")
                );
                guardarPersona(usuario);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al crear: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Lista todas las personas de un rol específico.
     *
     * @param rol Rol de las personas a listar
     * @return Lista de personas que coinciden con el rol especificado
     */
    public List<Persona> listarPorRol(String rol) {
        List<Persona> personasFiltradas = new ArrayList<>();
        JSONArray personasArray = database.getJSONArray("personas");

        for (int i = 0; i < personasArray.length(); i++) {
            JSONObject obj = personasArray.getJSONObject(i);
            if (obj.getString("rol").equalsIgnoreCase(rol)) {
                Persona persona = new Gson().fromJson(obj.toString(), Persona.class);
                personasFiltradas.add(persona);
            }
        }

        return personasFiltradas;
    }

    private void guardarPersona(Object persona) {
        JSONArray personas = database.getJSONArray("personas");
        JSONObject jsonPersona = new JSONObject(new Gson().toJson(persona));
        personas.put(jsonPersona);
        JsonManager.saveJson(database);
    }

    public JSONObject consultarPersonaPorId(int id) {
        JSONArray personas = database.getJSONArray("personas");

        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.getInt("id") == id) {
                return persona;
            }
        }
        return null;
    }

    public String consultarDocumentoPorId(String id) {
        try {
            int idNum = Integer.parseInt(id);
            JSONArray personas = database.getJSONArray("personas");
            for (int i = 0; i < personas.length(); i++) {
                JSONObject persona = personas.getJSONObject(i);
                if (persona.getInt("id") == idNum) {
                    return persona.getString("cedula");
                }
            }
        } catch (NumberFormatException e) {
            // System.err.println("Error: El ID proporcionado no es un número válido.");
            return null;
        }
        return null;
    }

    public String obtenerNombrePorDocumento(String documento) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.has("cedula") && persona.getString("cedula").equals(documento)) {
                return persona.getString("nombres") + " " + persona.getString("apellidos");
            }

        }

        return null; // Retorna null si no se encuentra la persona

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

    public boolean eliminarPersona(int id) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.getInt("id") == id) {
                personas.remove(i);
                JsonManager.saveJson(database);
                return true;
            }
        }
        return false;
    }

    public boolean actualizarPersona(int id, JSONObject nuevosDatos) {
        JSONArray personas = database.getJSONArray("personas");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.getInt("id") == id) {
                nuevosDatos.put("id", persona.getInt("id"));
                nuevosDatos.put("rol", persona.getString("rol"));
                personas.put(i, nuevosDatos);
                JsonManager.saveJson(database);
                return true;
            }
        }
        return false;
    }

    public static boolean existePacienteC(String identificacion) {
        JSONObject database = JsonManager.loadOrCreateJson();
        JSONArray personas = database.getJSONArray("personas");

        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.has("cedula")
                    && persona.getString("cedula").equals(identificacion)
                    && persona.getString("rol").equalsIgnoreCase("paciente")) {
                return true;
            }
        }
        return false;
    }

    public static boolean existeOdontologoC(String identificacion) {
        JSONObject database = JsonManager.loadOrCreateJson();
        JSONArray personas = database.getJSONArray("personas");

        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.has("cedula")
                    && persona.getString("cedula").equals(identificacion)
                    && persona.getString("rol").equalsIgnoreCase("odontologo")) {
                return true;
            }
        }
        return false;
    }

    // Método privado para guardar una persona genérica en la base de datos
}
