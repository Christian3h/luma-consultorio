package modelo;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import util.JsonManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author christian
 */
public class PersonaJson {

    private JSONObject database;

    public PersonaJson() {
        this.database = JsonManager.loadOrCreateJson();
    }

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

    public void agregarUsuario(JSONObject nuevoUsuario) {
        JSONArray usuarios = database.getJSONArray("usuarios");
        usuarios.put(nuevoUsuario);
        JsonManager.saveJson(database);
    }

    public boolean crearPersona(JSONObject datosPersona, String rol) {
        try {
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

            int nuevoId = JsonManager.generarNuevoId("personas");
            datosPersona.put("id", nuevoId); // Aseguramos que el ID esté en los datos
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

    // se comento por que guardaba los objetos de manera desorganizada entonces no me servia
    private void guardarPersona(Object persona) {
        JSONArray personas = database.getJSONArray("personas");
        JSONObject jsonPersona = new JSONObject(new Gson().toJson(persona));
        personas.put(jsonPersona);
        JsonManager.saveJson(database);
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

}
