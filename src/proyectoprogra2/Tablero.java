/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra2;

import java.awt.*;
import javax.swing.*;


public class Tablero extends JFrame {
    private static final int ROWS = 11; // Número de filas
    private static final int COLS = 9; // Número de columnas
    private final JPanel[][] tablero = new JPanel[ROWS][COLS]; // Matriz para almacenar los paneles

    public Tablero() {
        JFrame frame = new JFrame("Xiangqui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        
        
         // Paneles para dejar espacio en la parte superior e inferior
        JPanel espacio1 = new JPanel();
        JPanel espacio2 = new JPanel();
        espacio1.setPreferredSize(new Dimension(600, 50));
        espacio2.setPreferredSize(new Dimension(600, 50));
        
        
        Color color1 = new Color(238, 238, 210); // Crema claro
        Color color2 = new Color(118, 150, 86);  // Verde oscuro
        Color colorRio = new Color(173, 216, 230); // Azul claro para el río 
        
        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                // Definir bordes más gruesos para la parte central 3x3 en los extremos superior e inferior
                if ((row < 3 || row >= ROWS - 3) && (col >= (COLS / 2) - 1 && col <= (COLS / 2) + 1)) {
                    cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                } else {
                    cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                
                // Alternar colores como tablero de ajedrez
                if (row == ROWS / 2) {
                    cell.setBackground(colorRio); // Línea central azul4
                    
                } else {
                    if ((row + col) % 2 == 0) {
                        cell.setBackground(color1);
                    } else {
                        cell.setBackground(color2);
                    }
                }
                
                tablero[row][col] = cell; // Guardar en el arreglo
                boardPanel.add(cell);
            }
        }
        
        // Botón de resignación
        JButton resignar = new JButton("Resignarse");
        resignar.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Te has rendido."));
        
        
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(espacio2,BorderLayout.SOUTH);
        espacio2.add(resignar, BorderLayout.SOUTH);
        frame.add(espacio1,BorderLayout.NORTH);
        frame.setVisible(true);
}
}
