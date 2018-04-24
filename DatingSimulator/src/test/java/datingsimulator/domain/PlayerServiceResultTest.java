package datingsimulator.domain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerServiceResultTest {

    FakePlayerDao playerDao;
    FakeResultDao resultDao;
    PlayerService service;

    public PlayerServiceResultTest() {
    }

    @Before
    public void setUp() {
        playerDao = new FakePlayerDao();
        resultDao = new FakeResultDao();
        Player p1 = new Player("tester1");
        Player p2 = new Player("tester2");
        playerDao.create(p1);
        playerDao.create(p2);
        resultDao.create(new Result(4, p1.getName()));
        service = new PlayerService(resultDao, playerDao);
        service.logIn(p1.getName());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createsResultCorrectly() throws Exception {
        boolean createOk = service.createResult(3, service.getLoggedInPlayer().getName());
        assertTrue(createOk);

    }

    @Test
    public void atStartPlayerHasInitializedResults() {
        List<Result> results = service.getPlayersResults();

        assertEquals(1, results.size());
        Result result = results.get(0);
        assertEquals(4, result.getResult());
        assertEquals("tester1", result.getPlayerName());
    }

    @Test
    public void noResultsFoundIfNotLoggedIn() {
        service.logOut();
        List<Result> results = service.getPlayersResults();

        assertEquals(0, results.size());
    }

    @Test
    public void loggedPlayersResultsContainCreatedResult() throws Exception {
        service.createResult(1, service.getLoggedInPlayer().getName());

        List<Result> results = service.getPlayersResults();
        assertEquals(2, results.size());
        Result result = results.get(1);

        assertEquals(1, result.getResult());
        assertEquals("tester1", result.getPlayerName());
    }

    @Test
    public void loggedPlayersResultsDoNotContainResultsOfOther() throws Exception {
        service.createResult(0, service.getLoggedInPlayer().getName());
        service.logOut();
        service.logIn("tester2");

        List<Result> results = service.getPlayersResults();
        assertEquals(0, results.size());
    }

}
