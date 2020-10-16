package rps.game.backend.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import rps.game.backend.RockPaperScissors;
import rps.game.backend.database.InsertIntoDatabase;
import rps.game.backend.gameList.GameManager;

public class WebServer {
    private final RockPaperScissors rockPaperScissors;
    private final GameManager gameManager;
    private final CorsHandler corsHandler;
    private final InsertIntoDatabase insertIntoDatabase;

    public WebServer(RockPaperScissors rockPaperScissors, GameManager gameManager, InsertIntoDatabase insertIntoDatabase, CorsHandler corsHandler) {
        this.rockPaperScissors = rockPaperScissors;
        this.gameManager = gameManager;
        this.corsHandler = corsHandler;
        this.insertIntoDatabase = insertIntoDatabase;
    }
    public void startServer() throws Exception{
        final ContextHandler rpsHandler = new ContextHandler("/rps");
        final ContextHandler databaseHandler = new ContextHandler("/rpsdb");
        rpsHandler.setAllowNullPathInfo(true);
        rpsHandler.setHandler(new RockPaperScissorsEndpoint(rockPaperScissors, gameManager, corsHandler));
        databaseHandler.setHandler(new RockPaperScissorsDatabaseEndpoint(rockPaperScissors, insertIntoDatabase, corsHandler));

        ContextHandlerCollection contextHandlers = new ContextHandlerCollection(rpsHandler, databaseHandler);

        String port = System.getenv("PORT");
        if (port == null) {
            port = "8080";
        }
        final Server server = new Server(Integer.parseInt(port));

        server.setHandler(contextHandlers);
        server.start();
        server.join();
    }
}
