package BuzzfeedQuiz;
import java.util.Scanner;

public class Question {
    // Fields
    String label;
    Answer[] possibleAnswers = new Answer[4];

    Question(String label) {
        this.label = label;
    }

    // Asks question and updates user profile based on their answer
    void ask(Scanner sc) { 
        System.out.println(this.label);
        
        // Prints out all the answer choices
        for (int i = 0; i < this.possibleAnswers.length; i++) {
            String choice = Integer.toString(i + 1);
            System.out.println("[" + choice + "]: " +
                    this.possibleAnswers[i].label);
        }

        // Stores the number that user inputs
        int ans = Tools.get_int(sc);
        Answer their_answer = possibleAnswers[ans - 1];

        // Updates user profile based on answer
        if (their_answer.personality != null) {
            Quiz.user.usr_personality_counts[their_answer.personality.index]++;
        } else {
            Quiz.user.usr_interests.add(their_answer.interest);
        }
    }

    
    


}
