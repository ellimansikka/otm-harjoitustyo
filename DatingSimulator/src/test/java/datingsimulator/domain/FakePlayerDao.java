package datingsimulator.domain;

import java.util.ArrayList;
import java.util.List;
import datingsimulator.dao.PlayerDao;

public class FakePlayerDao implements PlayerDao {

    List<Player> players = new ArrayList<>();

    public FakePlayerDao() {
        players.add(new Player("Tester Elli"));
    }

    @Override
    public Player findPlayer(String name) {
        return players.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Player create(Player player) {
        players.add(player);
        return player;
    }

    @Override
    public List<Player> getAll() {
        return players;
    }

}
