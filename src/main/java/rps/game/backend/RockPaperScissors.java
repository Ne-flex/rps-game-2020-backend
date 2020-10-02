package rps.game.backend;

import java.util.Random;

public class RockPaperScissors {

    public String rockPaperScissors(String playerOneChoice, String playerTwoChoice) {
        if (playerOneChoice.equals(playerTwoChoice)) {
            return "Draw";
        }

        return switch (playerOneChoice) {
            case "Rock" -> playerTwoChoice.equals("Scissors") ? "Player 1" : "Player 2";
            case "Paper" -> playerTwoChoice.equals("Rock") ? "Player 1" : "Player 2";
            case "Scissors" -> playerTwoChoice.equals("Paper") ? "Player 1" : "Player 2";
            default -> "";
        };
    }

    public String randomDecision() {
        Random rand = new Random();

        return switch (rand.nextInt(3)) {
            case 0 -> "Rock";
            case 1 -> "Paper";
            case 2 -> "Scissors";
            default -> "DEFAULT";
        };
    }
}
