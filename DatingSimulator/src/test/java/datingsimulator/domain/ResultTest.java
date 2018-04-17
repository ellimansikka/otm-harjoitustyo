package datingsimulator.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResultTest {

    Player player;
    Result result;

    public ResultTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        player = new Player("Elli");
        result = new Result(5, player.getName());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getResultWorksCorrectly() {
        assertEquals(5, result.getResult());
    }

    @Test
    public void getPlayerWorksCorrectly() {
        assertEquals(player.getName(), result.getPlayerName());
    }
}
