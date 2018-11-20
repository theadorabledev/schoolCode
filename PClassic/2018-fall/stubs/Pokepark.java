import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pokepark {
	// Before submitting, make sure the main method hasn't been changed!
    // DO NOT MODIFY BELOW THIS LINE
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("PokeparkIN.txt"));
        String[] data = br.readLine().split(" ");
        int testCases = Integer.parseInt(data[0]);
        for( ; testCases > 0; testCases--) {
	        data = br.readLine().split(" ");
	        int N = Integer.parseInt(data[0]);
	        int[] list = new int[N];
            data = br.readLine().split(" ");
	        for(int i = 0; i < N; i++){
	            list[i]=Integer.parseInt(data[i]);
	        }
	        System.out.println( solve(list) );
        }
        br.close();
       
    }   
    
    // DO NOT MODIFY ABOVE THIS LINE
 
    // Fill out the body of this method
    public static int solve(int[] list) {
        return 0;
    }
}
