package NumberGuessGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Game.GameWriteable;

public class NumberGuessGame extends GameFunctions implements GameWriteable { // Number guess game class

    public String getGameName() {
        return "Number Guessing Game";
    }

    public void play() {
        System.out.println("\n---Number Guess Game---");
        game();
    }

    public String getScore() {
        return String.valueOf(guesses);
    }

    public boolean isHighScore(String score, String currentHighScore) {
        if (currentHighScore == null) {
            return true;
        } else {
            return Integer.valueOf(score) < Integer.valueOf(currentHighScore);
        }
    }

    @Override
    public void writeHighScore(File f) { // This overriden version will display "5 guesses" as the user's score as
                                         // opposed to just "5"
        String score = getScore();
        String highScore = getBestScore(f);

        System.out.println("\n---Game Over---");

        if (Integer.valueOf(score) == 1) {
            System.out.println("Your score: 1 guess");
        } else {
            System.out.println("Your score: " + score + " guesses");
        }

        if (isHighScore(score, highScore)) {
            if (highScore != null) {
                System.out.println("You got a new high score, beating the previous one of " + highScore);
            }
            try {

                Scanner myReader = new Scanner(f);
                String newFile = "";
                while (myReader.hasNextLine()) {
                    // rewrite all lines except the line with the game name
                    String line = myReader.nextLine();
                    String[] data = line.split(",");
                    if (!data[0].equals(getGameName())) {
                        newFile += line + "\n";
                    }
                }
                myReader.close();

                // replace the Game's line with the new high score
                // puts it at the end of the file
                newFile += getGameName() + "," + score + "\n";
                // write the new file
                FileWriter myWriter = new FileWriter(f);
                myWriter.write(newFile);
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("----------------");
    }

}
