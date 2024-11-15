package vista;

import models.Player;
import utils.StateManager;
import vista.admin.ActualizarJugador;
import vista.admin.BuscarJugadorView;
import vista.admin.CrearJugadorView;
import vista.admin.EliminarJugadorView;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class MenuProvicional {
    public static StateManager sm = new StateManager();

    public static void mostrarMenuPrincipal() {
        JFrame menuFrame = new JFrame("Menú Principal");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(400, 300);
        menuFrame.setLayout(new FlowLayout());

        JButton crearJugadorButton = new JButton("Crear Jugador");
        JButton iniciarSesionButton = new JButton("Iniciar Sesión");
        JButton buscarJugadoresButton = new JButton("Buscar Jugadores");
        JButton actualizarContraseñaButton = new JButton("Actualizar Contraseña");
        JButton eliminarJugadorButton = new JButton("Eliminar Jugador");
        JButton salirButton = new JButton("Salir");

        crearJugadorButton.addActionListener(e -> CrearJugadorView.crearJugador());
        iniciarSesionButton.addActionListener(e -> iniciarSesion());
        buscarJugadoresButton.addActionListener(e -> BuscarJugadorView.buscarYMostrarUsuarios());
        actualizarContraseñaButton.addActionListener(e -> ActualizarJugador.actualizarContraseña());
        eliminarJugadorButton.addActionListener(e -> EliminarJugadorView.eliminarJugador());
        salirButton.addActionListener(e -> System.exit(0));

        menuFrame.add(crearJugadorButton);
        menuFrame.add(iniciarSesionButton);
        menuFrame.add(buscarJugadoresButton);
        menuFrame.add(actualizarContraseñaButton);
        menuFrame.add(eliminarJugadorButton);
        menuFrame.add(salirButton);

        menuFrame.setVisible(true);
    }

    public static void iniciarSesion() {
        sm.readPlayers();

        if (sm.playerCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay jugadores registrados. Crea uno primero.");
            return;
        }

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
