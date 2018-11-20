import java.io.BufferedReader;
import java.util.*;
import java.lang.Math;
import java.io.FileReader;
import java.io.IOException;

public class Proximity {

	public static void main(String[] args) throws IOException {
		//turn input from file into arrays
		BufferedReader br = new BufferedReader(new FileReader("ProximityIN.txt"));
		
		while (br.ready()) {
			String[] data1 = br.readLine().split(" ");
			int[] l = new int[data1.length];
			for (int i = 0; i < data1.length; i++)
			{
				l[i] = Integer.parseInt(data1[i]);
			}
			String[] data2 = br.readLine().split(" ");
			int[] m = new int[data2.length];
			for (int i = 0; i < data2.length; i++)
			{
				m[i] = Integer.parseInt(data2[i]);
			}
			
			int[] output = twoClosest(l, m); //calls the method on the arrays
			
			for (int i = 0; i < output.length; i++)
				System.out.print(output[i] + " ");
		}
	}

//Do not modify above this line!
	
	public static int[] twoClosest (int[] a1, int[] a2)
	{
		//TODO: Fill out this method!
		return a2;
	}

}
