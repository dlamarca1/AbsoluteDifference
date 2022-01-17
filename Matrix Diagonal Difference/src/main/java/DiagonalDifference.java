import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DiagonalDifference {
    private static FileReader getFileReader() throws FileNotFoundException {
        return new FileReader("src\\main\\java\\squareMatrix1.txt");//reads first input file when provided with its path
    }
    private static FileReader getFileReader1() throws FileNotFoundException {
        return new FileReader("src\\main\\java\\squareMatrix2.txt");//reads second input file when provided with its path
    }
    public static void main(String[] args) throws IOException { //more files can be read by modifying x to the new amount of files being read in: BufferedReader[x] and Scanner[x]
        BufferedReader[] br = new BufferedReader[2];
        br[0] = new BufferedReader(getFileReader());
        br[1] = new BufferedReader(getFileReader1());
        Scanner[] sc = new Scanner[2];
        sc[0] = new Scanner(getFileReader());
        sc[1] = new Scanner(getFileReader1()); //if you want to read more files at the same time, please create an instance of br[] and sc[] for each file

        for (int n = 0; n < 2; n++) {  //where 2 is the number of input files, if more files are being read please change 2 to the current amount of files being read
            List<String[]> lines = new ArrayList<>();
            for (String line = br[n].readLine(); line != null; line = br[n].readLine()) {
                String[] fields = line.split(",");
                lines.add(fields);
            }
            String[][] strings = lines.toArray(new String[lines.size()][]); // identifies array size

            int rowsColumns = strings.length;// square matrix, rows = columns
            int[][] matrix = new int[rowsColumns][rowsColumns];
            while (sc[n].hasNextLine()) {
                for (int i = 0; i < matrix.length; i++) {
                    String[] line = sc[n].nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        matrix[i][j] = Integer.parseInt(line[j]);

                    }
                }
            } //reading provided txt file

            int sumLeftRight = 0;
            for (int i = 0; i < rowsColumns; i++) {
                sumLeftRight = sumLeftRight + matrix[i][i];
            } //getting the first diagonal value
            //System.out.println("Left-Right diagonal sum: " + sumLeftRight); //tests if left-right sum value is correct

            int sumRightLeft = 0;
            for (int i = 0; i < rowsColumns; i++) {
                for (int j = 0; j < rowsColumns; j++)
                    if ((i + j) == (rowsColumns - 1)) {
                        sumRightLeft = sumRightLeft + matrix[i][j];
                    }
            } //getting the second diagonal value
            //System.out.println("Right-Left diagonal sum: " + sumRightLeft); //tests if right-left sum value is correct

            System.out.println("Absolute Difference from file " + (n+1) + ": " + Math.abs(sumLeftRight - sumRightLeft)); //getting the absolute difference between the diagonals as a positive value
        }
    }
}


