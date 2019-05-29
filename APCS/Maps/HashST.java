import java.util.HashSet;
import java.util.Set;


public class HashST<Key,Value>{

    private static  class Node{
	private Object key;
	private Object value;
	private Node next;

	public Node(Object key, Object value, Node next){
	    this.key = key;
	    this.value = value;
	    this.next = next;
	}

    }

    // instance variables
    private int m;   // # of buckets
    private Node[] buckets;
    private HashSet<Key> keys;
   
    
    
    public HashST(int capacity){
	m = capacity;
	buckets = new Node[m];
	keys = new HashSet<Key> ();
    }

    public int hash(Object key){
	return Math.abs(key.hashCode()) % m;
    }



    
    public static void main(String [] args){
	HashST<String, Integer> d = new HashST<String, Integer> (13);
    }

    
    
    

}
