import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
public class Node<E>{
	private final E VALUE;
	//private ArrayList<Node<E>> connections = new ArrayList<Node<E>>();
	private HashMap<Node<E>, Integer> connections = new HashMap<Node<E>, Integer>();
	public Node(E value){
		VALUE = value;
	}
	public E getValue(){
		return VALUE;
	}
	public Set<Node<E>> getConnections(){
		return connections.keySet();
	}
	public HashMap<Node<E>, Integer> connections(){
			return connections;
	}
	public void connect(Node<E> n){
		//Adds path from this to n
		//connections.add(n);
		connect(n, 1);
	}
	public void connect(Node<E> n, int weight){
		connections.put(n, weight);
	}
	public int distanceTo(Node<E> n){
		return connections.get(n);
	}
	@Override
	public String toString(){
		return VALUE.toString();
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Node<E> n = (Node<E>) o;
		return VALUE.equals(n.getValue());
	}
	
	@Override
	public int hashCode() {
		return VALUE.hashCode();
		
	}
}
