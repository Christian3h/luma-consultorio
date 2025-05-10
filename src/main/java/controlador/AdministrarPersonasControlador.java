package controlador;

/**
 * Controlador para la gestión CRUD de personas (pacientes, odontólogos, usuarios)
 * 
 * Funcionalidades principales:
 * - Listar personas por rol
 * - Filtrar y buscar personas
 * - Editar y eliminar registros
 * - Validación de datos
 * 
 * Componentes asociados:
 * - JTable para mostrar datos
 * - JTextField para búsqueda
 * - JButton para acciones
 * 
 * Modelos utilizados:
 * - AdministrarPersonaJson (persistencia)
 */
import modelo.AdministrarPersonaJson;
import org.json.JSONObject;
import org.json.JSONArray;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Arrays;

public class AdministrarPersonasControlador {

    private JTable tabla;
    private JTextField txtBuscar;
    private JButton btnEliminar;
    private String rol;
    private DefaultTableModel modeloTabla;
    private AdministrarPersonaJson json;
    private boolean mostrarPassword;

    public AdministrarPersonasControlador(JTable tabla, JTextField txtBuscar, JButton btnEliminar, String rol) {
        this.tabla = tabla;
        this.txtBuscar = txtBuscar;
        this.btnEliminar = btnEliminar;
        this.rol = rol;
        this.json = new AdministrarPersonaJson();

        this.mostrarPassword = "Odontologo".equalsIgnoreCase(rol) || "Usuario".equalsIgnoreCase(rol);

        this.modeloTabla = (DefaultTableModel) tabla.getModel();
        configurarModeloTabla();
        cargarDatos();
        agregarEventos();
    }

    private void configurarModeloTabla() {
        modeloTabla.setColumnCount(0);
        modeloTabla.addColumn("Nombres");
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Fecha Nacimiento");
        modeloTabla.addColumn("Correo");

        if (mostrarPassword) {
            modeloTabla.addColumn("Contraseña");
        }
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<JSONObject> personas = json.listarPorRol(rol);
        agregarFilasATabla(personas);
    }

    private void agregarFilasATabla(List<JSONObject> personas) {
        for (JSONObject persona : personas) {
            Object[] fila = new Object[]{
                persona.optString("nombres"),
                persona.optString("cedula"),
                persona.optString("apellidos"),
                persona.optString("telefono"),
                persona.optString("direccion"),
                persona.optString("fechaNacimiento"),
                persona.optString("correo")
            };

            if (mostrarPassword) {
                fila = Arrays.copyOf(fila, fila.length + 1);
                fila[fila.length - 1] = persona.has("password") ? "********" : "";
            }

            modeloTabla.addRow(fila);
        }
    }

    private void agregarEventos() {
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarDatos(txtBuscar.getText());
            }
        });

        btnEliminar.addActionListener(e -> eliminarPersonaSeleccionada());

        modeloTabla.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int fila = e.getFirstRow();
                    String cedula = (String) modeloTabla.getValueAt(fila, 1);

                    // Buscar la persona original usando el método existente
                    List<JSONObject> resultados = json.buscarPorCedulaONombre(cedula, rol);
                    if (!resultados.isEmpty()) {
                        JSONObject personaOriginal = resultados.get(0);
                        JSONObject personaActualizada = obtenerPersonaDesdeFila(fila, personaOriginal);

                        if (validarCambios(personaOriginal, personaActualizada)) {
                            json.actualizarPersona(personaActualizada);
                        } else {
                            //JOptionPane.showMessageDialog(null, "Datos inválidos. Por favor revise los campos.");
                            cargarDatos();
                        }
                    }
                }
            }
        });
    }

    private void filtrarDatos(String filtro) {
        modeloTabla.setRowCount(0);
        List<JSONObject> filtrados = json.buscarPorCedulaONombre(filtro, rol);
        agregarFilasATabla(filtrados);
    }

    private void eliminarPersonaSeleccionada() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una persona para eliminar.");
            return;
        }
        String cedula = (String) modeloTabla.getValueAt(fila, 1);
        json.eliminarPorCedula(cedula);
        modeloTabla.removeRow(fila);
    }

    private JSONObject obtenerPersonaDesdeFila(int fila, JSONObject personaOriginal) {
        JSONObject persona = new JSONObject(personaOriginal.toString());

        persona.put("nombres", modeloTabla.getValueAt(fila, 0));
        persona.put("cedula", modeloTabla.getValueAt(fila, 1));
        persona.put("apellidos", modeloTabla.getValueAt(fila, 2));
        persona.put("telefono", modeloTabla.getValueAt(fila, 3));
        persona.put("direccion", modeloTabla.getValueAt(fila, 4));
        persona.put("fechaNacimiento", modeloTabla.getValueAt(fila, 5));
        persona.put("correo", modeloTabla.getValueAt(fila, 6));
        persona.put("rol", rol);

        if (mostrarPassword && modeloTabla.getColumnCount() > 7) {
            String passwordEnTabla = (String) modeloTabla.getValueAt(fila, 7);
            if (!passwordEnTabla.equals("********")) {
                persona.put("password", passwordEnTabla);
            }
        }

        return persona;
    }

    private boolean validarCambios(JSONObject original, JSONObject actualizado) {

        if (!original.optString("nombres").equals(actualizado.optString("nombres"))
                && actualizado.optString("nombres").isEmpty()) {
            JOptionPane.showMessageDialog(null, "Datos inválidos. El nombre no puede estar vacío.");
            return false;
        }

        if (!original.optString("cedula").equals(actualizado.optString("cedula"))) {
            if (!Pattern.matches("\\d+", actualizado.optString("cedula"))) {
                JOptionPane.showMessageDialog(null, "Datos inválidos. La cédula debe contener solo números.");
                return false;
            }
        }

        if (!original.optString("apellidos").equals(actualizado.optString("apellidos"))
                && actualizado.optString("apellidos").isEmpty()) {
            return false;
        }

        if (!original.optString("telefono").equals(actualizado.optString("telefono"))) {
            if (!Pattern.matches("\\d+", actualizado.optString("telefono"))) {
                JOptionPane.showMessageDialog(null, "Datos inválidos. El teléfono debe contener solo números.");
                return false;
            }
        }

        if (!original.optString("direccion").equals(actualizado.optString("direccion"))
                && actualizado.optString("direccion").isEmpty()) {
            return false;
        }

        if (!original.optString("fechaNacimiento").equals(actualizado.optString("fechaNacimiento"))) {
            String fechaStr = actualizado.optString("fechaNacimiento");
            if (!fechaStr.isEmpty()) {
                if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", fechaStr)) { // Usando regex para la fecha
                    JOptionPane.showMessageDialog(null, "Datos inválidos. La fecha de nacimiento debe tener el formato YYYY-MM-DD.");
                    return false;
                }

                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                try {
                    LocalDate.parse(fechaStr, formatter);
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(null, "Datos inválidos. La fecha de nacimiento debe tener el formato YYYY-MM-DD.");
                    return false;
                }

            } else {
                return false; 
            }
        }

        if (!original.optString("correo").equals(actualizado.optString("correo"))) {
            if (!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$", actualizado.optString("correo"))) {
                JOptionPane.showMessageDialog(null, "Datos inválidos. Por favor, ingrese un correo electrónico válido (ejemplo: usuario@dominio.com).");
                return false;
            }
        }

        return true;
    }
}
