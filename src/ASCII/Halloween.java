package ASCII;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Halloween {

    static final Random rand = new Random();

    static final String squashed_ghost1 = "   .---.     '     '   | o   o |  |   ~   | <|       |> |       \\_  \\       /    -----' ";
    static final String squashed_ghost2 = "   .---.     '     '   | @   @ |  |   U   | <|       |> |       \\_  \\       /    -----' ";
    static final int ghostRow = 8; // length of ghost
    static final int ghostCol = 11; // width of ghost

    static char[][] painting = new char[8][120]; // canvas whose rows we will iterate through

    public static void runHalloween() throws Exception {
        MakeEmptyCanvas();
        long secondsToRun = 5;
        long durationNanos = TimeUnit.SECONDS.toNanos(secondsToRun);
        long endTime = System.nanoTime() + durationNanos;
        while (System.nanoTime() < endTime) { //Ascii art should only run for 5 seconds

            double roll = rand.nextDouble(0, 3); // add no ghost, ghost1, or ghost2 to canvas
            if (roll < 1) {
                paintObject(squashed_ghost1, rand.nextInt(0, 110), ghostRow, ghostCol);
            } else if (roll < 2) {
                paintObject(squashed_ghost2, rand.nextInt(0, 110), ghostRow, ghostCol);
            }

            printRow(0);
            shiftUp();

            Thread.sleep(100);

        }

    }

    static void MakeEmptyCanvas() { // fills canvas with empty space
        for (int i = 0; i < ghostRow; i++) {
            for (int j = 0; j < 120; j++) {
                painting[i][j] = ' ';
            }
        }
    }

    static void paintObject(String characters, int col, int n_rows, int n_cols) { // adds characters onto canvas
        char[] squashed_object = characters.toCharArray(); // convert string to array

        int k = 0; // add characters to canvas given its dimensions (n_rows X n_cols) and column
                   // number of the leftmost side.
        for (int i = 0; i < n_rows; i++) {
            for (int j = col; j < col + n_cols; j++) {
                painting[i][j] = squashed_object[k];
                k++;
            }
        }

    }

    static void printRow(int index) {
        for (int col = 0; col < 120; col++) {
            System.out.print(painting[index][col]);
        }

        System.out.println();
    }

    static void shiftUp() throws InterruptedException { // shift every row up in the canvas
        for (int row = 0; row < ghostRow - 1; row++) {
            painting[row] = painting[row + 1];
        }

        painting[7] = emptyRow();
    }

    static char[] emptyRow() { // create an empty row (for the bottom-most row of canvas after a shift)
        char[] row = new char[120];
        for (int i = 0; i < 120; i++) {
            row[i] = ' ';
        }

        return row;
    }
}
