import java.util.*;
import java.io.*;
import java.math.*;

public class PalindromeSS {
	public void run() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("PalindromeSSIN.txt"));
		int n = Integer.parseInt(br.readLine());
	    while (n-->0) {
		    String s = br.readLine();
		    System.out.println(solve(s));
	    }
	}
	
	public String solve(String s) {
		return "";
	}
	
	public static void main (String[] args) throws Exception {
		new PalindromeSS().run();
	}
}
