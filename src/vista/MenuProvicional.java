package vista;

import models.Player;
import utils.StateManager;

import javax.swing.*;
import models.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuProvicional {
    static StateManager sm = new StateManager();
    public static void mostrarMenuPrincipal() {
        // Mientras el usuario no haya cerrado la aplicación, seguir mostrando el menú
        while (true) {
            // Mostrar el menú principal con opciones
            String[] options = {"Crear Jugador", "Iniciar Sesión", "Salir"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Selecciona una opción:",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (choice == 0) {
                // Opción 0: Crear un nuevo jugador
                crearJugador();
            } else if (choice == 1) {
                // Opción 1: Iniciar sesión
                iniciarSesion();
            } else if (choice == 2) {
                // Opción 2: Salir
                System.exit(0);
            }
        }
    }

    public static void crearJugador() {
        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario no puede estar vacío.");
            return;
        }

        String password = JOptionPane.showInputDialog("Ingrese la contraseña:");
        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía.");
            return;
        }

        // Crear un nuevo jugador
        JOptionPane.showMessageDialog(null, sm.createPlayer(username, password));
    }

    public static void iniciarSesion() {
        // Leer los jugadores existentes
        sm.readPlayers();

        if (sm.playerCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay jugadores registrados. Crea uno primero.");
            return;
        }

        // Mostrar los jugadores existentes
        StringBuilder playersList = new StringBuilder("Selecciona un jugador:\n");
        for (int i = 0; i < sm.playerCount; i++) {
            Player player = sm.players[i];
            if (player != null) {
                playersList.append(i + 1).append(". ").append(player.getUsername()).append("\n");
            }
        }

        String input = JOptionPane.showInputDialog(playersList.toString());
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        try {
            int choice = Integer.parseInt(input.trim()) - 1;
            if (choice >= 0 && choice < sm.playerCount) {
                Player player = sm.players[choice];
                String password = JOptionPane.showInputDialog("Ingrese la contraseña para el jugador " + player.getUsername() + ":");

                if (player.getPassword().equals(password)) {
                    sm.login(player.getId().toString());
                    JOptionPane.showMessageDialog(null, "Bienvenido, " + player.getUsername());
                    mostrarMenuSesion();
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

    public static void mostrarMenuSesion() {
        while (StateManager.islogged) {
            String[] options = {"Logout"};
            int choice = JOptionPane.showOptionDialog(null,
                    String.format("Sesión iniciada como: %s", StateManager.currentPlayerOnSession.getUsername()),
                    "Menú de Sesión",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (choice == 0) {

                sm.logout();
                JOptionPane.showMessageDialog(null, "Has cerrado sesión.");
                break;
            }
        }
    }
}
