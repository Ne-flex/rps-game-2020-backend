package rps.game.backend.web;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.JSONObject;
import rps.game.backend.RockPaperScissors;
import rps.game.backend.gameList.GameManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RockPaperScissorsEndpoint extends AbstractHandler {
    private final RockPaperScissors rockpaperScissors;
    private final GameManager gameManager;
    private final CorsHandler corsHandler;

    public RockPaperScissorsEndpoint(RockPaperScissors rockPaperScissors, GameManager gameManager, CorsHandler corsHandler) {
        this.rockpaperScissors = rockPaperScissors;
        this.gameManager = gameManager;
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

        if (gameID != 0 && !gameManager.gameExists(gameID)) {
            gameManager.createGame(gameID);
        } else {
            decisionPlayerOne = playerDecision;
            decisionPlayerTwo = rockpaperScissors.randomDecision();
            gameWinner = rockpaperScissors.rockPaperScissors(playerDecision, decisionPlayerTwo);
        }

        if (gameID != 0 && gameManager.gameExists(gameID) && playerID != 0 && playerDecision != null) {
            gameManager.addDecisionToPlayerInGame(gameID, playerID, playerDecision);
        }

        if (gameID != 0 && gameManager.bothPlayerMadeDecisions(gameID)) {
            String[] playerDecisions = gameManager.getGameDecisions(gameID);
            decisionPlayerOne = playerDecisions[0];
            decisionPlayerTwo = playerDecisions[1];
            gameWinner = rockpaperScissors.rockPaperScissors(decisionPlayerOne, decisionPlayerTwo);
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
