import java.util.HashSet;
import java.util.Iterator;

public class MathSet<Key> implements Iterable{

	private HashSet<Key> set;  // set is a subset of the universe
	private HashSet<Key> universe; // a set of the potential items in a MathSet
	private int size = 0;
	public MathSet(Key[] universe){
		set = new HashSet<Key>();
		this.universe = new HashSet<Key>();
		for (Key x: universe)
		this.universe.add(x);
	}
	// post: key is added to the set if key is both a member of the universe
	//       and not already a member of the set. Otherwise, returns false.
	public boolean add(Key key){
		if(universe.contains(key) || set.contains(key)) return false;
		set.add(key);
		size ++;
		return true;
	}

	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return (size == 0);
	}
	public Iterator<Key> iterator(){
		return set.iterator();
	}

	public void union(MathSet<Key> a){
	for(Object k : a){
			set.add((Key) k);
		}
	}



	public String toString(){
		return  set.toString();
	}

	public static void main(String [] args){
		MathSet<String> A = new MathSet<String>(args);
		System.out.println("A: " + A);
		
		for (String x : args){
			double r = Math.random();
			if (r < 0.5)
			A.add(x);
		}
		System.out.println("A: " + A);
	}


}
