package com.mycompany.luma;

import controlador.AdministrarPersonasControlador;
import controlador.Controlador;
import controlador.LoginControlador;
import javax.swing.SwingUtilities;
import org.json.JSONObject;
import vista.usuario.usuarioGestionarOdontologo;

public class LUMA {

    private static usuarioGestionarOdontologo usuarioGestionarOdontologoo;

    public static void main(String[] args) {
        LoginControlador objC = new LoginControlador();
        objC.iniciar();
    }
}
