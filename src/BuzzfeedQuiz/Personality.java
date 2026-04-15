package BuzzfeedQuiz;

public class Personality {
    String label;
    String description_2nd; // description in the first and second person
    String description_3rd; 
    int index; // index in user's personality array

    Personality(String label, String description_2nd, String description_3rd, int index){
        this.label = label;
        this.description_2nd = description_2nd;
        this.description_3rd = description_3rd;
        this.index = index;
    }
}
