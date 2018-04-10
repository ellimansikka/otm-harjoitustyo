/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datingsimulator.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import datingsimulator.dao.PlayerDao;

/**
 *
 * @author ellimans
 */
public class FakePlayerDao implements PlayerDao {
    List<Player> players = new ArrayList<>();
    
    public FakePlayerDao() {
        players.add(new Player("Tester Elli"));
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Override
    public Player findPlayer(String name) {
        return players.stream().filter(u->u.getName().equals(name)).findFirst().orElse(null);
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
    
    
    
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
