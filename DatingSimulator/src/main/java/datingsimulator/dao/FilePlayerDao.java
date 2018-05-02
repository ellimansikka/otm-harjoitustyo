package datingsimulator.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import datingsimulator.domain.Player;

/**
 * Reads existing users from player-file and can write and save new users.
 *
 * @author ellimans
 */
public class FilePlayerDao implements PlayerDao {

    private List<Player> players;
    private String file;

    /**
     * Reads players from the player-file to a private player list.
     *
     * @param file player-file
     * @throws Exception
     */
    public FilePlayerDao(String file) throws Exception {
        players = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String name = reader.nextLine();
                Player player = new Player(name);
                players.add(player);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }

    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Player player : players) {
                writer.write(player.getName() + "\n");
            }
        }
    }

    @Override
    public List<Player> getAll() {
        return players;
    }

    /**
     * Creates player by adding the player to the class' player list and saving
     * and writing it to the player-file.
     *
     * @param player
     * @return player
     * @throws Exception
     */
    public Player create(Player player) throws Exception {
        players.add(player);
        save();
        return player;
    }

    /**
     * Finds the player from all the players by the player's name, if the player
     * exists. If the player does not exist, returns null.
     *
     * @param name
     * @return player or null if player does not exist
     */
    public Player findPlayer(String name) {
        return players.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
