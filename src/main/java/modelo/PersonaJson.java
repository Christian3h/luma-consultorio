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

    public String validarUsuario(String username, String password) {
        JSONArray personas = database.getJSONArray("personas");

        for (int i = 0; i < personas.length(); i++) {
            JSONObject usuario = personas.getJSONObject(i);
            if (usuario.getString("username").equals(username)
                    && usuario.getString("password").equals(password)) {
                String rol = usuario.getString("rol");
                return rol;
            }
        }
        return "false";
    }

    public void agregarUsuario(JSONObject nuevoUsuario) {
        JSONArray usuarios = database.getJSONArray("usuarios");
        usuarios.put(nuevoUsuario);
        JsonManager.saveJson(database);
    }

    public boolean crearPersona(JSONObject datosPersona, String rol) {
        try {
            int nuevoId = JsonManager.generarNuevoId("personas");
            datosPersona.put("id", nuevoId); // Aseguramos que el ID esté en los datos

            if (rol.equalsIgnoreCase("paciente")) {
                Paciente paciente = new Paciente(
                        datosPersona.getInt("id"),
                        datosPersona.getString("nombre"),
                        datosPersona.getString("apellido"),
                        datosPersona.getString("identificacion"),
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
                        datosPersona.getString("nombre"),
                        datosPersona.getString("apellido"),
                        datosPersona.getString("identificacion"),
                        datosPersona.getString("fechaNacimiento"),
                        datosPersona.getString("direccion"),
                        datosPersona.getString("telefono"),
                        datosPersona.getString("correo"),
                        rol,
                        datosPersona.getString("password") // Asumo que la contraseña está en los datos
                );
                guardarPersona(odontologo);
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

    private void guardarPersona(Object persona) {
        JSONArray personas = database.getJSONArray("personas");
        JSONObject jsonPersona = new JSONObject(new Gson().toJson(persona));
        personas.put(jsonPersona);
        JsonManager.saveJson(database);
    }
}