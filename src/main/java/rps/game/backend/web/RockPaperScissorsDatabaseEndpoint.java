package rps.game.backend.web;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.JSONObject;
import rps.game.backend.RockPaperScissors;
import rps.game.backend.database.InsertIntoDatabase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RockPaperScissorsDatabaseEndpoint extends AbstractHandler {
    private final RockPaperScissors rockpaperScissors;
    private final InsertIntoDatabase insertIntoDatabase;
    private final CorsHandler corsHandler;

    public RockPaperScissorsDatabaseEndpoint(RockPaperScissors rockPaperScissors, InsertIntoDatabase insertIntoDatabase, CorsHandler corsHandler) {
        this.rockpaperScissors = rockPaperScissors;
        this.insertIntoDatabase = insertIntoDatabase;
        this.corsHandler = corsHandler;
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int gameID = 0;
        int playerID = 0;
        String playerDecision = "";
        String decisionPlayerOne = "";
        String decisionPlayerTwo = "";
        String gameWinner = "";
        boolean requested = false;

        corsHandler.handleCors(response);

        if (request.getParameter("gameID") != null) {
            gameID = Integer.parseInt(request.getParameter("gameID"));
        }
        if (request.getParameter("playerID") != null) {
            playerID = Integer.parseInt(request.getParameter("playerID"));
        }
        if (request.getParameter("playerDecision") != null) {
            playerDecision = request.getParameter("playerDecision");
        }
        if (request.getParameter("requestCheck") != null) {
            requested = Boolean.parseBoolean(request.getParameter("requestCheck"));
        }

        if (gameID != 0 && playerID != 0 && !playerDecision.equals("")) {
            if (insertIntoDatabase.idsNotPresentInDatabase(gameID, playerID)) {
                insertIntoDatabase.insertDecisionIntoDatabase(gameID, playerID, playerDecision);
            }
            String[] decisions = insertIntoDatabase.getDecisionsFromDatabase(gameID);
            if (decisions != null) {
                decisionPlayerOne = decisions[0];
                decisionPlayerTwo = decisions[1];
                gameWinner = rockpaperScissors.rockPaperScissors(playerDecision, decisionPlayerTwo);
            }
        }

        if (requested) {
            if(!insertIntoDatabase.requestedAmount(gameID)){
                insertIntoDatabase.addRequested(gameID, playerID);
            }
            if(insertIntoDatabase.requestedAmount(gameID)){
                insertIntoDatabase.deleteGame(gameID);
            }
        }

        JSONObject gameData = new JSONObject();
        gameData.put("winner", gameWinner);
        gameData.put("playerOne", decisionPlayerOne);
        gameData.put("playerTwo", decisionPlayerTwo);

        response.setHeader("Content-Type", "text/plain; charset=utf-8");
        response.getWriter().print(gameData);
        baseRequest.setHandled(true);
    }
}

