import java.io.*;
import java.math.*;
import java.util.*;
 
public class BeatThatClefairy {
   
  // Before submitting, make sure the main method hasn't been changed!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("BeatThatClefairyIN.txt"));

        while (br.ready()) {
            int n = Integer.parseInt(br.readLine());
            String[] data = br.readLine().split(", ");
            int[] A = new int[data.length];
            for (int i = 0; i < data.length; i++) A[i] = Integer.parseInt(data[i]);
            System.out.println(beatThatClefairy(A, n));
        }
        br.close();
    }

    public static int beatThatClefairy(int[] A, int hp) {
        return 0;
    }
}