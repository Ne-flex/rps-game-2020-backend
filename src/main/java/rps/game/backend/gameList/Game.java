package rps.game.backend.gameList;

public class Game {

    final private int gameID;
    private String decisionPlayerOne = "";
    private String decisionPlayerTwo = "";


    public Game(int gameID) {
        this.gameID = gameID;
    }
    public void setDecision(String decision, int playerID) {
        if (playerID == 1) {
            this.decisionPlayerOne = decision;
        } else {
            this.decisionPlayerTwo = decision;
        }
    }

    public String getDecision(int playerID) {
        if (playerID == 1) {
            return decisionPlayerOne;
        }
        return decisionPlayerTwo;
    }

    public int getGameID() {
        return gameID;
    }
}
