package datingsimulator.dao;

import datingsimulator.domain.FakePlayerDao;
import datingsimulator.domain.Player;
import datingsimulator.domain.Result;
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

public class FileResultDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File resultFile;
    ResultDao dao;

    @Before
    public void setUp() throws Exception {
        resultFile = testFolder.newFile("testfile_players.txt");
        PlayerDao playerDao = new FakePlayerDao();
        Player player = new Player("Elli");
        playerDao.create(player);

        try (FileWriter file = new FileWriter(resultFile.getAbsolutePath())) {
            file.write(5 + ";" + player.getName() + "\n");
        }

        dao = new FileResultDao(resultFile.getAbsolutePath(), playerDao);
    }

    @After
    public void tearDown() {
        resultFile.delete();
    }

    @Test
    public void resultsAreReadCorrectlyFromFile() {
        List<Result> results = dao.getAll();
        assertEquals(1, results.size());
        Result result = results.get(0);
        assertEquals(5, result.getResult());
        assertEquals("Elli", result.getPlayerName());
    }

    @Test
    public void createdResultsAreListed() throws Exception {
        Player player = new Player("Timo");
        dao.create(new Result(4, player.getName()));

        List<Result> results = dao.getAll();
        assertEquals(2, results.size());
        Result result = results.get(1);
        assertEquals(4, result.getResult());
        assertEquals("Timo", result.getPlayerName());
    }
}
