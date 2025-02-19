package proyectoprogra2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class reportes extends JFrame implements ActionListener {
    private final JButton btnRanking;
    private final JButton btnRegresarMenu;
    private final usuarios sistema;
    private final usuarios[] jugadores;
    
    public reportes() {

        sistema = usuarios.obtenerInstancia();
        jugadores = sistema.getjugador();
        configurarVentana();

        btnRanking = crearBoton("Ranking de los Jugadores", 50, 50);
        btnRegresarMenu = crearBoton("Regresar al Menú", 50, 100);
      
        add(btnRanking);
        add(btnRegresarMenu);
    }
    
    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("XIANQI Reportes");
        setLayout(null);
        setSize(400, 200);  
        setLocationRelativeTo(null);
        
        JPanel backgroundPanel = new JPanel(null);
        backgroundPanel.setBounds(0, 0, 400, 200);  
        backgroundPanel.setBackground(new Color(240, 240, 240));
        add(backgroundPanel);
    }
    
    private JButton crearBoton(String texto, int x, int y) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, 300, 35);  
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.addActionListener(this);
        boton.setFocusPainted(false);
        boton.setBackground(new Color(220, 220, 220));
        return boton;
    }
    
    private void mostrarRanking() {
        int cantUsuarios = sistema.getcantusuarios();
        if (cantUsuarios == 0) {
            JOptionPane.showMessageDialog(this, 
                "No hay jugadores registrados", 
                "Ranking Vacío", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        
        usuarios[] jugadoresCopia = new usuarios[cantUsuarios];
        System.arraycopy(jugadores, 0, jugadoresCopia, 0, cantUsuarios);
        
      
        ordenarJugadoresPorPuntaje(jugadoresCopia, cantUsuarios);
        
        String mensaje = crearMensajeRanking(jugadoresCopia, cantUsuarios);
        
        mostrarMensajeConScroll(mensaje, "Ranking de Jugadores");
    }
    
    private void ordenarJugadoresPorPuntaje(usuarios[] jugadores, int cantidad) {
        for (int i = 0; i < cantidad - 1; i++) {
            for (int j = 0; j < cantidad - i - 1; j++) {
                if (jugadores[j].getPuntaje() < jugadores[j + 1].getPuntaje()) {
                    usuarios temp = jugadores[j];
                    jugadores[j] = jugadores[j + 1];
                    jugadores[j + 1] = temp;
                }
            }
        }
    }
    
    private String crearMensajeRanking(usuarios[] jugadores, int cantidad) {
        StringBuilder mensaje = new StringBuilder("Ranking de Jugadores\n\n");
        for (int i = 0; i < cantidad; i++) {
            mensaje.append(String.format("%d. %-20s Puntos: %-5d Partidas: %-3d\n",
                i + 1,
                jugadores[i].getUsuario(),
                jugadores[i].getPuntaje(),
                jugadores[i].getCantpart()));
        }
        return mensaje.toString();
    }
    
    private void mostrarMensajeConScroll(String mensaje, String titulo) {
        JTextArea textArea = new JTextArea(mensaje);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        
        JOptionPane.showMessageDialog(this, 
            scrollPane, 
            titulo, 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRanking) {
            mostrarRanking();
        } else if (e.getSource() == btnRegresarMenu) {
            new MenuInicial();
            dispose();
        }
    }
}