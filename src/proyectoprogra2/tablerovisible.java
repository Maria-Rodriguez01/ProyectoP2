package proyectoprogra2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class tablerovisible extends JFrame implements ActionListener {

    private JLabel JugadorALabel;
    private JComboBox<String> opciones;
    private JButton Salirbtn, Jugarbtn;
    private static final players players = new usuarios(null, null);

    public tablerovisible() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("XIANQI Oponente");
        this.setLayout(null);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        

        JugadorALabel = new JLabel("Jugador Activo: " + players.getpa().getUsuario());
        JugadorALabel.setBounds(150, 100, 200, 25);
        JugadorALabel.setForeground(Color.black);
        JugadorALabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(JugadorALabel);

        opciones = new JComboBox<>();
        opciones.setBounds(150, 150, 200, 25);
        this.add(opciones);
        llenarComboBox();

        Jugarbtn = new JButton("Jugar!");
        Jugarbtn.setBounds(150, 200, 100, 30);  // Adjusted position and size
        Jugarbtn.addActionListener(this);  // Add action listener
        this.add(Jugarbtn);

        Salirbtn = new JButton("Salir");
        Salirbtn.setBounds(270, 200, 100, 30);
        Salirbtn.addActionListener(this);
        this.add(Salirbtn);

        this.setVisible(true);
    }

    private void llenarComboBox() {
        opciones.removeAllItems();
        usuarios jugadorActivo = players.getpa();
        usuarios[] jugadores = players.getjugador();

        // Add all registered players except the active one
        for (int i = 0; i < players.getcantusuarios(); i++) {
            if (jugadores[i] != null) {
                // Add all players except the active one
                if (jugadorActivo == null || !jugadores[i].getUsuario().equals(jugadorActivo.getUsuario())) {
                    opciones.addItem(jugadores[i].getUsuario());
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Salirbtn) {
            new MenuInicial();
            this.dispose();
        } else if (e.getSource() == Jugarbtn) {
            if (opciones.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this,
                        "Debe agregar otro jugador antes de jugar.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String oponente = (String) opciones.getSelectedItem();
                new Tablero(oponente);
                this.dispose();
            }
        }
    }
}
