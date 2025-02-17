/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra2;

import java.awt.*;

import javax.swing.*;

/**
 *
 * @author Maria Gabriela
 */
public class registro extends JFrame {
    private final JTextField txtUsuario;
    private final JTextField txtContrasena;
    private final JButton btnRegistrar;
    private final JButton btnSalir;
    private static final players players = new usuarios(null, null);
    private final usuarios[] jugadores = players.getjugador();
    private int contadorJug = players.getcantusuarios();

    public registro() {
        setTitle("Registro de Jugadores");
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
        btnRegistrar = new JButton("Registrar");
        btnSalir = new JButton("Salir");
        
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnSalir);

        // Register button action
        btnRegistrar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String contrasena = txtContrasena.getText().trim();
            registrarJugador(usuario, contrasena);
        });

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

    private boolean registrarJugador(String usu, String password) {
        // Validation checks
        if (usu.isEmpty() || password.isEmpty()) {
            mostrarError("Por favor, complete ambos campos.");
            return false;
        }
        
        if (password.length() < 5) {
            mostrarError("Por favor, ingrese una clave de 5 caracteres o más.");
            return false;
        }
        
        // Check array capacity first
        if (contadorJug > jugadores.length) {
            mostrarError("No se pueden registrar más jugadores.");
            return false;
        }
        
        // Check for duplicate username BEFORE registering
        for (int i = 0; i < contadorJug; i++) {
            if (jugadores[i] != null && 
                jugadores[i].getUsuario().equalsIgnoreCase(usu)) {
                mostrarError("Nombre de usuario ya en uso.");
                return false;
            }
        }
        
        try {
            // Create and register new player
            usuarios nuevoJugador = new usuarios(usu, password);
            
            // Add to array using contadorJug as index
            jugadores[contadorJug] = nuevoJugador;
            
            // Update the players management
            players.addusuario(nuevoJugador);
            players.setpa(nuevoJugador);
            contadorJug = players.getcantusuarios();
            
            JOptionPane.showMessageDialog(this, 
                "Jugador registrado correctamente.", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Clear the input fields
            txtUsuario.setText("");
            txtContrasena.setText("");
            
            // Return to main menu
            MenuPrincipal regreso = new MenuPrincipal();
            regreso.setVisible(true);
            dispose();
            return true;
            
        } catch (Exception e) {
            mostrarError("Error al registrar el jugador: " + e.getMessage());
            return false;
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, 
            mensaje, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}