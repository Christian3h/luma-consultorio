package modelo;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.JSONArray;
import util.JsonManager;
import javax.swing.JOptionPane;

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

            // jata aqui no llega
            System.out.println("Datos recibidos para crear persona:");
            System.out.println(datosPersona.toString(2));

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

            System.out.println(nuevoId);
            datosPersona.put("id", nuevoId); // Aseguramos que el ID esté en los datos

            if (rol.equalsIgnoreCase("paciente")) {
                System.out.println("Se creo un paciente lo que no deberia pasar si use un usuario");
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
                System.out.println("Se crea un paciente");
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
                System.out.println("Se creo un odontologo");
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
                System.out.println(usuario);
                guardarPersona(usuario);
                System.out.println("Se creo un usuario");
                return true;
            } else {
                System.out.println("Rol de persona no reconocido: " + rol);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al crear: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // se comento por que guardaba los objetos de manera desorganizada entonces no me servia
    private void guardarPersona(Object persona) {
        JSONArray personas = database.getJSONArray("personas");
        JSONObject jsonPersona = new JSONObject(new Gson().toJson(persona));
        personas.put(jsonPersona);
        System.out.println(jsonPersona);
        JsonManager.saveJson(database);
    }

    public JSONObject buscarPorCedula(String cedula) {
        JSONArray personas = database.getJSONArray("personas");

        for (int i = 0; i < personas.length(); i++) {
            JSONObject persona = personas.getJSONObject(i);
            if (persona.has("cedula") && persona.getString("cedula").equals(cedula)) {
                return persona;
            }
        }

        return null; // Si no se encuentra
    }

}
