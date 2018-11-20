import java.util.*;
import java.io.*;
import java.math.*;

public class Homefun {
	public void run() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("HomefunIN.txt"));
		//given equations
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		String[][] equations = new String[n][2];
		double[] values = new double[n];
	    for (int i = 0; i < n; i++) { 
		    st = new StringTokenizer(br.readLine());
		    String num = st.nextToken();
		    String denom = st.nextToken();
		    double ans = Double.parseDouble(st.nextToken());
		    equations[i] = new String[]{num, denom};
		    values[i] = ans;
	    }
	    //queries
	    st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		String[][] queries = new String[n][2];
		for (int i = 0; i < n; i++) { 
		    st = new StringTokenizer(br.readLine());
		    String num = st.nextToken();
		    String denom = st.nextToken();
		    queries[i] = new String[]{num, denom};
	    }
		double[] answers = solve(equations, values, queries);
		for (double a : answers) {
			System.out.printf("%.2f%n",a);
		}
	}
	
	public double[] solve(String[][] equations, double[] values, String[][] queries) {
		return new double[]{};
	}
	
	public static void main (String[] args) throws Exception {
		new Homefun().run();
	}
}
