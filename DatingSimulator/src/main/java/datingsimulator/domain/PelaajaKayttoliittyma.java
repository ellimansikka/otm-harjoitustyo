
package datingsimulator.domain;

import datingsimulator.dao.PelaajaDao;
import datingsimulator.dao.TulosDao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PelaajaKayttoliittyma {
    private PelaajaDao pelaajaDao;
    private TulosDao tulosDao;
    private Pelaaja kirjautunutPelaaja;
    
    public PelaajaKayttoliittyma(TulosDao tulosDao, PelaajaDao pelaajaDao) {
        this.pelaajaDao = pelaajaDao;
        this.tulosDao = tulosDao;
    }
    
     /**
    * sisäänkirjautuminen
    * 
    * 
    * @return true jos pelaajan nimi on olemassa, muuten false 
    */    
    
    public boolean kirjaudu(String nimi) {
        Pelaaja pelaaja = pelaajaDao.loydaPelaaja(nimi);
        if (pelaaja == null) {
            return false;
        }
        
        kirjautunutPelaaja = pelaaja;
        
        return true;
    }
    
    /**
    * kirjautuneena oleva pelaaja
    * 
    * @return kirjautuneena oleva pelaaja
    */   
    
    public Pelaaja getKirjautunutPelaaja() {
        return kirjautunutPelaaja;
    }
    
    /**
    * uloskirjautuminen
    */  
    
    public void kirjauduUlos() {
        kirjautunutPelaaja = null;  
    }
    
    /**
    * uuden pelaajan luominen
    * 
    * @param   name   pelaajan nimi
    * 
    * @return true jos pelaaja luonti on onnistunut, muuten false 
    */ 
    
    public boolean luoPelaaja(String nimi)  {   
        if (pelaajaDao.loydaPelaaja(nimi) != null) {
            return false;
        }
        Pelaaja pelaaja = new Pelaaja(nimi);
        try {
            pelaajaDao.luoUusi(pelaaja);
        } catch(Exception e) {
            return false;
        }

        return true;
    }
}
