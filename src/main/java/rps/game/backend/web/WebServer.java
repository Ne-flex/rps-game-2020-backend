package rps.game.backend.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import rps.game.backend.RockPaperScissors;
import rps.game.backend.gameList.GameManager;

public class WebServer {
    private final RockPaperScissors rockPaperScissors;
    private final GameManager gameManager;

    public WebServer(RockPaperScissors rockPaperScissors, GameManager gameManager) {
        this.rockPaperScissors = rockPaperScissors;
        this.gameManager = gameManager;
    }
    public void startServer() throws Exception{
        final ContextHandler rpsHandler = new ContextHandler("/rps");
        rpsHandler.setAllowNullPathInfo(true);
        rpsHandler.setHandler(new RockPaperScissorsEndpoint(rockPaperScissors, gameManager));

        ContextHandlerCollection contextHandlers = new ContextHandlerCollection(rpsHandler);

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
