package proyectoprogra2;

import java.awt.*;

import javax.swing.*;

public class registro extends JFrame {
    private final JTextField txtUsuario;
    private final JPasswordField txtContrasena;
    private final JButton btnRegistrar;
    private final JButton btnSalir;
    private final usuarios sistema = usuarios.obtenerInstancia();
    private final usuarios[] jugadores = sistema.getjugador();

    public registro() {
        setTitle("Registro de Jugadores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel for input fields with improved layout
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

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnRegistrar = new JButton("Registrar");
        btnSalir = new JButton("Salir");
        
        btnRegistrar.setPreferredSize(new Dimension(100, 30));
        btnSalir.setPreferredSize(new Dimension(100, 30));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnSalir);

        // Register button action
        btnRegistrar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String contrasena = new String(txtContrasena.getPassword()).trim();
            registrarJugador(usuario, contrasena);
        });

        btnSalir.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea salir del registro?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION
            );
            if (option == JOptionPane.YES_OPTION) {
                MenuPrincipal regreso = new MenuPrincipal();
                regreso.setVisible(true);
                dispose();
            }
        });

        setLayout(new BorderLayout());
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

   private boolean registrarJugador(String usu, String password) {
        try {
            // Validaciones
            if (usu.isEmpty() || password.isEmpty()) {
                mostrarError("Por favor, complete ambos campos.");
                return false;
            }
            
             if (password.length() != 5) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener exactamente 5 caracteres");
            return false;
        }

            // Verificar usuario existente
            usuarios[] jugadores = sistema.getjugador();
            for (int i = 0; i < sistema.getcantusuarios(); i++) {
                if (jugadores[i] != null && 
                    jugadores[i].getUsuario().equalsIgnoreCase(usu)) {
                    mostrarError("El usuario ya existe.");
                    return false;
                }
            }

            // Crear y registrar nuevo usuario
            usuarios nuevoJugador = new usuarios(usu, password);
            sistema.addusuario(nuevoJugador);
            sistema.setpa(nuevoJugador);
            
            JOptionPane.showMessageDialog(this, 
                "Registro exitoso", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);

            MenuInicial regreso = new MenuInicial();
            regreso.setVisible(true);
            dispose();
            return true;

        } catch (Exception e) {
            mostrarError("Error en el registro: " + e.getMessage());
            return false;
        }
    }
   private void mostrarError(String mensaje) {
    JOptionPane.showMessageDialog(
        this,
        mensaje,
        "Error de Registro",
        JOptionPane.ERROR_MESSAGE
    );

}
}