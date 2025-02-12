/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra2;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class InicioSesion extends JFrame {

    public usuarios[] jugadores;
    int indice;

    public JTextField txtUsuario;
    public JTextField txtContrasena;
    public JButton btnIniciar, btnSalir;

    public InicioSesion(usuarios[] jugadores, int indice) {
        this.jugadores = jugadores;
        this.indice = indice;

        setTitle("Iniciar Sesion");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 4, 2, 5));

        add(new JLabel("Usuario:"));
        txtUsuario = new JTextField(15);
        add(txtUsuario);

        add(new JLabel("Contraseña:"));
        txtContrasena = new JTextField(15);
        add(txtContrasena);

        btnIniciar = new JButton("Ingresar");
        add(btnIniciar);
        btnSalir=new JButton("Salir");
        add(btnSalir);

        btnIniciar.addActionListener((ActionEvent e) -> {
            verificarCredenciales();
            if (verificarCredenciales() == true) {
                MenuInicial mi = new MenuInicial();
                mi.setVisible(true);
                setVisible(false);
            }
        });
        
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
            MenuPrincipal mp= new MenuPrincipal();
            mp.setVisible(true);
        });

        setVisible(true);
    }

    private boolean verificarCredenciales() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getText());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Favor no dejar vacia las casillas", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        for (int i = 0; i < indice; i++) {
            if (jugadores[i].getUsuario().equals(usuario) && jugadores[i].getContrasena().equals(contrasena)) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso. ¡Bienvenido " + usuario + "!");
                return true;
            }
        }
        JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error!", JOptionPane.ERROR_MESSAGE);
        return false;
    }

}
