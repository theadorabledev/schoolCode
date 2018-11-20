import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
public class Elevator {
   
    // Before submitting, make sure the main method hasn't been changed!
    // DO NOT MODIFY BELOW THIS LINE
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ElevatorIN.txt"));
        String[] data = br.readLine().split(" ");
        int testCases = Integer.parseInt(data[0]);
        for( ; testCases > 0; testCases--) {
	        data = br.readLine().split(" ");
	        int N = Integer.parseInt(data[0]);
	        int maxDiff = Integer.parseInt(data[1]);
	        List<Integer> list = new ArrayList<Integer>();
            data = br.readLine().split(" ");
	        for(int i = 0; i < N; i++){
	            list.add(Integer.parseInt(data[i]));
	        }
	        System.out.println( solve(list, maxDiff) ? "True" : "False" );
        }
        br.close();
    }   
    
    // DO NOT MODIFY ABOVE THIS LINE
 
    // Fill out the body of this method
    public static boolean solve(List<Integer> list, int dif) {
		return false;
    }
}