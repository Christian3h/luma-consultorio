package modelo;

import org.json.JSONObject;
import org.json.JSONArray;
import util.JsonManager;
import com.google.gson.Gson;
import javax.swing.JOptionPane;
import vista.odontologo.odontologCrearOdontologo;
import vista.usuario.usuarioCrearPaciente;

/**
 * @author christian
 */
public class PersonaJson {

    private JSONObject database;

    public PersonaJson() {
        this.database = JsonManager.loadOrCreateJson();
    }

    public String validarUsuario(String username, String password) {
        JSONArray usuarios = database.getJSONArray("personas");
        
        for (int i = 0; i < usuarios.length(); i++) {
            JSONObject usuario = usuarios.getJSONObject(i);
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
    
    public boolean crearPersona(JSONObject nuevoUsuario, String rol){
        try {
            if (this instanceof usuarioCrearPaciente) {
                usuarioCrearPaciente vista = (usuarioCrearPaciente) this;
                int nuevoId = JsonManager.generarNuevoId("personas");
                Paciente paciente = new Paciente(
                        nuevoId, // ID temporal, deberías obtenerlo del sistema o base de datos
                        vista.getTxtNombre().getText(),
                        vista.getTxtApellido().getText(),
                        vista.getTxtIdentificacion().getText(),
                        vista.getTxtFechaNacimiento().getText(),
                        vista.getTxtDireccion().getText(),
                        vista.getTxtTelefono().getText(),
                        vista.getTxtCorreo().getText(),
                        rol
                );

                guardarPersona(paciente);

            } else if (this instanceof odontologCrearOdontologo) {
                odontologCrearOdontologo vista = (odontologCrearOdontologo) this;
                int nuevoId = JsonManager.generarNuevoId("personas");

                Odontologo odontologo = new Odontologo(
                        nuevoId, // Primer parámetro: ID generado
                        vista.getTxtNombre().getText(),
                        vista.getTxtApellido().getText(),
                        vista.getTxtIdentificacion().getText(),
                        vista.getTxtFechaNacimiento().getText(), 
                        vista.getTxtDireccion().getText(),
                        vista.getTxtTelefono().getText(),
                        vista.getTxtCorreo().getText(),
                        rol,
                        new String(vista.getTxtPassword().getPassword()) 
                );

                guardarPersona(odontologo);
            }else{
                System.out.println("No estas entrando a nada mi chino");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al crear: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

}
