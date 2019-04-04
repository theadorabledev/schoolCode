import java.util.ArrayList;

// How to run: $java Substets 3
// Here is code to complete question 2. 
// It uses an queue of ArrayLists.  
// ArrayLists are AP material.


public class Subsets{



	public static void main(String [] args){
		ArrayList<Integer> set = new ArrayList<Integer>();
		final int N = Integer.parseInt(args[0]);
		// create a list : [ 0, 1, ..., N-1]
		for(int i = 0; i < N; i++) set.add(i);
		System.out.println("set: " + set);
		// Creates a queue of ArrayLists
		MyQueue<ArrayList> q = new ArrayQueue<ArrayList>((int)(Math.pow(2,set.size())));
		q.add(new ArrayList<Integer>());
		for(Integer i : set){
			int s = q.size();
			for(int k = 0; k < s; k++){
				ArrayList<Integer> a = q.poll(); 
				ArrayList<Integer> b = (ArrayList<Integer>)a.clone();
				b.add(i);
				q.add(a);
				q.add(b);
			}
			//System.out.println(q);
		}
		System.out.println(q);

	}

}