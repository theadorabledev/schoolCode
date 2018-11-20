import java.io.*;
import java.math.*;
import java.util.*;
 
public class GottaCatchEmAll {
   
  // Before submitting, make sure the main method hasn't been changed!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("GottaCatchEmAllIN.txt"));

        while (br.ready()) {
            int n = Integer.parseInt(br.readLine());
            int rows = Integer.parseInt(br.readLine());
            String[] data = br.readLine().split(", ");
            int[][] A = new int[rows][data.length];
            
            for (int i = 0; i < data.length; i++) {
                A[0][i] = Integer.parseInt(data[i]);
            }
            for (int i = 1; i < rows; i++) {
                data = br.readLine().split(", ");
                for (int j = 0; j < data.length; j++) {
                    A[i][j] = Integer.parseInt(data[j]);
                }
            }
            System.out.println(gottaCatchEmAll(n, rows, A));
        }
        br.close();
    }

    public static String gottaCatchEmAll(int n, int rows, int[][] A) {
        return "";
    }
}