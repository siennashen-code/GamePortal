package BuzzfeedQuiz;
import java.util.ArrayList;

public class Person {
    String name;
    String phone_number;
    int[] usr_personality_counts;
    ArrayList<String> usr_interests;

    Person(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
        this.usr_personality_counts = new int[4];
        this.usr_interests = new ArrayList<String>();
    }

    Person(String name, String phone_number, int[] usr_personality, ArrayList<String> usr_interests) {
        this.name = name;
        this.phone_number = phone_number;
        this.usr_personality_counts = usr_personality;
        this.usr_interests = usr_interests;
    }

    double distance(Person other) { // Returns a double that represents how close user is with someone else (the
                                    // smaller the double, the closer the people)

        double euclidean_d = 0;
        for (int i = 0; i < usr_personality_counts.length; i++) { // Calculate euclidean distance of personality arrays
            euclidean_d += Math.pow(usr_personality_counts[i] - other.usr_personality_counts[i], 2);
        }
        euclidean_d = Math.sqrt(euclidean_d);

        double similarities = 0;
        for (int i = 0; i < usr_interests.size(); i++) { // Calculate how many shared interests there are
            if (usr_interests.get(i).equals(other.usr_interests.get(i))) {
                similarities++;
            }
        }

        return euclidean_d - similarities;

    }

    ArrayList<Personality> get_top_personalities() { // Returns ArrayList of personalities with the points
        ArrayList<Personality> top_personalities = new ArrayList<Personality>();

        int top_count_1 = -1;
        int top_index_1 = 0;
        int top_count_2 = -1;
        int top_index_2 = 0;

        for (int i = 0; i < usr_personality_counts.length; i++) { // Add in personalities with the two highest points
            if (usr_personality_counts[i] > top_count_1) {
                top_count_1 = usr_personality_counts[i];
                top_index_1 = i;
            } else if (usr_personality_counts[i] > top_count_2) {
                top_count_2 = usr_personality_counts[i];
                top_index_2 = i;
            }
        }

        top_personalities.add(Tools.index_to_personality(top_index_1));
        top_personalities.add(Tools.index_to_personality(top_index_2));

        for (int i = 0; i < usr_personality_counts.length; i++) { // Add in personalities that were tied with top personalities
            if (i != top_index_1 && i != top_index_2) {
                if (usr_personality_counts[i] == usr_personality_counts[top_index_2]) {
                    top_personalities.add(Tools.index_to_personality(i));
                }
            }
        }

        return top_personalities;
    }

    public Person get_match() { // Get person with shortest distance
        Person match = Quiz.others.get(0);
        double lowest = Quiz.user.distance(Quiz.others.get(0));

        for (Person other : Quiz.others) {// Find closest match
            if (Quiz.user.distance(other) < lowest) {
                lowest = Quiz.user.distance(other);
                match = other;
            }
        }

        return match;
    }


    ArrayList<Personality> get_common_personalities(Person other) { // return an ArrayList of common top personalities
        ArrayList<Personality> common_personalities = new ArrayList<Personality>();

        for (Personality personality1 : this.get_top_personalities()) {
            for (Personality personality2 : other.get_top_personalities()) {
                if (personality1.index == personality2.index) { // we can do this because each personality has a unique
                                                                // index
                    common_personalities.add(personality1);
                }
            }
        }

        return common_personalities;
    }

    ArrayList<String> get_common_interests(Person other) { // return ArrayList of common interests
        ArrayList<String> common_personalities = new ArrayList<String>();
        for (int i = 0; i < usr_interests.size(); i++) {
            if (usr_interests.get(i).equals(other.usr_interests.get(i))) {
                common_personalities.add(usr_interests.get(i));
            }
        }

        return common_personalities;

    }

    

}
