import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Comparator;
public class Graph<E>{
	private ArrayList<Node<E>> nodes = new ArrayList<Node<E>>();

	public Graph(){
	}
	public ArrayList<Node<E>> getNodes(){
		return nodes;
	}
	public void addEdge(Node<E> a, Node<E> b, int weight){
		a.connect(b, weight);
		if(!nodes.contains(a)) nodes.add(a);
		if(!nodes.contains(b)) nodes.add(b);
	}
	public void addEdge(Node<E> a, Node<E> b){
		addEdge(a, b, 1);
	}
	public void print(){
		for(Node<E> n : nodes){
			for(Node<E> cn : n.connections().keySet()){
				System.out.println("(" + n + " --" + n.distanceTo(cn) + "-> " + cn + ")");
			}
		}
	}
	public void printEdgeList(){
		for(Node<E> node : nodes){
			System.out.println(node + " : " + node.connections());
		}
	}
	public ArrayList<Node<E>> djikstra(Node<E> start, Node<E> end){
		HashMap<Node<E>, Integer> dist = new HashMap<Node<E>, Integer>();
		HashMap<Node<E>, Node<E>> last = new HashMap<Node<E>, Node<E>>();
		HashSet<Node<E>> unvisited = new HashSet<Node<E>>(nodes);
		class SortByWeight implements Comparator<Node<E>>{ 
			public int compare(Node<E> a, Node<E> b){ 
				return dist.get(a) - dist.get(b); 
			} 
		} 
		PriorityQueue<Node<E>> pQueue = new PriorityQueue<Node<E>>(nodes.size(), new SortByWeight());		
		for(Node<E> n : nodes){
			dist.put(n, Integer.MAX_VALUE);
			last.put(n, null);
			
		}
		dist.put(start, 0);
		pQueue.add(start);
		boolean found = false;
		while(!found){
			Node<E> n = pQueue.poll();
			for(Node<E> cNode : n.connections().keySet()){
				if(unvisited.contains(cNode)){
					int d = n.distanceTo(cNode) + dist.get(n);
					if(d < dist.get(cNode)){
						dist.put(cNode, d);
						last.put(cNode, n);
						pQueue.remove(cNode);
					}
					pQueue.add(cNode);

					if(cNode == end) found = true;
				}
			}
			unvisited.remove(n);
		}
		Node<E> n = end;
		ArrayList<Node<E>> path = new ArrayList<Node<E>>();
		while(n != null){
			path.add(0, n);
			n = last.get(n);
		}
		return path;
	}
	public static <T> int pathLength(ArrayList<Node<T>> path){
		int length = 0;
		for(int i = 0; i < path.size() - 1; i++){
			length += path.get(i).distanceTo(path.get(i + 1));
		}
		return length;
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
		System.out.println();
		g.printEdgeList();
		System.out.println();

		ArrayList<Node<String>> path = g.djikstra(a, f);
		System.out.println(path + " " + pathLength(path));
		
		
	}
}
