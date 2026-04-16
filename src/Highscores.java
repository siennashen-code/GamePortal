import java.io.File;
import java.io.FileNotFoundException;

import Game.GameWriteable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import BuzzfeedQuiz.Person;

public class Highscores implements GameWriteable {
    public String getGameName() {
        return "VIEW HIGH SCORES";
    }

    public void play() {
        System.out.println("\n---Highscores---");

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("Highscore.csv"));
            try {
                String line;
                line = reader.readLine();
                while (line != null) {
                    String[] row = line.split(",");
                    System.out.println("   " + row[0] + "....." + row[1]);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String getScore() {
        return null;
    } // get a score - if there is no "score" you can return return "N/A" or
      // something.

    public boolean isHighScore(String score, String currentHighScore) {
        return false;
    }
}
