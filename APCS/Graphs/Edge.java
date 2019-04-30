import java.util.ArrayList;

public class Edge<E>{
    private Node<E> from;
    private Node<E> to;
    private int weight;
    public Edge(Node<E> from, Node<E> to){
	this(from, to, 1);
    }

    public Edge(Node<E> from, Node<E> to, int weight){
	this.from = from;
	this.to = to;
	this.weight = weight;
	from.connect(to);
    }
    public int weight(){
	return weight;
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
	    
	String arrow = " -> ";//(undirected) ?  " <-> " : " -> ";
	return "("+ from + arrow + to + ")";
	    
    }
    
}
