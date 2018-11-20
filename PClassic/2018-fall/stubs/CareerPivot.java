import java.io.*;
import java.math.*;
import java.util.*;
 
public class CareerPivot {
   
	// Before submitting, make sure the main method hasn't been changed!
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("CareerPivotIN.txt"));

		while (br.ready()) {
			String[] data = br.readLine().split(" ");
			int[] A = new int[data.length];
			for (int i = 0; i < data.length; i++) A[i] = Integer.parseInt(data[i]);
			System.out.println(careerPivot(A));
		}
		br.close();
	}

	public static int careerPivot(int[] A) {
		return 0;
	}
}
