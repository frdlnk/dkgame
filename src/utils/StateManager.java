package utils;

import models.Player;

import java.io.*;
import java.util.UUID;

public class StateManager {

    private static final String DATA_FILE = "data.json";
    private static Player[] players = new Player[10];
    private static int playerCount = 0;
    public static Player currentPlayerOnSession;
    public static boolean islogged = false;

    public Object readPlayers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            players = (Player[]) ois.readObject();
            playerCount = players.length;

            for (Player player : players) {
                if (player != null) {
                    System.out.println("Id: " + player.getId() + " Jugador: " + player.getUsername() + ", Nivel: " + player.getLevel() + ", Puntaje: " + player.getScore());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo data.json no existe. Se creará un archivo nuevo.");
            createNewFile();
        } catch (IOException | ClassNotFoundException e) {

        }
        return null;
    }

    public Player getPlayerById(String playerId) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i] != null && players[i].getId().toString().equals(playerId)) {
                return players[i];
            }
        }
        return null;
    }

    public void createPlayer(String username, String password) {
        // Leemos los jugadores existentes
        readPlayers();

        // Verificamos si el nombre de usuario ya existe
        for (Player player : players) {
            if (player != null && player.getUsername().equals(username)) {
                System.out.println("Error: El nombre de usuario '" + username + "' ya está en uso. Intenta con otro nombre.");
                return; // No creamos el jugador si el nombre ya existe
            }
        }

        // Si no existe, creamos el nuevo jugador
        Player newPlayer = new Player(username, password);
        newPlayer.setId(UUID.randomUUID());
        newPlayer.setLevel(1);
        newPlayer.setScore(0);

        players[playerCount++] = newPlayer;

        writePlayersToFile();
        System.out.println("Jugador " + username + " creado exitosamente.");
    }


    private void writePlayersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir el archivo JSON.");
        }
    }

    private void createNewFile() {
        try {
            new File(DATA_FILE).createNewFile();
            System.out.println("Archivo data.json creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayerData(String playerId, int newScore, int newLevel) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i] != null && players[i].getId().toString().equals(playerId)) {
                players[i].setScore(newScore);
                players[i].setLevel(newLevel);
                writePlayersToFile();
                System.out.println("Datos del jugador " + playerId + " actualizados correctamente.");
                return;
            }
        }
        System.out.println("Jugador no encontrado.");
    }

    public void login(String id) {
        for (Player player : players) {
            if(player != null && player.getId().toString().equals(id)) {
                currentPlayerOnSession = player;
                islogged = true;
            }
        }

    }

    public void logout() {
        currentPlayerOnSession = null;
        islogged = false;
    }
}
