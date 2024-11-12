package vista.admin;

import models.Player;

import javax.swing.*;
import java.awt.*;

import static vista.MenuProvicional.sm;

public class ActualizarJugador {
    public static void mostrarInformacionJugador(Player player) {
        String playerInfo = String.format(
                "Nombre de usuario: %s\nNivel: %d\nPuntaje: %d\nID: %s\nCorreo electrónico: %s",
                player.getUsername(),
                player.getLevel(),
                player.getScore(),
                player.getId()
        );
        JOptionPane.showMessageDialog(null, playerInfo, "Información del Jugador", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void actualizarContraseña() {
        String[] searchCriteria = {"Nombre", "Nivel", "Puntaje"};
        String selectedCriterion = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona el criterio de búsqueda:",
                "Actualizar Contraseña",
                JOptionPane.QUESTION_MESSAGE,
                null,
                searchCriteria,
                searchCriteria[0]
        );

        if (selectedCriterion == null) {
            return;
        }

        String searchQuery = JOptionPane.showInputDialog("Ingresa el valor para buscar por " + selectedCriterion + ":");

        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            return;
        }

        StringBuilder results = new StringBuilder("Resultados de la búsqueda por " + selectedCriterion + ":\n");
        Player[] searchResults = new Player[sm.playerCount];
        int resultCount = 0;

        for (int i = 0; i < sm.playerCount; i++) {
            Player player = sm.players[i];
            if (player != null) {
                boolean match = false;
                switch (selectedCriterion) {
                    case "Nombre":
                        if (player.getUsername().toLowerCase().contains(searchQuery.toLowerCase())) {
                            match = true;
                        }
                        break;
                    case "Nivel":
                        if (String.valueOf(player.getLevel()).equals(searchQuery.trim())) {
                            match = true;
                        }
                        break;
                    case "Puntaje":
                        if (String.valueOf(player.getScore()).equals(searchQuery.trim())) {
                            match = true;
                        }
                        break;
                }
                if (match) {
                    results.append(i + 1).append(". ").append(player.getUsername())
                            .append(" - Nivel: ").append(player.getLevel())
                            .append(", Puntaje: ").append(player.getScore()).append("\n");
                    searchResults[resultCount++] = player;
                }
            }
        }

        if (resultCount == 0) {
            JOptionPane.showMessageDialog(null, "No se encontraron jugadores que coincidan con la búsqueda.");
            return;
        }

        String input = JOptionPane.showInputDialog(results.toString());

        if (input == null || input.trim().isEmpty()) {
            return;
        }

        try {
            int choice = Integer.parseInt(input.trim()) - 1;
            if (choice >= 0 && choice < resultCount) {
                Player selectedPlayer = searchResults[choice];
                mostrarFormularioActualizarContraseña(selectedPlayer);
            } else {
                JOptionPane.showMessageDialog(null, "Selección no válida.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }


    public static void mostrarFormularioActualizarContraseña(Player player) {
        JFrame frame = new JFrame("Actualizar Contraseña");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel newPasswordLabel = new JLabel("Nueva Contraseña:");
        JPasswordField newPasswordField = new JPasswordField();

        JButton updateButton = new JButton("Actualizar Contraseña");
        updateButton.addActionListener(e -> {
            String newPassword = new String(newPasswordField.getPassword());

            if (newPassword == null || newPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía.");
                return;
            }

            player.setPassword(newPassword.trim());
            sm.writePlayersToFile(sm.players);
            JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente.");
            frame.dispose();
        });

        panel.add(newPasswordLabel);
        panel.add(newPasswordField);
        panel.add(updateButton);

        frame.add(panel);
        frame.setSize(400, 150);
        frame.setVisible(true);
    }

}
