import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;

public class Nicknames {
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("NicknamesIN.txt"));
		
		while (br.ready()) {
			String s = br.readLine();
			System.out.println(getMostRepeating(s)); 
		} 
	}
	//Do not modify above this line
	
	public static String getMostRepeating (String s) {
		//Put your code here!
		return s;
	}
}
