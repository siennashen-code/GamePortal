package ASCII;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Game.GameWriteable;
import Game.ErrorCheck;

public class AsciiGame implements GameWriteable {

    static Scanner sc = new Scanner(System.in);

    public String getGameName() {
        return "ASCII art";
    }

    public void play() {

        System.out.println("\n---Ascii Art---");
        System.out.println("Which art would you like to see?");
        System.out.println("[1]: Ghosts");
        System.out.println("[2]: DNA");

        int userInput = ErrorCheck.getInt(sc); // get user choice
        while (userInput != 1 && userInput != 2) {
            System.out.println("Choose either 1 or 2!");
            userInput = ErrorCheck.getInt(sc);
        }

        System.out.println();

        if (userInput == 1) { // run corresponding program
            try {
                Halloween.runHalloween();
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            try {
                DNA.runDNA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String getScore() {
        return null;
    }

    public boolean isHighScore(String score, String currentHighScore) {
        return false;
    }

    @Override
    public void writeHighScore(File f) {
        System.out.println("\n---Program Over---");
    }

}
