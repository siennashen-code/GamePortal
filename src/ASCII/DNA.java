package ASCII;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DNA {
    static final Random rand = new Random();

    static final int geneRow = 11; //length of gene array
    static final int geneCol = 15; //width of gene array
    static char[][] gene = new char[geneRow][geneCol]; //array containing characters of 1 gene

    static char[][] painting = new char[geneRow][120]; //array storing rows that will be printed in terminal
    public static void runDNA() throws Exception{

        makeBlankCanvas();
        
        long secondsToRun = 5;
        long durationNanos = TimeUnit.SECONDS.toNanos(secondsToRun);
        long endTime = System.nanoTime() + durationNanos;
        while(System.nanoTime() < endTime){
            makeGene();
            loadGene();
            for(int i = 0; i < geneRow; i++){
                printRow(i);
                Thread.sleep(60);
            }
        }

    } 

    static void makeBlankCanvas(){
      for(int i = 0; i < geneRow; ++i) {
         for(int j = 0; j < 120; ++j) {
            painting[i][j] = '\'';
         }
      }
    }

    static void loadGene(){ //updates painting based off generated gene array
        int row = 0;
        int col = 0;
        for(int i = 0; i < painting.length; i++){
            for(int j = 52; j < 52 + geneCol; j++){
                painting[i][j] = gene[row][col];
                col++;
            }
            col = 0;
            row++;
        }
    }

    static void makeGene(){ //creates array of one segment of the DNA helix
        for(int i = 0; i < geneRow; i++){ //first fill array with (') character
            for (int j = 0; j < geneCol; j++){
                gene[i][j] = '\'';
            }
        }

        gene[0][7] = '0'; //first layer

        for(int row = 1; row < geneRow; row++){ //for the rest of the layers
            
            int start = 0; //how far to the left the sides of each layer are
            
            if (row == 1|| row == 10){
                start = 4;
            } else if (row == 2|| row == 9){
                start = 2;
            } else if (row == 3 || row == 8){
                start = 1;
            }

            //DNA "sugar-phosphate backbone"
            gene[row][start] = '0';
            gene[row][start+1] = '0';

            gene[row][geneCol - start-1] = '0';
            gene[row][geneCol - start -2] = '0';

            //DNA nitrogenous bases with randomly generated lengths
            if(row == 2 || row == 4 || row == 5 || row == 7 || row == 9){
                for (int col = start + 2; col < geneCol-start-1; col++){
                    gene[row][col] = '=';
                }

                gene[row][rand.nextInt(start+2, geneCol-start-1)] = '.';
            }
        }
    }

    static void printRow(int index) {
      for(int col = 0; col < 120; ++col) {
         System.out.print(painting[index][col]);
      }

      System.out.println();
   }

}
