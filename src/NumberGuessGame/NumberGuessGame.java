package NumberGuessGame;

import Game.GameWriteable;

public class NumberGuessGame extends GameFunctions implements GameWriteable {

    public String getGameName() {
        return "Number Guessing Game";
    }

    public void play() {
        System.out.println("\n---Number Guess Game---");
        game();
    }

    public String getScore() {
        return String.valueOf(guesses) + " guesses";
    }

    public boolean isHighScore(String score, String currentHighScore) {
        if (currentHighScore == null) {
            return true;
        } else {
            return Integer.valueOf(score.replace(" guesses", "")) > Integer.valueOf(currentHighScore.replace(" guesses", ""));
        }
    }

}
