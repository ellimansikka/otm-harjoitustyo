

package datingsimulator.domain;


public class Player {
    private String name;
    
    
    public Player(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        
        Player other = (Player) obj;
        return name.equals(other.name);
    }
}
