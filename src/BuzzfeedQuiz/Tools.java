package BuzzfeedQuiz;
import java.util.Scanner;

public class Tools { // Some random methods that help declutter other classes
    public static boolean all_letters(String str) { // Returns whether string is made of all letters or not
        for (char c : str.toCharArray()) {
            if (!(Character.isLetter(c))) {
                return false;
            }
        }
        return true;
    }

    public static boolean all_digits(String str) { // Returns whether string is made of all digits or not
        for (char c : str.toCharArray()) {
            if (!(Character.isDigit(c))) {
                return false;
            }
        }
        return true;
    }

    public static int get_int(Scanner sc) { // Forces user to input an acceptable integer
        int ans;

        if (!(sc.hasNextInt())) { // If user enters a non-integer
            System.out.println("**Unidentifiable input. Please enter an integer between 1 and 4.");
            sc.next();
            ans = get_int(sc);
        } else {
            ans = sc.nextInt();
        }

        if (ans < 1 || ans > 4) { // If user's integer is out of bounds
            System.out.println("**Unidentifiable input: Please enter an integer between 1 and 4.");
            ans = get_int(sc);
        }

        return ans;
    }

    public static void get_1(Scanner sc) { // Forces user to input 1 to continue
        // requires 1 to keep going
        String play = sc.next();
        if (!play.equals("1")) {
            System.out.println("** Unidentifiable input. Please enter '1' to play:");
            get_1(sc);
        }

    }

    public static String get_name(Scanner sc) { // Forces user to input their name (all letters or spaces) to
                                                // continue
        String ans = sc.next();

        if (!(Tools.all_letters(ans))) {
            System.out.println("** Unidentifiable input. Please enter your first name in letters only:");
            ans = get_name(sc);
        }

        return ans;
    }

    public static String get_number(Scanner sc) { // Forces user to input a 10-digit phone number or skip to
        String ans = sc.next();

        if (ans.equals("x")) {
            ans = "NA";
        } else if (!Tools.all_digits(ans)) {
            System.out.println(
                    "** Unidentifiable input. Please enter your 10-digit number in digits only (or 'x' to skip)");
            ans = get_number(sc);
        } else if (ans.length() != 10) {
            System.out.println(
                    "** Unidentifiable input. Enter your number in exactly 10 digits (or 'x' to skip)");
            ans = get_number(sc);
        }

        return ans;
    }

    public static Personality index_to_personality(int i) { // Returns personality given its corresponding index
        if (i == 0) {
            return Quiz.independent;
        } else if (i == 1) {
            return Quiz.creative;
        } else if (i == 2) {
            return Quiz.charming;
        } else {
            return Quiz.adventurous;
        }
    }

    public static String condense_to_string(Person user) { // Converts Person object to a String that is stored in csv
        String row = "";

        row += user.name + ",";
        row += user.phone_number + ",";

        for (int count : user.usr_personality_counts) {
            row += count + ",";
        }

        for (int i = 0; i < 3; i++) {
            row += user.usr_interests.get(i);
            if (i != 2) {
                row += ",";
            }
        }

        return row;
    }

}
