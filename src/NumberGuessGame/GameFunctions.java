package NumberGuessGame;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameFunctions { //A collection of methods for the Number Guessing Game
    int guesses;
    int numToGuess;
    static Scanner sc = new Scanner(System.in);
    ArrayList<Integer> previous_guesses = new ArrayList<>();
    
    void game(){
        System.out.println("Let's set the range of numbers I can choose.");
        System.out.println("What's the lowest number: ");
        int low = getLowerBound();
        System.out.println(low);
        System.out.println("What's the highest number: ");
        int up = getUpperBound(low);
        System.out.println(up);

        guesses = 0;
        Random rand = new Random();
        numToGuess = rand.nextInt(low, up+1);

        System.out.println("Ok! I'm thinking of a number from " + low + " to " + up);
        gamePlay(low, up);
    }

    public int getLowerBound(){  
        if (sc.hasNextInt()){
            return sc.nextInt();
        } else {
            System.out.println("Integer please! Try again.");
            sc.next();
            return getLowerBound();
        }

    }
    
    public int getUpperBound(int low){
        int up = 0;
        if (sc.hasNextInt()){
            up = sc.nextInt();
            if (up <= low){
                System.out.println("Your upper bound must be greater than the lower! Try again.");
                
                return getUpperBound(low);
            } else{
                return up;
            }
        } else {
            System.out.println("Integer please! Try again.");
            sc.next();
            return getUpperBound(low);
        }
    }

    void gamePlay(int low, int up){

        int guess = getGuess(); // gets the user guess by calling getGuess()
        previous_guesses.add(guess);
        guesses++;

         if (numToGuess > guess) {
            System.out.println(">>> My number is higher! Try again.");
            gamePlay(low, up);
        } else if (numToGuess < guess) {
            System.out.println(">>> My number is lower! Try again");
            gamePlay(low, up);
        } else {
            System.out.println("---DONE  PLAYING---");
            if (guesses == 1){
                System.out.println("You got it! You did that in 1 guess!");
            } else {
                System.out.println("You got it! You did that in " + guesses + " guesses!");
            }
            
        }
    }


    int getGuess() {
        
        if (sc.hasNextInt()) {
            int guess = sc.nextInt();
            if (previous_guesses.contains(guess)) {
                System.out.println("You already guessed that dummy. Try again.");
                return getGuess();
            }
            
            return guess;
    
        } else {
            System.out.println("Integer please");
            sc.next();
            return getGuess();
        }


    }

    int getNumGuesses() {
        return guesses;
    }


}
