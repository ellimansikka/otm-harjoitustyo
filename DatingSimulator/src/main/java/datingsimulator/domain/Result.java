package datingsimulator.domain;

public class Result {

    private int points;
    private String player;

    public Result(int points, String player) {
        this.points = points;
        this.player = player;
    }

    public int getResult() {
        return points;
    }

    public String getPlayerName() {
        return player;
    }

}
