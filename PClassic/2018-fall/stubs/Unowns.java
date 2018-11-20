import java.io.*;
import java.math.*;
import java.util.*;
 
public class Unowns {
   
	// Before submitting, make sure the main method hasn't been changed!
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("UnownsIN.txt"));

		while (br.ready()) {
			String[] data = br.readLine().split(" ");
			int[] A = new int[data.length - 1];
			int k = Integer.parseInt(data[0]);
			for (int i = 0; i < data.length - 1; i++) A[i] = Integer.parseInt(data[i + 1]);
			
			int[] ans = unowns(k, A);
			StringBuilder sb = new StringBuilder();
			for (int v : ans) {
				sb.append(v + " ");
			}
			System.out.println(sb.toString().trim());
		}
		br.close();
	}

	public static int[] unowns(int k, int[] lst) {
		return null;
	}
}