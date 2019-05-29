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
		public String toString(){
			return key + " : " + value;
		}
		public Node next(){
			return next;
		}
		public void setNext(Node n){
			next = n;
		}
		public Object key(){
			return key;
		}
		public Object value(){
			return value;
		}
		public void setValue(Object v){
			value = v;
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
	
	public int size(){
			return keys.size();
	}
	public boolean containsKey(Key key){
		return keys.contains(key);
	}
	public Set<Key> getKeys(){
		return keys;
	}
	private Node getNode(Key key){
		Node node = buckets[hash(key)];
		while(node != null){
			if(node.key().equals(key)) return node;
			node = node.next();
		}
		return null;
	}
	public Value get(Key key){
		Node n = getNode(key);
		if (n == null) return null;
		return (Value) n.value();
	}
	public Value put(Key key, Value value){
		Value old = null;
		Node n = getNode(key);
		if (containsKey(key)){
			old = (Value) n.value();
			n.setValue(value);
		} else{
			keys.add(key);
			buckets[hash(key)] = new Node(key, value, buckets[hash(key)]);
		}
		return old;
	}
	public void printTable(){
		for(int i = 0; i < m; i++){
			System.out.print("[" + i + "] : ");
			Node node = buckets[i];
			while(node != null){
				System.out.print(node + " ");
				node = node.next();
			}
			System.out.println();
		}
		
	}

	
	public static void main(String [] args){
		HashST<String, Integer> d = new HashST<String, Integer> (13);
		for(String s : args){
			if(d.containsKey(s)){
				d.put(s, d.get(s) + 1);
			}else{
				d.put(s, 1);
			}
			
		}
		d.printTable();
	}

	
	
	

}
