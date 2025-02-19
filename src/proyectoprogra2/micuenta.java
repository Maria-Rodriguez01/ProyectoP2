package proyectoprogra2;

import javax.swing.*;
import java.awt.*;

public final class micuenta extends JFrame {
    private JLabel lblJugador;
    private JLabel lblPartidas;
    private JLabel lblPuntos;
    private JButton btnCambiarContrasena;
    private JButton btnEliminarCuenta;
    private JButton btnRegresar;
    private final usuarios jugadorActual;

    public micuenta() {
    usuarios gestorUsuarios = usuarios.obtenerInstancia();
    jugadorActual = gestorUsuarios.getpa();

    if (jugadorActual == null) {
        JOptionPane.showMessageDialog(this, "No hay usuario logueado.", "Error", JOptionPane.ERROR_MESSAGE);
        return; 
    }

    Labelsybotones();
    Ventana();
    Agregar();
    this.setVisible(true);
}

    private void Labelsybotones() {
        lblJugador = new JLabel("Jugador: " + jugadorActual.getUsuario());
        lblPartidas = new JLabel("Partidas jugadas: " + jugadorActual.getCantpart());
        lblPuntos = new JLabel("Puntos totales: " + jugadorActual.getPuntaje());

        btnCambiarContrasena = new JButton("Cambiar Contraseña");
        btnEliminarCuenta = new JButton("Eliminar Cuenta");
        btnRegresar = new JButton("Regresar al Menú");

        btnCambiarContrasena.addActionListener(e -> cambiarContrasena());
        btnEliminarCuenta.addActionListener(e -> eliminarCuenta());
        btnRegresar.addActionListener(e -> regresarMenu());
    }

    private void Ventana() {
        setTitle("Mi Cuenta");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
    }

    private void Agregar() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(lblJugador);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(lblPartidas);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(lblPuntos);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnCambiarContrasena);
        buttonPanel.add(btnEliminarCuenta);
        buttonPanel.add(btnRegresar);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(infoPanel, gbc);

        gbc.gridy = 1;
        add(buttonPanel, gbc);
    }

    private void cambiarContrasena() {
        String viejaContrasena = JOptionPane.showInputDialog(this, "Ingresar la contraseña actual", "Cambiar Contraseña", JOptionPane.QUESTION_MESSAGE);

        if (viejaContrasena == null) return;

        if (viejaContrasena.equals(jugadorActual.getContrasena())) {
            String nuevaContrasena = JOptionPane.showInputDialog(this, "Favor ingresar nueva contraseña", "Cambiar Contraseña", JOptionPane.QUESTION_MESSAGE);

            if (nuevaContrasena != null && !nuevaContrasena.trim().isEmpty()) {
                jugadorActual.setContrasena(nuevaContrasena);
                JOptionPane.showMessageDialog(this, "Contraseña cambiada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "La nueva contraseña no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Contraseña actual incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCuenta() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar su cuenta?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String contra=JOptionPane.showInputDialog(null, "Ingrese la contrasena para poder eliminar cuenta");
            if(contra.equals(jugadorActual.getContrasena())){
            usuarios gestorUsuarios = usuarios.obtenerInstancia();
            gestorUsuarios.eliminarusuario(jugadorActual);

            JOptionPane.showMessageDialog(this, "Cuenta eliminada exitosamente", "Cuenta Eliminada", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new MenuPrincipal();
            }
            JOptionPane.showMessageDialog(null, "Contrasena incorrecta");
        }
    }

    private void regresarMenu() {
        new MenuInicial();
        dispose();
    }
}

