package datingsimulator.dao;

import datingsimulator.domain.Player;
import java.util.List;

public interface PlayerDao {

    Player create(Player player) throws Exception;

    List<Player> getAll();

    Player findPlayer(String name);

}
