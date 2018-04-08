

package datingsimulator.domain;


public class Pelaaja {
    private String nimi;
    
    
    public Pelaaja(String nimi) {
        this.nimi = nimi;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pelaaja)) {
            return false;
        }
        
        Pelaaja toinen = (Pelaaja) obj;
        return nimi.equals(toinen.nimi);
    }
}
