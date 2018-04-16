package datingsimulator.domain;

public class Result {

    private int id;
    private int result;
    private Player player;

    public Result(int id, int result, Player player) {
        this.id = id;
        this.result = result;
        this.player = player;
    }

    public int getResult() {
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Result)) {
            return false;
        }
        Result other = (Result) obj;
        return id == other.id;
    }
}
