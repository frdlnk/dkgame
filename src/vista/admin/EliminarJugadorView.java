package vista.admin;

import models.Player;

import javax.swing.*;
import java.util.UUID;

import static vista.MenuProvicional.sm;

public class EliminarJugadorView {
    public static void eliminarJugador() {
        String[] searchCriteria = {"Nombre", "Nivel", "Puntaje"};
        String selectedCriterion = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona el criterio de búsqueda:",
                "Eliminar Jugador",
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
                mostrarConfirmacionEliminacion(selectedPlayer);
            } else {
                JOptionPane.showMessageDialog(null, "Selección no válida.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

    public static void mostrarConfirmacionEliminacion(Player player) {
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro que deseas eliminar al jugador " + player.getUsername() + "?\n" +
                        "Nivel: " + player.getLevel() + "\n" +
                        "Puntaje: " + player.getScore(),
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            eliminarJugadorDeLista(player);
        }
    }

    public static void eliminarJugadorDeLista(Player player) {
        int indexToDelete = -1;
        for (int i = 0; i < sm.playerCount; i++) {
            if (sm.players[i] != null && sm.players[i].equals(player)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            JOptionPane.showMessageDialog(null, "Jugador no encontrado.");
            return;
        }

        for (int i = indexToDelete; i < sm.playerCount - 1; i++) {
            sm.players[i] = sm.players[i + 1];
        }

        sm.players[sm.playerCount - 1] = null;
        sm.playerCount--;
        reorganizarIds();
        sm.writePlayersToFile(sm.players);
        JOptionPane.showMessageDialog(null, "Jugador eliminado correctamente.");
    }

    public static void reorganizarIds() {
        for (int i = 0; i < sm.playerCount; i++) {
            sm.players[i].setId(UUID.randomUUID());
        }
    }
}
