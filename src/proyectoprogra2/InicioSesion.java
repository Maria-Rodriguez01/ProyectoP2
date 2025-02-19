package proyectoprogra2;

import java.awt.*;
import javax.swing.*;

public class InicioSesion extends JFrame {
    private final JTextField txtUsuario;
    private final JPasswordField txtContrasena;
    private final JButton btnIngresar;
    private final JButton btnSalir;
    private final usuarios sistema = usuarios.obtenerInstancia();
    
    public InicioSesion() {
        setTitle("Login de Jugadores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Panel setup with GridBagLayout
        JPanel panelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Username field setup
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCampos.add(new JLabel("Usuario:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtUsuario = new JTextField(20);
        panelCampos.add(txtUsuario, gbc);
        
        // Password field setup
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panelCampos.add(new JLabel("Contraseña:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtContrasena = new JPasswordField(20);
        panelCampos.add(txtContrasena, gbc);
        
        // Buttons panel
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnIngresar = new JButton("Ingresar");
        btnSalir = new JButton("Salir");
        
        btnIngresar.setPreferredSize(new Dimension(100, 30));
        btnSalir.setPreferredSize(new Dimension(100, 30));
        
        panelBotones.add(btnIngresar);
        panelBotones.add(btnSalir);
        
        // Action listeners
        btnIngresar.addActionListener(e -> validarLogin());
        btnSalir.addActionListener(e -> {
            MenuPrincipal regreso = new MenuPrincipal();
            regreso.setVisible(true);
            dispose();
        });
        
        // Main layout setup
        setLayout(new BorderLayout());
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private void validarLogin() {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtContrasena.getPassword()).trim();
        
        // Validate input fields
        if (usuario.isEmpty() || password.isEmpty()) {
            mostrarError("Por favor, complete ambos campos.");
            return;
        }

        usuarios[] jugadores = sistema.getjugador(); // Obtener lista de jugadores
        if (jugadores == null) {
            mostrarError("No hay jugadores registrados.");
            return;
        }

        int contadorJugadores = sistema.getcantusuarios();
        boolean loginlogrado = false;

        for (int i = 0; i < contadorJugadores; i++) {
            if (jugadores[i].getUsuario().equalsIgnoreCase(usuario) && 
                jugadores[i].getContrasena().equals(password)) {
                
                sistema.setpa(jugadores[i]); // Establecer el usuario como activo
                loginlogrado = true;

                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");

                // Mostrar el menú principal
                MenuInicial menu = new MenuInicial();
                menu.setVisible(true);

                this.dispose(); // Cierra la ventana actual
                return;
            }
        }

        mostrarError("Usuario o contraseña incorrectos.");
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Error de Inicio de Sesión",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
