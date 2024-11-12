package vista.admin;

import javax.swing.*;
import java.awt.*;

import static vista.MenuProvicional.sm;

public class CrearJugadorView {
    public static void crearJugador() {
        JFrame frame = new JFrame("Crear Jugador");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Nombre de usuario:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField();

        JButton submitButton = new JButton("Crear Jugador");
        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username == null || username.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario no puede estar vacío.");
                return;
            }

            if (password == null || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía.");
                return;
            }

            JOptionPane.showMessageDialog(null, sm.createPlayer(username, password));
            frame.dispose();
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(submitButton);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
