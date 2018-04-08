

package datingsimulator.domain;

public class Tulos {
    private int id;
    private int tulos;
    private Pelaaja pelaaja;
    
    
    public Tulos(int id, int tulos, Pelaaja pelaaja) {
        this.id = id;
        this.tulos = tulos;
        this.pelaaja = pelaaja;
    }
    
    public int getTulos() {
        return tulos;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Pelaaja getPelaaja() {
        return pelaaja;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tulos)) {
            return false;
        }
        Tulos other = (Tulos) obj;
        return id == other.id;
    }
}
