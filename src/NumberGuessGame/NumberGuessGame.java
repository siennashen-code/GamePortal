package NumberGuessGame;

import Game.GameWriteable;

public class NumberGuessGame extends GameFunctions implements GameWriteable {

    public String getGameName(){
        return "Number Guessing Game";
    }

    public void play(){
       game();
    }

    public String getScore(){
        return String.valueOf(guesses);
    }

    public boolean isHighScore(String score, String currentHighScore){
        if (currentHighScore == null){
            return true;
        } else{
        return Integer.valueOf(score) < Integer.valueOf(currentHighScore);
        }
    }

}
