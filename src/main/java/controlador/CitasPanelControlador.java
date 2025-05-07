package controlador;

import java.awt.Color;
import modelo.CitasJson;
import modelo.PersonaJson;
import vista.usuario.citasPanel;
import vista.usuario.EditarCitaFrame;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.List;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import vista.Odontologo.Consulta;

public class CitasPanelControlador {

    private PersonaJson personaModel;
    private citasPanel vista;
    private CitasJson modeloCita;
    private PersonaJson modeloPersona;
    private DateTimeFormatter horaDisplayFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter SPANISH_FORMATTER = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy HH:mm", new Locale("es"));

    private String rol;

    public CitasPanelControlador(citasPanel vista, PersonaJson modeloPersona) {
        this(vista, modeloPersona, "usuario"); // Valor por defecto
    }

    public CitasPanelControlador(citasPanel vista, PersonaJson modeloPersona, String rol) {
        this.vista = vista;
        this.personaModel = new PersonaJson();

        this.modeloPersona = modeloPersona;
        this.modeloCita = new CitasJson(modeloPersona);
        this.rol = rol != null ? rol.toLowerCase() : "usuario"; // Normalización del rol;

        configurarListeners();
        cargarCitas();
    }

    private void configurarListeners() {
        // Buscador por texto
        vista.getTxtBuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarCitas();
            }
        });

        // Filtro por fecha
        vista.getTxtFecha().addDateChangeListener(dateChangeEvent -> {
            buscarCitas();
        });
    }

    private void cargarCitas() {
        JSONArray citasArray = modeloCita.obtenerTodasCitas();
        mostrarCitas(citasArray);
    }

    private void mostrarCitas(JSONArray citasArray) {
        JPanel panelContenedor = vista.getCitasPanel();
        panelContenedor.removeAll();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        for (int i = 0; i < citasArray.length(); i++) {
            JSONObject cita = citasArray.getJSONObject(i);
            panelContenedor.add(crearPanelCita(cita));
            panelContenedor.add(Box.createVerticalStrut(10));
        }

        panelContenedor.revalidate();
        panelContenedor.repaint();
    }

    private JPanel crearPanelCita(JSONObject cita) {

        String idStr = Sesion.getInstancia().getIdUsuario();
        String cedula = cita.getString("documentoOdontologo");
        PersonaJson miPersonaJson = new PersonaJson();
        String documentoEncontrado = miPersonaJson.consultarDocumentoPorId(idStr);

        JPanel panelCita = new JPanel();
        panelCita.setLayout(new BoxLayout(panelCita, BoxLayout.Y_AXIS));
        panelCita.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(242, 242, 242)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panelCita.setBackground(new Color(242, 242, 242));

        try {

            //83,115,112 color verde
            //60,63,65 color texto
            //242,242,242 color texto
            // Obtener la fecha como String (sin parsear)
            String fechaStr = cita.getString("fecha");

            // Crear componentes comunes
            JLabel pacienteLabel = new JLabel("👤 Paciente: " + cita.getString("documentoPaciente"));
            JLabel odontologoLabel = new JLabel("🦷 Odontólogo: " + cita.getString("documentoOdontologo"));
            JLabel fechaLabel = new JLabel("📅 Fecha: " + fechaStr);
            JLabel descripcionLabel = new JLabel("📝 Descripción: " + cita.getString("descripcion"));

            // Agregar componentes comunes al panel
            panelCita.add(pacienteLabel);
            panelCita.add(odontologoLabel);
            panelCita.add(fechaLabel);
            panelCita.add(descripcionLabel);

            // Panel para botones
            JPanel panelBotones = new JPanel();

            JButton btnEditar = crearBotonEditar(cita);
            JButton btnEliminar = crearBotonEliminar(cita);

            // ---> Agregar colores a los botones
            btnEditar.setBackground(new Color(83, 115, 112));
            btnEditar.setForeground(new Color(242, 242, 242));
            btnEditar.setFocusPainted(false);
            btnEditar.setOpaque(true);
            btnEditar.setBorderPainted(false);

            btnEliminar.setBackground(new Color(83, 115, 112));
            btnEliminar.setForeground(new Color(242, 242, 242));
            btnEliminar.setFocusPainted(false);
            btnEliminar.setOpaque(true);
            btnEliminar.setBorderPainted(false);

            // Efecto hover para btnEditar
            btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnEditar.setBackground(new Color(100, 145, 140));
                    btnEditar.setForeground(Color.WHITE);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnEditar.setBackground(new Color(83, 115, 112));
                    btnEditar.setForeground(new Color(242, 242, 242));
                }
            });

            // Efecto hover para btnEliminar
            btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnEliminar.setBackground(new Color(100, 145, 140));
                    btnEliminar.setForeground(Color.WHITE);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnEliminar.setBackground(new Color(83, 115, 112));
                    btnEliminar.setForeground(new Color(242, 242, 242));
                }
            });
            // <---

            if (idStr == null) {
                panelBotones.add(btnEditar);
                panelBotones.add(btnEliminar);
            }

            // obteer id
            if (idStr != null && cedula.equals(documentoEncontrado)) {
                JButton btnIngresar = new JButton("🏥 Ingresar a consulta");
                btnIngresar.setFocusPainted(false);
                btnIngresar.addActionListener(e -> abrirVentanaConsulta(cita));
                panelBotones.add(btnIngresar);

                // ---> Agregar estilo a btnIngresar
                btnIngresar.setBackground(new Color(83, 115, 112));
                btnIngresar.setForeground(new Color(242, 242, 242));
                btnIngresar.setOpaque(true);
                btnIngresar.setBorderPainted(false);

                btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        btnIngresar.setBackground(new Color(100, 145, 140));
                        btnIngresar.setForeground(Color.WHITE);
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        btnIngresar.setBackground(new Color(83, 115, 112));
                        btnIngresar.setForeground(new Color(242, 242, 242));
                    }
                });
                // <---

            } else if (idStr != null) {
                panelCita.setVisible(false);
            }

            if(!verificarFechaCita(fechaStr)){
                panelCita.setVisible(false);
            }

            panelCita.add(panelBotones);

        } catch (Exception e) {
            e.printStackTrace();
            panelCita.add(new JLabel("Error al mostrar cita"));
        }

        panelCita.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, panelCita.getPreferredSize().height));
        return panelCita;
    }

    private void abrirVentanaConsulta(JSONObject cita) {
        try {
            Consulta consultaFrame = new Consulta();
            PersonaJson personaJson = new PersonaJson();

            String documentoPaciente = cita.getString("documentoPaciente");
            String documentoOdontologo = cita.getString("documentoOdontologo");

            String nombrePaciente = personaJson.obtenerNombrePorDocumento(documentoPaciente);
            String nombreOdontologo = personaJson.obtenerNombrePorDocumento(documentoOdontologo);

            consultaFrame.setDatosCita(
                    documentoPaciente,
                    documentoOdontologo,
                    cita.getString("fecha"),
                    cita.getString("descripcion"),
                    nombrePaciente,
                    nombreOdontologo
            );
            consultaFrame.setLocationRelativeTo(null);
            consultaFrame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Error al abrir la consulta: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton crearBotonEditar(JSONObject cita) {
        JButton btn = new JButton("✏️ Editar");
        btn.setFocusPainted(false);
        btn.addActionListener(e -> abrirVentanaEdicion(cita));
        return btn;
    }

    private JButton crearBotonEliminar(JSONObject cita) {
        JButton btn = new JButton("🗑️ Eliminar");
        btn.setFocusPainted(false);
        btn.addActionListener(e -> eliminarCita(cita));
        return btn;
    }

    private void abrirVentanaEdicion(JSONObject cita) {
        EditarCitaFrame ventanaEdicion = new EditarCitaFrame();
        ventanaEdicion.setLocationRelativeTo(null);
        ventanaEdicion.setVisible(true);

        try {
            // Parsear fecha usando el método mejorado
            LocalDateTime fechaHora = parsearFecha(cita.getString("fecha"));

            // Llenar campos
            ventanaEdicion.getTxtPaciente().setText(cita.getString("documentoPaciente"));
            ventanaEdicion.getTxtOdontologo().setText(cita.getString("documentoOdontologo"));
            ventanaEdicion.getTxtDescripcion().setText(cita.getString("descripcion"));
            ventanaEdicion.getTxtFecha().setDate(fechaHora.toLocalDate());
            ventanaEdicion.getTxtHora().setText(fechaHora.format(horaDisplayFormatter));

            // Configurar botón guardar
            ventanaEdicion.getBtnGuardar().addActionListener(e -> {
                guardarCambiosCita(ventanaEdicion, cita);
                ventanaEdicion.dispose();
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Error al cargar cita: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCambiosCita(EditarCitaFrame ventana, JSONObject citaOriginal) {
        try {
            // Obtener nuevos valores
            String paciente = ventana.getTxtPaciente().getText().trim();
            String odontologo = ventana.getTxtOdontologo().getText().trim();
            String descripcion = ventana.getTxtDescripcion().getText().trim();
            LocalDate fecha = ventana.getTxtFecha().getDate();
            String hora = ventana.getTxtHora().getText().trim();

            // Validación básica
            if (paciente.isEmpty() || odontologo.isEmpty() || descripcion.isEmpty() || fecha == null || hora.isEmpty()) {
                JOptionPane.showMessageDialog(ventana,
                        "Todos los campos son obligatorios",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar formato de hora
            try {
                LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(ventana,
                        "Formato de hora inválido. Use HH:mm (ej: 14:30)",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar existencia de paciente
            if (!PersonaJson.existePacienteC(paciente)) {
                JOptionPane.showMessageDialog(ventana,
                        "El paciente no está registrado en el sistema",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar existencia de odontólogo
            if (!PersonaJson.existeOdontologoC(odontologo)) {
                JOptionPane.showMessageDialog(ventana,
                        "El odontólogo no está registrado en el sistema",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear nueva fecha+hora
            LocalTime horaObj = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
            LocalDateTime nuevaFechaHora = LocalDateTime.of(fecha, horaObj);
            String nuevaFechaHoraStr = nuevaFechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            // Crear cita actualizada
            JSONObject citaActualizada = new JSONObject();
            citaActualizada.put("documentoPaciente", paciente);
            citaActualizada.put("documentoOdontologo", odontologo);
            citaActualizada.put("descripcion", descripcion);
            citaActualizada.put("fecha", nuevaFechaHoraStr);

            // Actualizar en el modelo
            if (modeloCita.actualizarCita(
                    citaOriginal.getString("documentoPaciente"),
                    citaOriginal.getString("fecha"),
                    citaActualizada)) {
                JOptionPane.showMessageDialog(vista,
                        "Cita actualizada correctamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarCitas();
            } else {
                JOptionPane.showMessageDialog(vista,
                        "Error al actualizar cita",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Error inesperado: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCita(JSONObject cita) {
        int confirmacion = JOptionPane.showConfirmDialog(
                vista,
                "¿Está seguro de eliminar esta cita?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (modeloCita.eliminarCita(
                    cita.getString("documentoPaciente"),
                    cita.getString("fecha"))) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Cita eliminada correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarCitas();
            } else {
                JOptionPane.showMessageDialog(
                        vista,
                        "Error al eliminar cita",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarCitas() {
        String textoBusqueda = vista.getTxtBuscar().getText().trim().toLowerCase();
        LocalDate fechaBusqueda = vista.getTxtFecha().getDate();

        JSONArray citasArray = modeloCita.obtenerTodasCitas();
        JSONArray citasFiltradas = new JSONArray();

        for (int i = 0; i < citasArray.length(); i++) {
            JSONObject cita = citasArray.getJSONObject(i);
            boolean coincide = true;

            // Filtrar por texto
            if (!textoBusqueda.isEmpty()) {
                String paciente = cita.getString("documentoPaciente").toLowerCase();
                String odontologo = cita.getString("documentoOdontologo").toLowerCase();
                coincide = paciente.contains(textoBusqueda) || odontologo.contains(textoBusqueda);
            }

            // Filtrar por fecha
            if (fechaBusqueda != null && coincide) {
                try {
                    LocalDateTime fechaCita = parsearFecha(cita.getString("fecha"));
                    coincide = fechaCita.toLocalDate().equals(fechaBusqueda);
                } catch (Exception e) {
                    System.err.println("Error al parsear fecha: " + e.getMessage());
                    coincide = false;
                }
            }

            if (coincide) {
                citasFiltradas.put(cita);
            }
        }

        mostrarCitas(citasFiltradas);
    }

    public boolean verificarFechaCita(String fechaCitaStr) {
        try {
            LocalDateTime fechaCita;

            // Intenta parsear con el primer formato
            try {
                fechaCita = LocalDateTime.parse(fechaCitaStr, ISO_FORMATTER);
            } catch (DateTimeParseException e1) {
                // Si falla, intenta con el segundo formato
                fechaCita = LocalDateTime.parse(fechaCitaStr, SPANISH_FORMATTER);
            }

            LocalDate hoy = LocalDate.now();
            LocalDate fechaCitaDate = fechaCita.toLocalDate();

            if (fechaCitaDate.isEqual(hoy)) {
                return true;
            } else if (fechaCitaDate.isAfter(hoy)) {
                return true;
            } else {
                LocalDate ayer = hoy.minusDays(1);
                return false;
            }
        } catch (DateTimeParseException e) {
            System.err.println("Error al parsear la fecha: " + fechaCitaStr + " - " + e.getMessage());
            return false;
        }
    }

    private LocalDateTime parsearFecha(String fechaStr) {
        // Normalización exhaustiva del string
        String normalizada = fechaStr
                .replace("\u202f", " ") // Espacio estrecho
                .replace("a. m.", "a.m.")
                .replace("p. m.", "p.m.")
                .replace("a.m.", "am")
                .replace("p.m.", "pm")
                .replace("am", "a.m.")
                .replace("pm", "p.m.")
                .replace("  ", " ") // Dobles espacios
                .trim();

        // Lista de formatos posibles
        List<DateTimeFormatter> formatos = Arrays.asList(
                DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy h:mma", new Locale("es", "ES")),
                DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy h:mm a", new Locale("es", "ES")),
                DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy HH:mm", new Locale("es", "ES")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), // Formato ISO
                DateTimeFormatter.ofPattern("d/M/yyyy HH:mm") // Formato corto
        );

        // Intentar con cada formato
        for (DateTimeFormatter formato : formatos) {
            try {
                return LocalDateTime.parse(normalizada, formato);
            } catch (DateTimeParseException e) {
                // Continuar con el siguiente formato
            }
        }

        // Si falla, intentar extraer solo fecha
        try {
            String[] partes = normalizada.split(" ");
            if (partes.length >= 5) {
                String soloFecha = partes[0] + " de " + partes[2] + " de " + partes[4];
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
                return LocalDate.parse(soloFecha, formatoFecha).atStartOfDay();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de fecha no reconocido: " + fechaStr);
        }

        throw new IllegalArgumentException("No se pudo parsear la fecha: " + fechaStr);
    }

}
