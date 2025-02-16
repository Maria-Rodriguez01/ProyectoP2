
package proyectoprogra2;


import java.awt.*;

import javax.swing.*;


public class MenuPrincipal extends JFrame {
    
    
    
    public MenuPrincipal(){
    // Configuración de la ventana
        setTitle("Menú Principal - Xiangqi");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         

        
        JLabel titleLabel = new JLabel(" BIENVENIDO A XIANGQI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.RED); // Color rojo para destacar
        add(titleLabel, BorderLayout.NORTH); // Se coloca en la parte superior

        // Panel para centrar los botones
        JPanel centerPanel = new JPanel(new GridBagLayout()); // Usa GridBagLayout para centrar
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE; // Se posicionan uno debajo del otro
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20); // Espaciado alrededor de los botones

        // Crear botones
        JButton btnRegistro = new JButton("Registrarse");
        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnSalir = new JButton("Salir");

        // Agregar acciones a los botones
        btnRegistro.addActionListener(e -> abrirRegistro());
        btnLogin.addActionListener(e -> abrirLogin());
        btnSalir.addActionListener(e -> System.exit(0));

        // Agregar botones al panel centrado
        centerPanel.add(btnRegistro, gbc);
        centerPanel.add(btnLogin, gbc);
        centerPanel.add(btnSalir, gbc);

        // Agregar el panel centrado a la ventana
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Métodos para abrir otras ventanas (Placeholder)
    private void abrirRegistro() {
        registro r=new registro();
        r.setVisible(true);
        this.dispose();
        
        
    }

    private void abrirLogin() {
       InicioSesion is=new InicioSesion(jugadores, indice);
       is.setVisible(true);
       this.dispose();
    }

}
