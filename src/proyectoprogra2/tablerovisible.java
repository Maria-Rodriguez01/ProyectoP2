package proyectoprogra2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tablerovisible extends JFrame implements ActionListener {
    private JLabel JugadorALabel;
    private JComboBox<String> opciones;
    private JButton Salirbtn, Jugarbtn;
    // Use the singleton instance
    private final usuarios sistema = usuarios.obtenerInstancia();
    
    public tablerovisible() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("XIANQI Oponente");
        this.setLayout(null);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        
        if (sistema.getpa() != null) {
            JugadorALabel = new JLabel("Jugador Activo: " + sistema.getpa().getUsuario());
        } else {
            JugadorALabel = new JLabel("No hay jugador activo");
            JOptionPane.showMessageDialog(this,
                    "No hay jugador activo. Por favor inicie sesión primero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            // Return to previous menu
            new MenuInicial();
            this.dispose();
            return;
        }
        
        JugadorALabel.setBounds(150, 100, 200, 25);
        JugadorALabel.setForeground(Color.black);
        JugadorALabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(JugadorALabel);
        
        opciones = new JComboBox<>();
        opciones.setBounds(150, 150, 200, 25);
        this.add(opciones);
        llenarComboBox();
        
        Jugarbtn = new JButton("Jugar!");
        Jugarbtn.setBounds(150, 200, 100, 30);
        Jugarbtn.addActionListener(this);
        this.add(Jugarbtn);
        
        Salirbtn = new JButton("Salir");
        Salirbtn.setBounds(270, 200, 100, 30);
        Salirbtn.addActionListener(this);
        this.add(Salirbtn);
        
        this.setVisible(true);
    }
    
    private void llenarComboBox() {
        opciones.removeAllItems();
        usuarios jugadorActivo = sistema.getpa();
        usuarios[] jugadores = sistema.getjugador();
        
        for (int i = 0; i < sistema.getcantusuarios(); i++) {
            if (jugadores[i] != null) {
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
                try {
                    // Asegurarnos que el jugador activo existe
                    if (sistema.getpa() == null) {
                        JOptionPane.showMessageDialog(this,
                                "No hay jugador activo. Por favor inicie sesión primero.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        new MenuInicial();
                        this.dispose();
                        return;
                    }
                    
                    String oponenteNombre = (String) opciones.getSelectedItem();
                    
                    usuarios oponente = null;
                    
                    usuarios[] jugadores = sistema.getjugador();
                    for (int i = 0; i < sistema.getcantusuarios(); i++) {
                        if (jugadores[i] != null && jugadores[i].getUsuario().equals(oponenteNombre)) {
                            oponente = jugadores[i];
                            break;
                        }
                    }
                    
                    if (oponente == null) {
                        JOptionPane.showMessageDialog(this,
                                "No se pudo encontrar el oponente seleccionado.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    try {
                        Class<?> tableroClass = Class.forName("proyectoprogra2.Tablero");
                        
                        try {
                            java.lang.reflect.Field playersField = tableroClass.getDeclaredField("players");
                            playersField.setAccessible(true);
                            playersField.set(null, sistema);
                        } catch (NoSuchFieldException nsfe) {
                            System.out.println("No se encontró el campo 'players'");
                        }
                        
                        try {
                            java.lang.reflect.Field jugador1Field = tableroClass.getDeclaredField("jugador1");
                            jugador1Field.setAccessible(true);
                            jugador1Field.set(null, sistema.getpa());
                        } catch (NoSuchFieldException nsfe) {
                           
                        }
                        
                        try {
                            java.lang.reflect.Field jugador2Field = tableroClass.getDeclaredField("jugador2");
                            jugador2Field.setAccessible(true);
                            jugador2Field.set(null, oponente);
                        } catch (NoSuchFieldException nsfe) {
                            
                        }
                    } catch (Exception ex) {
                        System.err.println("Error al configurar campos: " + ex.getMessage());
                    }
                    
                    JFrame gameFrame = new JFrame("Xiangqi - Ajedrez Chino");
                    gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    
                    Tablero tablero = new Tablero(oponenteNombre);
                    
                    gameFrame.add(tablero);
                    
                    gameFrame.pack();
                    gameFrame.setLocationRelativeTo(null);
                    gameFrame.setVisible(true);
                    
                    this.dispose();
                    
                } catch (Exception ex) {
                    StringBuilder errorMsg = new StringBuilder();
                    errorMsg.append("Error al abrir el tablero: ").append(ex.getMessage()).append("\n");
                    errorMsg.append("Tipo: ").append(ex.getClass().getName()).append("\n");
                    
                    if (ex.getStackTrace().length > 0) {
                        errorMsg.append("Ubicación: ").append(ex.getStackTrace()[0].getClassName())
                               .append(".").append(ex.getStackTrace()[0].getMethodName())
                               .append(" (línea ").append(ex.getStackTrace()[0].getLineNumber()).append(")");
                    }
                    
                    if (ex.getCause() != null) {
                        errorMsg.append("\nCausa: ").append(ex.getCause().getMessage());
                    }
                    
                    JOptionPane.showMessageDialog(this,
                            errorMsg.toString(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
    }
}