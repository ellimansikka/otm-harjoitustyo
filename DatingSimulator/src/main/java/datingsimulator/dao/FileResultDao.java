

package datingsimulator.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import datingsimulator.domain.Result;
import datingsimulator.domain.Player;

public class FileResultDao implements ResultDao {
    public List<Result> results;
    private String file;
    
    public FileResultDao(String file, PlayerDao players) throws Exception {
        results = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                int result = Integer.parseInt(parts[1]);
                Player p = null;
                for(Player player : players.getAll()) {
                    if (player.getName().equals(parts[2])){
                        p = player;
                    } else {
                        p = null;
                    }
                }
                Result r = new Result(id, result, p);
                results.add(r);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
        }
        
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Result result : results) {
                writer.write(result.getId() + ";" + result.getResult() + ";" + result.getPlayer().getName() + "\n");
            }
        }
    }    
    
    private int createId() {
        return results.size() + 1;
    }
    
    @Override
    public List<Result> getAll() {
        return results;
    }
    
    public Result create(Result result) throws Exception {
        result.setId(createId());
        results.add(result);
        save();
        return result;
    }   
}
