package Blackjack;

import Game.GameWriteable;
import processing.core.PApplet;

public class BlackjackGame extends GameFunctions implements GameWriteable { //Game class for Blackjack

    public void play() {
        PApplet.main("Blackjack.GameFunctions");
    }

    public String getGameName(){
        return "Blackjack";
    }

    public String getScore(){
        return String.valueOf(cardGame.playerMoney);
    } // get a score - if there is no "score" you can return return "N/A" or something.

    public boolean isHighScore(String score, String currentHighScore){
        if (currentHighScore == null){
            return true;
        } else{
        return Integer.valueOf(score) > Integer.valueOf(currentHighScore);
        }
    }

}
