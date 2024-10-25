package utils;

import models.Player;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.UUID;

public class StateManager {

    private static final String DATA_FILE = "data.json";
    private static String currentPlayerOnSession = null;
    public static boolean islogged = false;

    public void readPlayers() {
        try (FileReader reader = new FileReader(DATA_FILE)) {
            JSONParser parser = new JSONParser();
            JSONArray players = (JSONArray) parser.parse(reader);

            for (Object playerObj : players) {
                JSONObject player = (JSONObject) playerObj;
                String username = (String) player.get("username");
                Long level = (Long) player.get("level");
                Long score = (Long) player.get("score");

                System.out.println("Jugador: " + username + ", Nivel: " + level + ", Puntaje: " + score);
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo data.json no existe. Se creará un archivo nuevo.");
            try {
                FileWriter file = new FileWriter(DATA_FILE);
                file.write("[]");
                file.close();
                System.out.println("Archivo data.json creado correctamente.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getPlayerById(String playerId) {
        try (FileReader reader = new FileReader(DATA_FILE)) {
            JSONParser parser = new JSONParser();
            JSONArray players = (JSONArray) parser.parse(reader);

            for (Object playerObj : players) {
                JSONObject player = (JSONObject) playerObj;
                String id = (String) player.get("id");
                if (id.equals(playerId)) {
                    return player;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo data.json no existe.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createPlayer(String username, String password) {
        try (FileReader reader = new FileReader(DATA_FILE)) {
            JSONParser parser = new JSONParser();
            JSONArray players = (JSONArray) parser.parse(reader);

            Player newPlayer = new Player(username, password);
            newPlayer.setId(UUID.randomUUID());
            newPlayer.setLevel(1);
            newPlayer.setScore(0);

            JSONObject playerJson = new JSONObject();
            playerJson.put("id", newPlayer.getId().toString());
            playerJson.put("username", newPlayer.getUsername());
            playerJson.put("password", newPlayer.getPassword());
            playerJson.put("level", newPlayer.getLevel());
            playerJson.put("score", newPlayer.getScore());

            players.add(playerJson);

            try (FileWriter writer = new FileWriter(DATA_FILE)) {
                writer.write(players.toJSONString());
                System.out.println("Jugador " + username + " creado exitosamente.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al escribir el archivo JSON.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo data.json no existe. Se creará un archivo nuevo.");
            createPlayerWithNewFile(username, password);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void createPlayerWithNewFile(String username, String password) {
        JSONArray players = new JSONArray();

        Player newPlayer = new Player(username, password);
        newPlayer.setId(UUID.randomUUID());
        newPlayer.setLevel(1);
        newPlayer.setScore(0);

        JSONObject playerJson = new JSONObject();
        playerJson.put("id", newPlayer.getId().toString());
        playerJson.put("username", newPlayer.getUsername());
        playerJson.put("password", newPlayer.getPassword());
        playerJson.put("level", newPlayer.getLevel());
        playerJson.put("score", newPlayer.getScore());

        players.add(playerJson);

        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            writer.write(players.toJSONString());
            System.out.println("Jugador " + username + " creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir el archivo JSON.");
        }
    }

    public void updatePlayerData(String playerId, int newScore, int newLevel) {
        try (FileReader reader = new FileReader(DATA_FILE)) {
            JSONParser parser = new JSONParser();
            JSONArray players = (JSONArray) parser.parse(reader);

            boolean playerFound = false;
            for (int i = 0; i < players.size(); i++) {
                JSONObject player = (JSONObject) players.get(i);
                String id = (String) player.get("id");
                if (id.equals(playerId)) {
                    player.put("score", newScore);
                    player.put("level", newLevel);
                    playerFound = true;
                    break;
                }
            }

            if (!playerFound) {
                System.out.println("Jugador no encontrado.");
                return;
            }

            try (FileWriter writer = new FileWriter(DATA_FILE)) {
                writer.write(players.toJSONString());
                System.out.println("Datos del jugador " + playerId + " actualizados correctamente.");
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

    public JSONObject getCurrentSession() {
        return getPlayerById(currentPlayerOnSession);
    }

    public void login(String id) {
        currentPlayerOnSession = id;
        islogged = true;
    }
    public void logout() {
        currentPlayerOnSession = null;
        islogged = false;
    }
}
