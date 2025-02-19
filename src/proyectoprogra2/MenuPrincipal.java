
package proyectoprogra2;


import java.awt.*;

import javax.swing.*;


public class MenuPrincipal extends JFrame {
    
    
    
    public MenuPrincipal(){
        setTitle("Menú Principal - Xiangqi");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         

        
        JLabel titleLabel = new JLabel(" BIENVENIDO A XIANGQI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.RED); 
        add(titleLabel, BorderLayout.NORTH); 

        // Panel para centrar los botones
        JPanel centerPanel = new JPanel(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20); 

        JButton btnRegistro = new JButton("Registrarse");
        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnSalir = new JButton("Salir");

        btnRegistro.addActionListener(e -> abrirRegistro());
        btnLogin.addActionListener(e -> abrirLogin());
        btnSalir.addActionListener(e -> System.exit(0));

        centerPanel.add(btnRegistro, gbc);
        centerPanel.add(btnLogin, gbc);
        centerPanel.add(btnSalir, gbc);

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void abrirRegistro() {
        registro r=new registro();
        r.setVisible(true);
        this.dispose();
        
        
    }

    private void abrirLogin() {
        InicioSesion is=new InicioSesion();
        is.setVisible(true);
        this.dispose();
    }

}
