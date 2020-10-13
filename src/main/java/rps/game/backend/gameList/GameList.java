package rps.game.backend.gameList;

import java.util.ArrayList;

public class GameList {
    private ArrayList<Game> games = new ArrayList<Game>();

    public boolean addGame(Game game) {
        return games.add(game);
    }

    public boolean removeGame(int gameID) {
        for (Game game : games) {
            if (game.getGameID() == gameID) {
                return games.remove(game);
            }
        }
        return false;
    }

    public Game getGame(int gameID) {
        for (Game game : games) {
            if (game.getGameID() == gameID) {
                return game;
            }
        }
        return null;
    }
}
