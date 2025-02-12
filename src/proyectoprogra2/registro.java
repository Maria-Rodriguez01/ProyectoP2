/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Maria Gabriela
 */
public class registro extends JFrame {

    public JTextField txtUsuario;
    public JTextField txtContrasena;
    public JButton btnRegistrar, btnSalir;


    public usuarios[] jugadores;
    private int indice;

    public registro() {
        setTitle("Registro de Jugadores");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Inicializar el arreglo con un tamaño máximo (ejemplo: 10 jugadores)
        jugadores = new usuarios[20];
        indice = 0;

        JPanel panelCampos = new JPanel(new GridLayout(4, 1, 5, 5));
        panelCampos.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField(15);
        panelCampos.add(txtUsuario);

        panelCampos.add(new JLabel("Contraseña:"));
        txtContrasena = new JTextField(15);
        panelCampos.add(txtContrasena);

        JPanel panelBotones = new JPanel();
        btnRegistrar = new JButton("Registrar");
        panelBotones.add(btnRegistrar);
        btnSalir=new JButton("Salir");
        panelBotones.add(btnSalir);


        // Acción del botón de registro
        btnRegistrar.addActionListener((ActionEvent e) -> {
            registrarJugador();
            MenuPrincipal regreso=new MenuPrincipal();
            regreso.setVisible(true);
            this.dispose();
            new InicioSesion(jugadores, indice); 
        });
        btnSalir.addActionListener((ActionEvent e) -> {
            MenuPrincipal regreso=new MenuPrincipal();
            regreso.setVisible(true);
            this.dispose();
        });
        add(panelCampos, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        setVisible(true);
    }

    private void registrarJugador() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete ambos campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (contrasena.length() < 5) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una clave de 5 caracteres o mas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (indice < jugadores.length) {
            jugadores[indice] = new usuarios(usuario, contrasena);
            indice++;
            JOptionPane.showMessageDialog(this, "Jugador registrado correctamente.");
            txtUsuario.setText("");
            txtContrasena.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No se pueden registrar más jugadores.", "Límite alcanzado", JOptionPane.ERROR_MESSAGE);
        }
    }

}
