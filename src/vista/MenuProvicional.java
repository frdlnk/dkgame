package vista;

import models.Player;
import utils.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        crearJugadorButton.addActionListener(e -> crearJugador());
        iniciarSesionButton.addActionListener(e -> iniciarSesion());
        buscarJugadoresButton.addActionListener(e -> buscarYMostrarUsuarios());
        actualizarContraseñaButton.addActionListener(e -> actualizarContraseña());
        eliminarJugadorButton.addActionListener(e -> eliminarJugador());
        salirButton.addActionListener(e -> System.exit(0));

        menuFrame.add(crearJugadorButton);
        menuFrame.add(iniciarSesionButton);
        menuFrame.add(buscarJugadoresButton);
        menuFrame.add(actualizarContraseñaButton);
        menuFrame.add(eliminarJugadorButton);
        menuFrame.add(salirButton);

        menuFrame.setVisible(true);
    }

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
        panel.add(new JLabel()); // empty placeholder
        panel.add(submitButton);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setVisible(true);
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
