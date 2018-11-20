import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
 
public class Rectangles {
   
	// Before submitting, make sure the main method hasn't been changed!
	// DO NOT MODIFY BELOW THIS LINE
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("RectanglesIN.txt"));
 
		String[] data = br.readLine().split(" ");
		int testCases = Integer.parseInt(data[0]);
		for( ; testCases > 0; testCases--) {
			data = br.readLine().split(" ");
			int[] field = new int[4];
			for(int i=0; i<4; i++) field[i] = Integer.parseInt(data[i]);
			data = br.readLine().split(" ");
			int N = Integer.parseInt(data[0]);
			int M = Integer.parseInt(data[1]);
			int[][] list1 = new int[N][4];
			int[][] list2 = new int[N][4];
			for(int i = 0; i < N; i++){
			    data = br.readLine().split(" ");
			    for(int j = 0; j < 4; j++) {
			    	list1[i][j] = Integer.parseInt(data[j]);
			    }
			}
			for(int i = 0; i < M; i++){
			    data = br.readLine().split(" ");
			    for(int j = 0; j < 4; j++) {
			  		list2[i][j] = Integer.parseInt(data[j]);
			    }
			}
		    System.out.println( solve(field, list1, list2) );
		}
		br.close();
	}	
	
	// DO NOT MODIFY ABOVE THIS LINE
 
    // Fill out the body of this method
  	public static long solve(int[] field,  int[][] l1, int[][] l2) {
	  	return 0;
  	}
}