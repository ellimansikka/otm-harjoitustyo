
package datingsimulator.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import datingsimulator.domain.Pelaaja;

public class FilePelaajaDao implements PelaajaDao {
    private List<Pelaaja> pelaajat;
    private String file;
    
    public FilePelaajaDao(String file) throws Exception {
        pelaajat = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String nimi = reader.nextLine();
                Pelaaja pelaaja = new Pelaaja(nimi);
                pelaajat.add(pelaaja);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    private void tallenna() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Pelaaja pelaaja : pelaajat) {
                writer.write(pelaaja.getNimi());
            }
        } 
    }
    
    @Override
    public List<Pelaaja> getKaikki() {
        return pelaajat;
    }
    
    
    public Pelaaja luoUusi(Pelaaja pelaaja) throws Exception {
        pelaajat.add(pelaaja);
        tallenna();
        return pelaaja;
    }  
    
    public Pelaaja loydaPelaaja(String nimi) {
        Pelaaja p = null;
        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.getNimi().equals(nimi)){
                p = pelaaja;
            } else {
                p = null;
            }
        }
        return p;
    }
    
}
