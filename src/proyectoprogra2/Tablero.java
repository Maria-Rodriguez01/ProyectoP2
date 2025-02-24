package proyectoprogra2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tablero extends JPanel implements ActionListener {

    private boolean turnoprimerjugador = true;
    private final int celdas = 60;
    private static final int filas = 10;
    private static final int columnas = 9;
    private piezas[][] tablero = new piezas[filas][columnas];
    private piezas piezaSeleccionada = null;
    private static players players = new usuarios(null, null);
    private int moveNumber = 1;
    private String oponent;
    protected piezas piezaact = null;
    private JButton retirarseBtn;
    private JFrame parentFrame;
    private JLabel turnoLabel;
    private String jugadorActivo;
    private String jugadorOponente;

    public Tablero(String oponent) {
        this.setPreferredSize(new Dimension(columnas * celdas, filas * celdas + 40));
        this.setLayout(null);
        this.jugadorActivo = players.getpa().getUsuario();
        this.jugadorOponente = oponent;
        this.oponent = oponent;

        // Inicializa el label de turno
        turnoLabel = new JLabel("Turno: " + jugadorActivo + " (Rojo)");
        turnoLabel.setBounds(10, filas * celdas + 5, 200, 30);
        turnoLabel.setForeground(Color.RED);
        this.add(turnoLabel);

        // Create the resign button
        retirarseBtn = new JButton("Retirarse");
        retirarseBtn.setBounds((columnas * celdas - 100) / 2, filas * celdas + 5, 100, 30);
        retirarseBtn.addActionListener(this);
        this.add(retirarseBtn);

        if (players.getpa() == null) {
            JOptionPane.showMessageDialog(null,
                    "Error: No hay jugador activo. Por favor inicie sesión.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        players.getpa().nuevapartida();

        usuarios[] jugadores = players.getjugador();
        boolean opponentFound = false;

        for (int i = 0; i < players.getcantusuarios(); i++) {
            if (jugadores[i] != null && jugadores[i].getUsuario().equals(oponent)) {
                jugadores[i].nuevapartida();
                opponentFound = true;
                break;
            }
        }

        if (!opponentFound) {
            JOptionPane.showMessageDialog(null,
                    "Error: Oponente no encontrado: " + oponent,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.oponent = oponent;

        cargarPiezas();

        addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            int clickX = e.getX() / celdas;
            int clickY = e.getY() / celdas;

            if (clickX >= columnas || clickY >= filas || clickX < 0 || clickY < 0) {
                return;
            }

            if (piezaSeleccionada != null) {
                if (piezaSeleccionada.movimientoValido(clickX, clickY, tablero)) {
                    if (hayPiezaAmiga(clickX, clickY)) {
                        piezaSeleccionada = null;
                        repaint();
                        return;
                    }
                    
                    moverPieza(clickX, clickY);
                    
                    if (capturaGeneral()) {
                        return;
                    }
                    
                    cambiarTurno();
                } else {
                    piezaSeleccionada = null;
                }
                repaint();
                return;
            }

            piezas piezaClickeada = tablero[clickY][clickX];
            if (piezaClickeada != null) {
                String colorTurno = turnoprimerjugador ? "rojo" : "negro";
                if (piezaClickeada.getColor().equals(colorTurno)) {
                    piezaSeleccionada = piezaClickeada;
                    repaint();
                }
            }
        }
    });
    }
    private void cambiarTurno() {
        turnoprimerjugador = !turnoprimerjugador;
        turnoLabel.setText("Turno: " + (turnoprimerjugador ? jugadorActivo + " (Rojo)" : jugadorOponente + " (Negro)"));
        turnoLabel.setForeground(turnoprimerjugador ? Color.RED : Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == retirarseBtn) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea retirarse?",
                "Confirmar retiro",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String ganador;
            String perdedor;
            
            if (turnoprimerjugador) {
                ganador = oponent;
                perdedor = players.getpa().getUsuario();
                
                usuarios[] jugadores = players.getjugador();
                for (int i = 0; i < players.getcantusuarios(); i++) {
                    if (jugadores[i] != null && jugadores[i].getUsuario().equals(oponent)) {
                        jugadores[i].agregarp();
                        break;
                    }
                }
            } else {
                ganador = players.getpa().getUsuario();
                perdedor = oponent;
                players.getpa().agregarp();
            }

            JOptionPane.showMessageDialog(this,
                    perdedor + " se ha retirado.\n¡" + ganador + " ha ganado la partida!",
                    "Fin del juego",
                    JOptionPane.INFORMATION_MESSAGE);
            Container container = this.getParent();
            if (container instanceof JFrame) {
                ((JFrame) container).dispose();
            }
            new MenuInicial().setVisible(true);
        }
    }
}

    private void cargarPiezas() {
        
        //General
        tablero[0][4] = new General(4, 0, "negro", "src/imagenes/generaln.png");
        tablero[9][4] = new General(4, 9, "rojo", "src/imagenes/generalr.png");
        //Advisor
        tablero[0][3] = new Advisor(3, 0, "negro", "src/imagenes/advisorn.png");
        tablero[0][5] = new Advisor(5, 0, "negro", "src/imagenes/advisorn.png");
        tablero[9][3] = new Advisor(3, 9, "rojo", "src/imagenes/advisorr.png");
        tablero[9][5] = new Advisor(5, 9, "rojo", "src/imagenes/advisorr.png");
        //Elephant
        tablero[0][2] = new Elephant(2, 0, "negro", "src/imagenes/elephantn.png");
        tablero[0][6] = new Elephant(6, 0, "negro", "src/imagenes/elephantn.png");
        tablero[9][2] = new Elephant(2, 9, "rojo", "src/imagenes/elephantr.png");
        tablero[9][6] = new Elephant(6, 9, "rojo", "src/imagenes/elephantr.png");
        //Horse
        tablero[0][1] = new Horse(1, 0, "negro", "src/imagenes/horsen.png");
        tablero[0][7] = new Horse(7, 0, "negro", "src/imagenes/horsen.png");
        tablero[9][1] = new Horse(1, 9, "rojo", "src/imagenes/horser.png");
        tablero[9][7] = new Horse(7, 9, "rojo", "src/imagenes/horser.png");
        //Chariot
        tablero[0][0] = new Chariot(0, 0, "negro", "src/imagenes/chariotn.png");
        tablero[0][8] = new Chariot(8, 0, "negro", "src/imagenes/chariotn.png");
        tablero[9][0] = new Chariot(0, 9, "rojo", "src/imagenes/chariotr.png");
        tablero[9][8] = new Chariot(8, 9, "rojo", "src/imagenes/chariotr.png");
        //Cannon
        tablero[2][1] = new Cannon(1, 2, "negro", "src/imagenes/canonn.png");
        tablero[2][7] = new Cannon(7, 2, "negro", "src/imagenes/canonn.png");
        tablero[7][1] = new Cannon(1, 7, "rojo", "src/imagenes/canonr.png");
        tablero[7][7] = new Cannon(7, 7, "rojo", "src/imagenes/canonr.png");
        //Soldiers
        for (int i = 0; i < 9; i += 2) {
            tablero[3][i] = new Soldier(i, 3, "negro", "src/imagenes/soldiern.png");
            tablero[6][i] = new Soldier(i, 6, "rojo", "src/imagenes/soldierr.png");
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(columnas * celdas, filas * celdas + 40);
    }

    private void dibujarTablero(Graphics2D g) {
        g.setColor(new Color(233, 149, 65));
        g.fillRect(0, 0, columnas * celdas, filas * celdas);

        // Dibujar las casillas del tablero en patrón de ajedrez
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                if ((fila + col) % 2 == 0) {
                    g.setColor(new Color(240, 217, 181)); // Color claro
                } else {
                    g.setColor(new Color(181, 136, 99)); // Color oscuro
                }
                g.fillRect(col * celdas, fila * celdas, celdas, celdas);
            }
        }

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3));
        g.drawLine(0, 5 * celdas, columnas * celdas, 5 * celdas); // Línea superior del río

        g.setStroke(new BasicStroke(1));

        g.setColor(Color.BLACK);
        for (int col = 0; col <= columnas; col++) {
            g.drawLine(col * celdas, 0, col * celdas, filas * celdas);
        }

        for (int row = 0; row <= filas; row++) {
            g.drawLine(0, row * celdas, columnas * celdas, row * celdas);
        }

        g.drawLine(3 * celdas, 0, 6 * celdas, 3 * celdas);
        g.drawLine(6 * celdas, 0, 3 * celdas, 3 * celdas);

        g.drawLine(3 * celdas, 7 * celdas, 6 * celdas, 10 * celdas);
        g.drawLine(6 * celdas, 7 * celdas, 3 * celdas, 10 * celdas);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarTablero((Graphics2D) g);
        if (piezaSeleccionada != null) {
            Color mostrar = new Color(0, 250, 20, 100);
            g.setColor(mostrar);

            for (int x = 0; x < columnas; x++) {
                for (int y = 0; y < filas; y++) {
                    if (piezaSeleccionada.movimientoValido(x, y, tablero) && !hayPiezaAmiga(x, y)) {
                        g.fillRect(x * celdas, y * celdas, celdas, celdas);
                    }
                }
            }
        }

        Piezas(g);
    }

    private void Piezas(Graphics g) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] != null) {
                    tablero[i][j].dibujar(g, celdas);
                }
            }
        }
    }

   private boolean hayPiezaAmiga(int x, int y) {
        piezas piezaDestino = tablero[y][x];
        return piezaDestino != null && 
               piezaSeleccionada != null && 
               piezaDestino.getColor().equals(piezaSeleccionada.getColor());
    }


    

    private void declararGanador(boolean esPrimerJugador) {
        String ganador = esPrimerJugador ? players.getpa().getUsuario() : oponent;

        JOptionPane.showMessageDialog(this,
                "¡" + ganador + " ha ganado la partida!",
                "Fin del juego",
                JOptionPane.INFORMATION_MESSAGE);

        if (esPrimerJugador) {
            players.getpa().agregarp();
        } else {
            for (int i = 0; i < players.getcantusuarios(); i++) {
                usuarios[] jugadores = players.getjugador();
                if (jugadores[i] != null && jugadores[i].getUsuario().equals(oponent)) {
                    jugadores[i].agregarp();
                    break;
                }
            }
        }

        SwingUtilities.getWindowAncestor(this).dispose();
        new MenuInicial().setVisible(true);
    }
    private boolean capturaGeneral() {
    boolean generalRojo = false;
    boolean generalNegro = false;

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            if (tablero[i][j] instanceof General) {
                if (tablero[i][j].getColor().equals("rojo")) {
                    generalRojo = true;
                } else if (tablero[i][j].getColor().equals("negro")) {
                    generalNegro = true;
                }
            }
        }
    }

    if (!generalRojo) {
        declararGanador(false); 
        return true;
    } else if (!generalNegro) {
        declararGanador(true); 
        return true;
    }

    return false;
}
   private void moverPieza(int x, int y) {
        if (piezaSeleccionada == null) return;

        int origenX = piezaSeleccionada.getX();
        int origenY = piezaSeleccionada.getY();

        piezaSeleccionada.setX(x);
        piezaSeleccionada.setY(y);

        tablero[y][x] = piezaSeleccionada;
        tablero[origenY][origenX] = null;

        piezaSeleccionada = null;
        repaint();
    }

}
