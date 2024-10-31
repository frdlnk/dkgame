package models;

import java.io.*;
import java.util.UUID;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String username;
    private String password;
    private int score;
    private int level;

    private static final String DATA_FILE = "data.json";

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void updatePassword(String username, String currentPassword, String newPassword) {
        Player[] players = readPlayersFromFile();
        if (players == null) {
            System.out.println("El archivo data.json no existe o está vacío.");
            return;
        }

        boolean userFound = false;
        for (Player player : players) {
            if (player != null && player.getUsername().equals(username)) {
                if (player.getPassword().equals(currentPassword)) {
                    player.setPassword(newPassword); // Actualiza la contraseña
                    userFound = true;
                    break;
                } else {
                    System.out.println("La contraseña actual no coincide.");
                    return;
                }
            }
        }

        if (!userFound) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        writePlayersToFile(players);
        System.out.println("Contraseña actualizada correctamente.");
    }

    private Player[] readPlayersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (Player[]) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo data.json no existe.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writePlayersToFile(Player[] players) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir el archivo JSON.");
        }
    }
}
