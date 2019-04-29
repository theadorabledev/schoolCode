import java.util.ArrayList;

public class Edge<E>{
    private Node<E> from;
    private Node<E> to;
    private boolean undirected;
    public Edge(Node<E> from, Node<E> to){
	this(from, to, false);
    }
    public Edge(Node<E> from, Node<E> to, boolean undirected){
	this.from = from;
	this.to = to;
	from.connect(to);
	this.undirected = undirected;
	if(undirected) to.connect(from);
    }
    public Node<E> getStart(){
	return from;
    }
    public Node<E> getEnd(){
	return to;
    }
    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;

	Edge e = (Edge) o;
	if (!from.equals(e.getStart())) return false;
	return to.equals(e.getEnd());
    }
	
    @Override
    public int hashCode() {
	int result = from.hashCode();
	result = 31 * result + to.hashCode();
	return result;
    }
    @Override
    public String toString(){
	    
	String arrow = (undirected) ?  "<-> " : " -> ";
	return "("+ from + arrow + to + ")";
	    
    }
    
}
