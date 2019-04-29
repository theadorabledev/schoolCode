import java.util.ArrayList;

public class Node<E>{
    private final E VALUE;
    private ArrayList<Node<E>> connections = new ArrayList<Node<E>>();
    public Node(E value){
	VALUE = value;
    }
    public E getValue(){
	return VALUE;
    }
    public ArrayList<Node<E>> getConnections(){
	return connections;
    }
    public void connect(Node<E> n){
	//Adds path from this to n
	connections.add(n);
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
