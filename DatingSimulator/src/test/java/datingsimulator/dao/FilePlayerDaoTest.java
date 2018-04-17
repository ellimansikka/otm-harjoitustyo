package datingsimulator.dao;

import datingsimulator.domain.Player;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FilePlayerDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File playerFile;
    PlayerDao dao;

    @Before
    public void setUp() throws Exception {
        playerFile = testFolder.newFile("testfile_players.txt");

        try (FileWriter file = new FileWriter(playerFile.getAbsolutePath())) {
            file.write("Elli\n");
        }

        dao = new FilePlayerDao(playerFile.getAbsolutePath());
    }

    @After
    public void tearDown() {
        playerFile.delete();
    }

    @Test
    public void playersAreReadCorrectlyFromFile() {
        List<Player> players = dao.getAll();
        assertEquals(1, players.size());
        Player player = players.get(0);
        assertEquals("Elli", player.getName());
    }

    @Test
    public void existingPlayerIsFound() {
        Player player = dao.findPlayer("Elli");
        assertEquals("Elli", player.getName());
    }

    @Test
    public void nonExistingPlayerIsNotFound() {
        Player player = dao.findPlayer("Timo");
        assertEquals(null, player);
    }

    @Test
    public void savedPlayerIsFound() throws Exception {
        Player newPlayer = new Player("Timo");
        dao.create(newPlayer);

        Player player = dao.findPlayer("Timo");
        assertEquals("Timo", player.getName());
    }

}
