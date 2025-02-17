/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra2;

import java.awt.*;
import javax.swing.*;

public class InicioSesion extends JFrame {
    private final JTextField txtUsuario;
    private final JPasswordField txtContrasena;
    private final JButton btnIngresar;
    private final JButton btnSalir;
    private static final players players = new usuarios(null,null);
    private final usuarios[] jugadores = players.getjugador();
    private final int contadorJug = players.getcantusuarios();

    public InicioSesion() {
        setTitle("Login de Jugadores");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for input fields
        JPanel panelCampos = new JPanel(new GridLayout(4, 1, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField(15);
        panelCampos.add(txtUsuario);

        panelCampos.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField(15);
        panelCampos.add(txtContrasena);

        // Panel for buttons
        JPanel panelBotones = new JPanel();
        btnIngresar = new JButton("Ingresar");
        btnSalir = new JButton("Salir");
        
        panelBotones.add(btnIngresar);
        panelBotones.add(btnSalir);

        // Login button action
        btnIngresar.addActionListener(e -> validarLogin());

        // Exit button action
        btnSalir.addActionListener(e -> {
            MenuPrincipal regreso = new MenuPrincipal();
            regreso.setVisible(true);
            dispose();
        });

        // Layout setup
        setLayout(new BorderLayout());
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        
        pack();
        setVisible(true);
    }

    private boolean validarLogin() {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtContrasena.getPassword()).trim();

        // Validation checks
        if (usuario.isEmpty() || password.isEmpty()) {
            mostrarError("Por favor, complete ambos campos.");
            return false;
        }

        // Check credentials against registered users
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null && 
                jugadores[i].getUsuario().equalsIgnoreCase(usuario) && 
                jugadores[i].getContrasena().equals(password)) {
                
                JOptionPane.showMessageDialog(this, 
                    "Bienvenido " + usuario, 
                    "Login Exitoso", 
                    JOptionPane.INFORMATION_MESSAGE);
                MenuInicial mi=new MenuInicial();
                mi.setVisible(true);
                
                
                
                dispose();
                return true;
            }
        }

        // If we get here, login failed
        mostrarError("Usuario o contraseña incorrectos.");
        return false;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, 
            mensaje, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}