
package datingsimulator.dao;

import datingsimulator.domain.Pelaaja;
import java.util.List;


public interface PelaajaDao {
    
    Pelaaja luoUusi(Pelaaja pelaaja) throws Exception;
    
    List<Pelaaja> getKaikki();
    
    Pelaaja loydaPelaaja(String nimi);
    
}
