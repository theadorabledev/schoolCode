import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.HashMap;
public class Graph<E>{
    private ArrayList<Edge<E>> edges = new ArrayList<Edge<E>>();
    private ArrayList<Node<E>> nodes = new ArrayList<Node<E>>();
    public static class DjikstraNode{
    }
    public Graph(){
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
    public void addEdge(Node<E> a, Node<E> b){
	addEdge(new Edge<E>(a, b));
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
    public int djikstra(Node<E> start, Node<E> end){
	HashMap<Node<E>, Integer> dists = new HashMap<Node<E>, Integer>();
	HashSet<Node<E>> unvisited = new HashSet<Node<E>>();
	for(Node<E> n : nodes){
	    dists.put(n, Integer.MAX_VALUE);
	}
	dists.put(start, 0);
	return 0;
    }
    public static void main(String[] args){
	Graph<String> g = new Graph<String>();
	Node<String> a = new Node<String>("A");
	Node<String> b = new Node<String>("B");
	Node<String> c = new Node<String>("C");
	Node<String> d = new Node<String>("D");
	Node<String> e = new Node<String>("E");
	Node<String> f = new Node<String>("F");
	g.addEdge(a, b);
	g.addEdge(b, c);
	g.addEdge(b, d);
	g.addEdge(c, d);
	g.addEdge(d, e);
	g.addEdge(e, f);
	g.addEdge(e, a);

	g.print();
	g.printEdgeList();
	
    }
}
