package datingsimulator.domain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerServicePlayerTest {

    FakePlayerDao playerDao;
    FakeResultDao resultDao;
    PlayerService service;

    public PlayerServicePlayerTest() {
    }

    @Before
    public void setUp() {
        playerDao = new FakePlayerDao();
        resultDao = new FakeResultDao();
        service = new PlayerService(resultDao, playerDao);
    }

    @Test
    public void existingPlayerCanLogIn() {
        boolean result = service.logIn("Tester Elli");
        assertTrue(result);

        Player loggedIn = service.getLoggedInPlayer();
        assertEquals("Tester Elli", loggedIn.getName());
    }

    @Test
    public void loggedInPlayerrCanLogout() {
        service.logIn("Tester Elli");
        service.logOut();

        assertEquals(null, service.getLoggedInPlayer());
    }

    @Test
    public void nonExistingPlayerCantLogIn() {
        boolean result = service.logIn("nonexisting");
        assertFalse(result);

        assertEquals(null, service.getLoggedInPlayer());
    }

    @Test
    public void playerCreationFailsIfNameNotUnique() throws Exception {
        boolean result = service.createPlayer("Tester Elli");
        assertFalse(result);
    }

    @Test
    public void succesfullyCreatedPlayerCanLogIn() throws Exception {
        boolean result = service.createPlayer("Test-Timo");
        assertTrue(result);

        boolean loginOk = service.logIn("Test-Timo");
        assertTrue(loginOk);

        Player loggedIn = service.getLoggedInPlayer();
        assertEquals("Test-Timo", loggedIn.getName());
    }

}
