
package datingsimulator.dao;


import datingsimulator.domain.Tulos;
import java.util.List;


public interface TulosDao {
    
    Tulos luoUusi(Tulos tulos) throws Exception;
    
    List<Tulos> getKaikki();
    
}
