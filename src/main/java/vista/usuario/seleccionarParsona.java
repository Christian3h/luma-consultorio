package vista.usuario;

import modelo.AdministrarPersonaJson;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;
import org.json.JSONObject;

public class seleccionarParsona extends JDialog {

    private String datoSeleccionado;
    private final AdministrarPersonaJson personaModel;
    private final String rol;
    private final JTable jTable1;
    private int filaSeleccionada = -1;

    public seleccionarParsona(Frame parent, boolean modal, String rol) {
        super(parent, modal);
        this.personaModel = new AdministrarPersonaJson();
        this.rol = rol;
        this.jTable1 = new JTable();

        initComponents();
        cargarDatos();
        centrarVentana(parent);
    }

    private void initComponents() {
        setTitle("Seleccionar " + rol);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setResizable(false);

        // Configuración de la tabla
        jTable1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre", "Apellido", "Cédula"}));
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                filaSeleccionada = jTable1.rowAtPoint(evt.getPoint());
            }
        });

        JScrollPane jScrollPane1 = new JScrollPane(jTable1);

        // Botón de selección
        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.addActionListener(e -> {
            if (filaSeleccionada != -1) {
                datoSeleccionado = jTable1.getValueAt(filaSeleccionada, 2).toString();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Por favor seleccione una fila",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Layout mejorado
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(jScrollPane1, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSeleccionar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private void centrarVentana(Window parent) {
        setLocationRelativeTo(parent);
    }

    private void cargarDatos() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar tabla

        try {

            List<JSONObject> personas = personaModel.listarPorRol(rol);
            for (int i = 0; i < personas.size(); i++) {
                JSONObject persona = personas.get(i);
                model.addRow(new Object[]{
                    persona.optString("nombres", ""),
                    persona.optString("apellidos", ""),
                    persona.optString("cedula", "")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los datos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getDatoSeleccionado() {
        return datoSeleccionado;
    }

    public JTable getTabla() {
        return jTable1;
    }
}
