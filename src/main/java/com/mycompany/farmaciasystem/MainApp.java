package com.mycompany.farmaciasystem;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.mycompany.farmaciasystem.Presentacion.Login.Login;

public class MainApp {

    public static void main(String[] args) {

        FlatIntelliJLaf.setup();
        Login myLogin = new Login();
        myLogin.setVisible(true);
        myLogin.setLocationRelativeTo(null);
    }
}
