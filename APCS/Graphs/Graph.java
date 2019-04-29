import java.util.ArrayList;

public class Graph<E>{
    private ArrayList<Edge<E>> edges = new ArrayList<Edge<E>>();
    private ArrayList<Node<E>> nodes = new ArrayList<Node<E>>();
    private boolean undirected;

    public Graph(boolean undirected){
	this.undirected = undirected;
    }
    public Graph(ArrayList<Edge<E>> e, boolean undirected){
	edges = e;
	this.undirected = undirected;
    }
    public ArrayList<Node<E>> getNodes(){
	return nodes;
    }
    public void addEdge(Edge<E> edge){
	if(!edges.contains(edge)){
	    edges.add(edge);
	    if(!nodes.contains(edge.getStart())) nodes.add(edge.getStart());
	    if(!nodes.contains(edge.getEnd())) nodes.add(edge.getEnd());
	}
    }
    public void print(){
	for(Edge<E> edge : edges){
	    System.out.println(edge);
	}
    }
    public void printEdgeList(){
	for(Node<E> node : nodes){
	    System.out.println(node + " : " + node.getConnections());
	}
    }
    public static void main(String[] args){
	Graph<String> g = new Graph<String>(true);
	Node<String> a = new Node<String>("A");
	
	
    }
}
