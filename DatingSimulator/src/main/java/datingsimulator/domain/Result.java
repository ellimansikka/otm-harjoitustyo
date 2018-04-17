package datingsimulator.domain;

public class Result {

    private int result;
    private String player;

    public Result(int result, String player) {
        this.result = result;
        this.player = player;
    }

    public int getResult() {
        return result;
    }

    public String getPlayerName() {
        return player;
    }

}
