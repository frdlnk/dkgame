package models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;



public class Player {
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
        try (FileReader reader = new FileReader(DATA_FILE)) {
            JSONParser parser = new JSONParser();
            JSONArray users = (JSONArray) parser.parse(reader);

            boolean userFound = false;
            for (int i = 0; i < users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);
                String storedUsername = (String) user.get("username");
                String storedPassword = (String) user.get("password");

                if (storedUsername.equals(username))
                {
                    if (storedPassword.equals(currentPassword)) {
                        // Actualiza la contraseña
                        user.put("password", newPassword);
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

            // Reescribe el archivo JSON
            try (FileWriter writer = new FileWriter(DATA_FILE)) {
                writer.write(users.toJSONString());
                System.out.println("Contraseña actualizada correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al escribir el archivo JSON.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo data.json no existe.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
