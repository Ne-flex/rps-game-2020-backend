package rps.game.backend.gameList;

public class GameManager {
    GameList gamesList = new GameList();

    public void createGame(int gameID) {
        gamesList.addGame(new Game(gameID));
    }

    public boolean gameExists(int gameID) {
        return gamesList.getGame(gameID) != null;
    }

    public void addDecisionToPlayerInGame(int gameID, int playerID, String playerDecision) {
        Game game = gamesList.getGame(gameID);
        if( game.getDecision(playerID).equals("")) {
            game.setDecision(playerDecision, playerID);
        }
    }

    public boolean bothPlayerMadeDecisions(int gameID) {
        Game game = gamesList.getGame(gameID);

        return !game.getDecision(1).equals("") && !game.getDecision(2).equals("");
    }

    public String[] getGameDecisions(int gameID) {
        Game game = gamesList.getGame(gameID);
        return new String[] { game.getDecision(1), game.getDecision(2) };
    }
}
