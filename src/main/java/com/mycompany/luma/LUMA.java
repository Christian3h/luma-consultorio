
package com.mycompany.luma;

import controlador.Controlador;
import controlador.LoginControlador;
import javax.swing.SwingUtilities;
import org.json.JSONObject;


public class LUMA {
    public static void main(String[] args) {
        LoginControlador objC = new LoginControlador();
        objC.iniciar();
        //SwingUtilities.invokeLater(() -> new MiJFramePrincipal());
    }
}