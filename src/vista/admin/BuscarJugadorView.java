package vista.admin;

import models.Player;
import utils.StateManager;

import javax.swing.*;

import static vista.admin.ActualizarJugador.mostrarInformacionJugador;


public class BuscarJugadorView {
    public static StateManager sm = new StateManager();

    public static void buscarYMostrarUsuarios() {
        String[] searchCriteria = {"Nombre", "Nivel", "Puntaje"};
        String selectedCriterion = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona el criterio de búsqueda:",
                "Buscar Jugadores",
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
                    results.append(i + 1).append(". ").append(player.getUsername()).append(" - Nivel: ")
                            .append(player.getLevel()).append(", Puntaje: ").append(player.getScore()).append("\n");
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
                mostrarInformacionJugador(selectedPlayer);
            } else {
                JOptionPane.showMessageDialog(null, "Selección no válida.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

}
