import java.util.*;
import java.io.*;
import java.math.*;

public class Portals {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("PortalsIN.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
	    while (n-->0) {
		    st = new StringTokenizer(br.readLine());
		    int r = Integer.parseInt(st.nextToken());
		    int c = Integer.parseInt(st.nextToken());
		    char[][] map = new char[r][c];
		    for (int i = 0; i < r; i++) {
		    	map[i] = br.readLine().toCharArray();
		    }
		    System.out.println(distance(map));
	    }
	}
	
	public static int distance(char[][] map) {
		return 0;
	}
}
