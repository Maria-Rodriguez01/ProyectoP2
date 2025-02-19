/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra2;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;


public class MenuInicial extends JFrame{
     private final usuarios sistema = usuarios.obtenerInstancia();
    public MenuInicial() {
        setTitle("Menú Principal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        
        Font font = new Font("Arial", Font.BOLD, 18);
        Color color= new Color(201, 24, 42);

        // 🔹 Etiqueta de título
        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);
        titulo.setForeground(color);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(color);

        // 🔹 Crear botones
        JButton btnJugar = new JButton("Jugar Xiangqi");
        JButton btnCuenta = new JButton("Mi Cuenta");
        JButton btnReportes = new JButton("Reportes");
        JButton btnLogout = new JButton("Logout");

        btnJugar.setFont(font);
        btnCuenta.setFont(font);
        btnReportes.setFont(font);
        btnLogout.setFont(font);
        btnJugar.setForeground(color);
        btnCuenta.setForeground(color);
        btnReportes.setForeground(color);
        btnLogout.setForeground(color);

        // 🔹 Centrar botones dentro del panel
        btnJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReportes.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnJugar.setAlignmentY(Component.CENTER_ALIGNMENT);
        btnCuenta.setAlignmentY(Component.CENTER_ALIGNMENT);
        btnReportes.setAlignmentY(Component.CENTER_ALIGNMENT);
        btnLogout.setAlignmentY(Component.CENTER_ALIGNMENT);

        // 🔹 Agregar espaciado entre botones

        panelBotones.add(btnJugar);
  
        panelBotones.add(btnCuenta);

        panelBotones.add(btnReportes);

        panelBotones.add(btnLogout);

        add(panelBotones, BorderLayout.CENTER);
        
        btnJugar.addActionListener((ActionEvent e) -> {
           new tablerovisible() ;
           this.dispose();
        });
        btnLogout.addActionListener((ActionEvent e) -> {
            HacerLogout();
            new MenuPrincipal();
            this.dispose();
            
        });
        btnCuenta.addActionListener((ActionEvent e) -> {
           new micuenta() ;
           this.dispose();
        });
        btnReportes.addActionListener((ActionEvent e) -> {
           reportes rep=new reportes() ;
           rep.setVisible(true);
           this.dispose();
        });

        setVisible(true);
    }
    public void HacerLogout(){
        sistema.setpa(null);
    }
}
