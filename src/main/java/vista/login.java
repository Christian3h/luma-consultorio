package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import vista.components.menu;

/**
 * Vista principal para el inicio de sesión en el sistema LUMA
 *
 * Funcionalidades:
 * - Autenticación de usuarios (odontólogos, pacientes, administradores)
 * - Interfaz gráfica para ingreso de credenciales
 * - Manejo de eventos de ventana (arrastrar, cerrar)
 * - Validación básica de campos
 *
 * Componentes principales:
 * - Campos para documento y contraseña
 * - Botón de inicio de sesión
 * - Panel de cierre y movimiento de ventana
 *
 * Relaciones:
 * - Controlador: LoginControlador
 * - Modelo: PersonaJson
 * - Otras vistas: menu (se muestra después del login)
 *
 * Diseño:
 * - Patrón MVC (Vista)
 * - Interfaz personalizada sin decoraciones del sistema
 *
 * Autor: christian
 */
public class login extends javax.swing.JFrame {

    // Coordenadas para el arrastre de la ventana
    int xMouse, yMouse;
    
    // Referencia al menú principal que se mostrará después del login
    private menu vistaMenu;
    
    /**
     * Constructor de la ventana de login
     * - Inicializa los componentes visuales
     * - Crea y añade el menú principal (oculto inicialmente)
     * - Configura la ventana sin decoraciones del sistema
     */
    public login() {
        initComponents();
        vistaMenu = new menu(); // Inicializa el panel del menú
        this.add(vistaMenu, BorderLayout.WEST);
    }

