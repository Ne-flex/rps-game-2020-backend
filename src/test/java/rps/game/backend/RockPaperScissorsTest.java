package rps.game.backend;

import org.junit.Assert;
import org.junit.Test;

public class RockPaperScissorsTest {

    RockPaperScissors rps = new RockPaperScissors();
    
    @Test
    public void rockPaperScissors_shouldReturnDraw_bothChooseTheSame() {
        String expected = "Draw";
        String actual = rps.rockPaperScissors("Rock", "Rock");
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void rockPaperScissors_shouldReturnPlayer1_player1StonePlayer2Scissors() {
        String expected = "Player 1";
        String actual = rps.rockPaperScissors("Rock", "Scissors");
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void rockPaperScissors_shouldReturnPlayer1_player1ScissorsPlayer2Paper() {
        String expected = "Player 1";
        String actual = rps.rockPaperScissors("Scissors", "Paper");
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void rockPaperScissors_shouldReturnPlayer1_player1PaperPlayer2Stone() {
        String expected = "Player 1";
        String actual = rps.rockPaperScissors("Paper", "Rock");
        Assert.assertEquals(expected, actual);
    }
    
    
    @Test
    public void rockPaperScissors_shouldReturnPlayer2_player1StonePlayer2SPaper() {
        String expected = "Player 2";
        String actual = rps.rockPaperScissors("Rock", "Paper");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void rockPaperScissors_shouldReturnPlayer2_player1ScissorsPlayer2Stone() {
        String expected = "Player 2";
        String actual = rps.rockPaperScissors("Scissors", "Rock");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void rockPaperScissors_shouldReturnPlayer2_player1PaperPlayer2Scissors() {
        String expected = "Player 2";
        String actual = rps.rockPaperScissors("Paper", "Scissors");
        Assert.assertEquals(expected, actual);
    }
}
