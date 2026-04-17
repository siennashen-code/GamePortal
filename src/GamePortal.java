import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Game.Game;
import Game.ErrorCheck;

import Blackjack.BlackjackGame;
import NumberGuessGame.NumberGuessGame;
import BuzzfeedQuiz.Quiz;
import ASCII.AsciiGame;

public class GamePortal {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Game> games = new ArrayList<Game>();

    public static void main(String[] args) {
        // writes highscores
        File f = new File("Highscore.csv");

        while (true) {
            loadGames();

            System.out.println("~~~~Welcome to the game lobby! Which game would you like to play?~~~~");
            printGameChoices();
            Game g = getGameChoice();

            if (!g.getGameName().equals("VIEW HIGH SCORES")) {
                g.play();
                g.writeHighScore(f);
            } else {
                g.play(); // Don't run writeHighScore for the highscore page because it wouldn't make
                          // sense to print "GAME OVER" as is done in the
                          // writeHighScore method
            }

            try { // a little pause after game is finished 
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 3; i++) { //a little space after game is finished 
                System.out.println();
            }

        }
    }

    public static void loadGames() {
        games.clear();
        games.add(new BlackjackGame());
        games.add(new NumberGuessGame());
        games.add(new Quiz());
        games.add(new AsciiGame());

        games.add(new Highscores());
    }

    public static void printGameChoices() {
        int n = 1;
        
        for (Game s : games) {
            if (s.getGameName().equals("VIEW HIGH SCORES")) {
                System.out.println(); //a linespace between games and highscore page
            }
            System.out.println("[" + (n++) + "]: " + s.getGameName());
        }
    }

    public static Game getGameChoice() {
        int choice = ErrorCheck.getInt(sc);
        // for it to be numbered, we can't use hashmaps.
        while (choice < 1 || choice > games.size()) {
            System.out.println("We don't have this number. Try again.");
            choice = ErrorCheck.getInt(sc);
        }

        // valid game choice
        return games.get(choice - 1);
    }
}
