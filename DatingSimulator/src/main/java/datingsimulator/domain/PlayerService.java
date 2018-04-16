package datingsimulator.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import datingsimulator.dao.PlayerDao;
import datingsimulator.dao.ResultDao;

public class PlayerService {

    private PlayerDao playerDao;
    private ResultDao resultDao;
    private Player loggedIn;

    public PlayerService(ResultDao resultDao, PlayerDao playerDao) {
        this.playerDao = playerDao;
        this.resultDao = resultDao;
    }

    /**
     * logging in
     *
     *
     * @return true if the player name exists, otherwise false
     */
    public boolean logIn(String name) {
        Player player = playerDao.findPlayer(name);
        if (player == null) {
            return false;
        }

        loggedIn = player;

        return true;
    }

    /**
     * logged in player
     *
     * @return the player who has logged in
     */
    public Player getLoggedInPlayer() {
        return loggedIn;
    }

    /**
     * logging out
     */
    public void logOut() {
        loggedIn = null;
    }

    /**
     * creating a new player
     *
     * @param name player's name
     *
     * @return true if succeeded to create the player, if not then false
     */
    public boolean createPlayer(String name) {
        if (playerDao.findPlayer(name) != null) {
            return false;
        }
        Player player = new Player(name);
        try {
            playerDao.create(player);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
