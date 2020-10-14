package rps.game.backend.web;

import javax.servlet.http.HttpServletResponse;

public class CorsHandler {

    public void handleCors(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD");
    }
}
