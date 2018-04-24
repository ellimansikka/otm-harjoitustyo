package datingsimulator.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import datingsimulator.domain.Player;

public class FilePlayerDao implements PlayerDao {

    private List<Player> players;
    private String file;

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

    public Player create(Player player) throws Exception {
        players.add(player);
        save();
        return player;
    }

    public Player findPlayer(String name) {
        return players.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
