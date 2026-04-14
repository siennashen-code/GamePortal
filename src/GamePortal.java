import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Game.Game;
import Game.ErrorCheck;

import Blackjack.BlackjackGame;
import NumberGuessGame.NumberGuessGame;


public class GamePortal {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Game> games = new ArrayList<Game>();
    public static void main(String[] args) {
        HashMap<String, Integer> gameCounts = new HashMap<String, Integer>();
        // writes highscores
        File f = new File("Highscore.csv");
        
        while (true) {
            loadGames();
            
            System.out.println("~~~~Welcome to the game lobby! Which game would you like to play?~~~~");
            printGameChoices();
            Game g = getGameChoice();
            System.out.println("...You're playing " + g.getGameName() + "...");

            g.play();
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            g.writeHighScore(f);
            // add one to game counts the hashmap, if you wanted to store some stats.
            String gameKey = g.getGameName();
            if (gameCounts.containsKey(gameKey)) {
                gameCounts.put(gameKey, gameCounts.get(gameKey) + 1);
            } else {
                gameCounts.put(gameKey, 1);
            }
        }
    }

    public static void loadGames() {
        games.clear();
        games.add(new BlackjackGame());
        games.add(new NumberGuessGame());
    }

    public static void printGameChoices() {
        int n = 1;
        for (Game s : games) {
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