    /**
     * Obtiene la referencia al menú principal
     * @return Objeto menu que contiene el menú principal
     */
    public menu getMenu() {
        return vistaMenu;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        buttonUser = new javax.swing.JButton();
        passwordUser = new javax.swing.JPasswordField();
        nameUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        panelMover = new javax.swing.JPanel();
        panelCerrar = new javax.swing.JPanel();
        labelCerrar = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 450));
        setMinimumSize(new java.awt.Dimension(700, 450));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setForeground(new java.awt.Color(83, 115, 112));

        buttonUser.setBackground(new java.awt.Color(83, 115, 112));
        buttonUser.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        buttonUser.setForeground(new java.awt.Color(242, 242, 242));
        buttonUser.setText("Iniciar Sesión");
        buttonUser.setBorder(null);
        buttonUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonUserMouseExited(evt);
            }
        });
        buttonUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUserActionPerformed(evt);
            }
        });

        passwordUser.setBackground(new java.awt.Color(242, 242, 242));
        passwordUser.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        passwordUser.setForeground(new java.awt.Color(102, 102, 102));
        passwordUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordUser.setText("*****");
        passwordUser.setBorder(null);
        passwordUser.setPreferredSize(new java.awt.Dimension(220, 30));
        passwordUser.setSelectionColor(new java.awt.Color(83, 115, 112));
        passwordUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passwordUserMousePressed(evt);
            }
        });
        passwordUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordUserActionPerformed(evt);
            }
        });

        nameUser.setBackground(new java.awt.Color(242, 242, 242));
        nameUser.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        nameUser.setForeground(new java.awt.Color(102, 102, 102));
        nameUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameUser.setText("Ingrese su documento");
        nameUser.setBorder(null);
        nameUser.setCaretColor(new java.awt.Color(83, 115, 112));
        nameUser.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        nameUser.setMaximumSize(new java.awt.Dimension(220, 30));
        nameUser.setMinimumSize(new java.awt.Dimension(220, 30));
        nameUser.setPreferredSize(new java.awt.Dimension(220, 30));
        nameUser.setSelectionColor(new java.awt.Color(83, 115, 112));
        nameUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameUserMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nameUserMousePressed(evt);
            }
        });
        nameUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameUserActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 37, 38));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LUMA Ortodoncia y Odontología");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/path1.png"))); // NOI18N

        panelMover.setBackground(new java.awt.Color(83, 115, 112));
        panelMover.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelMoverMouseDragged(evt);
            }
        });
        panelMover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelMoverMousePressed(evt);
            }
        });

        panelCerrar.setBackground(new java.awt.Color(83, 115, 112));
        panelCerrar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        panelCerrar.setPreferredSize(new java.awt.Dimension(30, 30));

        labelCerrar.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        labelCerrar.setForeground(new java.awt.Color(36, 37, 38));
        labelCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCerrar.setText("X");
        labelCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCerrarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelCerrarLayout = new javax.swing.GroupLayout(panelCerrar);
        panelCerrar.setLayout(panelCerrarLayout);
        panelCerrarLayout.setHorizontalGroup(
            panelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(panelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCerrarLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(labelCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelCerrarLayout.setVerticalGroup(
            panelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(panelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCerrarLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(labelCerrar)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelMoverLayout = new javax.swing.GroupLayout(panelMover);
        panelMover.setLayout(panelMoverLayout);
        panelMoverLayout.setHorizontalGroup(
            panelMoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMoverLayout.createSequentialGroup()
                .addGap(0, 660, Short.MAX_VALUE)
                .addComponent(panelCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelMoverLayout.setVerticalGroup(
            panelMoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMoverLayout.createSequentialGroup()
                .addComponent(panelCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.setForeground(new java.awt.Color(242, 242, 242));
        jTextField1.setText("jTextField1");
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(242, 242, 242));
        jTextField1.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        jTextField1.setSelectedTextColor(new java.awt.Color(242, 242, 242));
        jTextField1.setSelectionColor(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(passwordUser, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(buttonUser, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                    .addGap(180, 180, 180)
                    .addComponent(nameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelMover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(nameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(passwordUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(buttonUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento al presionar el panel de movimiento
     * @param evt Evento del mouse que contiene las coordenadas
     */
    private void panelMoverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMoverMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelMoverMousePressed

    /**
     * Evento al arrastrar la ventana
     * @param evt Evento del mouse que contiene las coordenadas de pantalla
     */
    private void panelMoverMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMoverMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_panelMoverMouseDragged

    /**
     * Evento al hacer click en el botón de cerrar
     * @param evt Evento del mouse
     */
    private void labelCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_labelCerrarMouseClicked

    /**
     * Evento al entrar el mouse en el botón de cerrar (efecto hover)
     * @param evt Evento del mouse
     */
    private void labelCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseEntered
        panelCerrar.setBackground(Color.red);
        labelCerrar.setForeground(Color.white);
    }//GEN-LAST:event_labelCerrarMouseEntered

    /**
     * Evento al salir el mouse del botón de cerrar (fin efecto hover)
     * @param evt Evento del mouse
     */
    private void labelCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseExited
        panelCerrar.setBackground(new java.awt.Color(83, 115, 112));
        labelCerrar.setForeground(new java.awt.Color(36, 37, 38));
    }//GEN-LAST:event_labelCerrarMouseExited

    /**
     * Evento al entrar el mouse en el botón de inicio de sesión (efecto hover)
     * @param evt Evento del mouse
     */
    private void buttonUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUserMouseEntered
        buttonUser.setBackground(new java.awt.Color(70, 110, 112));
        buttonUser.setForeground(Color.white);
    }//GEN-LAST:event_buttonUserMouseEntered

    /**
     * Evento al hacer click en el botón de inicio de sesión
     * @param evt Evento de acción
     */
    private void buttonUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonUserActionPerformed

    /**
     * Evento al salir el mouse del botón de inicio de sesión (fin efecto hover)
     * @param evt Evento del mouse
     */
    private void buttonUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUserMouseExited
        buttonUser.setBackground(new java.awt.Color(83, 115, 112));
        buttonUser.setForeground(new java.awt.Color(242, 242, 242));
    }//GEN-LAST:event_buttonUserMouseExited

    /**
     * Evento al presionar el campo de nombre de usuario
     * - Limpia el texto de placeholder si es necesario
     * - Restaura el placeholder del campo de contraseña si está vacío
     * @param evt Evento del mouse
     */
    private void nameUserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameUserMousePressed
        if (nameUser.getText().equals("Ingrese su documento")) {
            nameUser.setText("");
            nameUser.setForeground(new java.awt.Color(36, 37, 38));
        }
        if (String.valueOf(passwordUser.getPassword()).isEmpty()) {
            passwordUser.setText("*****");
            passwordUser.setForeground(new java.awt.Color(102, 102, 102));
        }
    }//GEN-LAST:event_nameUserMousePressed

    /**
     * Evento al presionar el campo de contraseña
     * - Limpia el texto de placeholder si es necesario
     * - Restaura el placeholder del campo de usuario si está vacío
     * @param evt Evento del mouse
     */
    private void passwordUserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordUserMousePressed
        if (String.valueOf(passwordUser.getPassword()).equals("*****")) {
            passwordUser.setText("");
            passwordUser.setForeground(new java.awt.Color(36, 37, 38));
        }
        if (nameUser.getText().isEmpty()) {
            nameUser.setText("Ingrese su documento");
            nameUser.setForeground(new java.awt.Color(102, 102, 102));
        }
    }//GEN-LAST:event_passwordUserMousePressed

    private void passwordUserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_passwordUserActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_passwordUserActionPerformed

    private void nameUserMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_nameUserMouseClicked
        // TODO add your handling code here:

    }// GEN-LAST:event_nameUserMouseClicked

    private void nameUserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nameUserActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_nameUserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
     * If Nimbus (introduced in Java SE 6) is not available, stay with the default
     * look and feel.
     * For details see
     * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    public JButton getButtonUser() {
        return buttonUser;
    }

    public void setButtonUser(JButton buttonUser) {
        this.buttonUser = buttonUser;
    }

    public JTextField getNameUser() {
        return nameUser;
    }

    public void setNameUser(JTextField nameUser) {
        this.nameUser = nameUser;
    }

    public JPasswordField getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(JPasswordField passwordUser) {
        this.passwordUser = passwordUser;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelCerrar;
    private javax.swing.JTextField nameUser;
    private javax.swing.JPanel panelCerrar;
    private javax.swing.JPanel panelMover;
    private javax.swing.JPasswordField passwordUser;
    // End of variables declaration//GEN-END:variables
}
