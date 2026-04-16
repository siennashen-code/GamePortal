import java.io.File;
import java.io.FileNotFoundException;

import Game.GameWriteable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import BuzzfeedQuiz.Person;

public class Highscores implements GameWriteable { // Class for the high score display page. For easier implementation,
                                                   // I made it implement GameWriteable so it could be considered a Game
                                                   // type
    public String getGameName() {
        return "VIEW HIGH SCORES";
    }

    public void play() {
        System.out.println("\n---Highscores---");

        File highscores = new File("Highscore.csv");
        
        if (highscores.length() == 0) { //If there are no high scores
            System.out.println("No high scores yet!");
            System.out.println("----------------");
            return;
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("Highscore.csv"));
            try { // read through each line in the csv and print it in terminal
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

        System.out.println("----------------");

    }

    public String getScore() {
        return null;
    }

    public boolean isHighScore(String score, String currentHighScore) {
        return false;
    }
}
