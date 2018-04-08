

package datingsimulator.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import datingsimulator.domain.Tulos;
import datingsimulator.domain.Pelaaja;

public class FileTulosDao implements TulosDao {
    public List<Tulos> tulokset;
    private String file;
    
    public FileTulosDao(String file, PelaajaDao pelaajat) throws Exception {
        tulokset = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] osat = reader.nextLine().split(";");
                int id = Integer.parseInt(osat[0]);
                int tulos = Integer.parseInt(osat[1]);
                Pelaaja p = null;
                for(Pelaaja pelaaja : pelaajat.getKaikki()) {
                    if (pelaaja.getNimi().equals(osat[2])){
                        p = pelaaja;
                    } else {
                        p = null;
                    }
                }
                Tulos t = new Tulos(id, tulos, p);
                tulokset.add(t);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
        }
        
    }
    
     private void tallenna() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Tulos tulos : tulokset) {
                writer.write(tulos.getId() + ";" + tulos.getTulos() + ";" + tulos.getPelaaja().getNimi() + "\n");
            }
        }
    }    
    
     private int luoId() {
        return tulokset.size() + 1;
    }
    
    @Override
    public List<Tulos> getKaikki() {
        return tulokset;
    }
    
    public Tulos luoUusi(Tulos tulos) throws Exception {
        tulos.setId(luoId());
        tulokset.add(tulos);
        tallenna();
        return tulos;
    }   
}
