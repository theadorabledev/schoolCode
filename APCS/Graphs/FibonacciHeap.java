import java.util.*;
public class FibonacciHeap<E> extends PriorityQueue<E> implements Iterable<E>{
	private Tree<E> head;
	private Tree<E> min;
	private Comparator<E> comp;
	private boolean naturalOrder = false;
	private int size = 0;
	private HashMap<E, Tree<E>> locations = new HashMap<E, Tree<E>>();
	//Constructors
	public FibonacciHeap(){
		this(null);
	}
	public FibonacciHeap(Comparator<E> comp){
		head = new Tree<E>(null, null, null);
		head.setNext(head);
		head.setPrevious(head);
		min = head;
		if(comp != null){
			this.comp = comp;
		}else{
			class MyComparator implements Comparator<E> {
				public int compare(E a, E b) {
					return ((Comparable) a).compareTo(b);
				}
			};
			this.comp = new MyComparator();
			naturalOrder = true;
		}
	}	
	//FibonacciHeap method implementations
	public E min(){
		return min.root();
	}
	public void insert(Tree<E> node){
		node.setNext(head.getNext());
		node.setPrevious(head);
		head.getNext().setPrevious(node);
		head.setNext(node);
		if(comp.compare(node.root(), min()) < 0){
			min  = node;
		}
		size++;
	}
	public void insert(E val){
		if(size == 0){
			head.setRoot(val);
			locations.put(val, head);
			size++;
		}else{
			Tree<E> t = new Tree<E>(val);
			locations.put(val, t);
			insert(t);
		}
	}
	public Tree<E> merge(Tree<E> a, Tree<E> b){ //returns the new parent
		if(compareNodes(a, b) > 0){
			b.addChild(remove(a));
			return b;
		}
		else{
			a.addChild(remove(b));
			return a;
		}
	}
	public Tree<E> remove(Tree<E> node){
		Tree<E> next = node.getNext();
		Tree<E> last = node.getPrevious();
		next.setPrevious(last);
		last.setNext(next);
		if(node == head) head = node.getPrevious();
		return node;
	}
	public E deleteMin(){
		//Move min's children to list
		for(Tree<E> child : min.children()){
			insert(child);
		}
		//Remove min
		Tree<E> m = min;
		remove(min);
		if(min == head) head = min.getNext();
		///Update min
		min = head;
		Tree<E> node = head.getNext();
		int topSize = 1;
		while(node != head ){
			if(compareNodes(node, min) < 0) min = node;
			node = node.getNext();
			topSize++;
		}
		//Consolidate trees
		HashMap<Integer, Tree<E>> ranks = new HashMap<Integer, Tree<E>>();
		node = head.getNext();
		ranks.put(head.treeRank(), head);
		int s = 0;
		while(true){
			Tree<E> nextNode = node.getNext();
			Tree<E> lastNode = node.getPrevious();
			Tree<E> compNode = ranks.get(node.treeRank());
			if(compNode != null && compNode != node){	
				topSize--;
				s=0;
				ranks.remove(compNode.treeRank());
				Tree<E> newNode = merge(compNode, node);
				if(newNode == compNode){
					remove(node);
				}else{
					remove(compNode);
				}
				ranks.remove(compNode.treeRank());
				ranks.remove(node.treeRank());
				if(compNode == head || node == head) head = newNode;
				if(ranks.get(newNode.treeRank()) == null){
					ranks.put(newNode.treeRank(), newNode);
					node = nextNode;
				}else{
					node = newNode;
				}
			}else if(s == topSize){//compNode == node){
				break;
			}else{
				ranks.put(node.treeRank(), node);
				node = nextNode;
				s++;
			}
			if(s == size()){
				break;
			}
		}
		size--;
		return m.root();
	}
	public int compareNodes(Tree<E> a, Tree<E> b){
		return comp.compare(a.root(), b.root());
	}
	public void decreaseKey(Tree<E> tree, E val){
		locations.remove(tree.root());
		tree.setRoot(val);
		if(tree.parent() == null){
			Tree<E> node = head.getNext();
			if(compareNodes(head, min) < 0) min = head;
			while(node != head){
				if(compareNodes(node, min) < 0) min = node;
				node = node.getNext();
			}
		}else{
			if(comp.compare(val, tree.parent().root()) < 0){
				cut(tree);
			}
		}
		locations.put(val, tree);
	}
	public void decreaseKey(E val, E newVal){
		decreaseKey(locations.get(val), newVal);
	}
	public void cut(Tree<E> tree){
		Tree<E> parent = tree.parent();
		parent.children().remove(tree);
		tree.unmark();
		insert(tree);
		tree.setParent(null);
		if(!parent.marked() && parent.parent() != null){
			parent.mark();
		}else{
			cut(parent);
		}
	}
	//Printing and displaying methods
	public void print(){
		System.out.println("--------------");
		Tree<E> node = head.getNext();
		System.out.println(head);
		while(node != head){
			System.out.println(node);
			node = node.getNext();
		}
		System.out.println("--------------");
	}
	public void display(){
		if(size == 0) return;
		// Get maximum tree depth
		int maxDepth = head.treeRank();
		Tree<E> node = head.getNext();
		while(node != head){
			maxDepth = Math.max(maxDepth, node.treeRank());
			node = node.getNext();
		}
		String[] totalGraph = new String[maxDepth * 3];
		String[] graph = new String[maxDepth * 3];	
		for(int i = 0; i < graph.length; i++) totalGraph[i] = "";
		displayHelper(head, totalGraph, 0, 0, true);//Makes the trees graph
		equalizeGraph(totalGraph);
		node = head.getNext();
		while(node != head){//Loops through nodes, graphing and adding to the original		
			graph = new String[maxDepth * 3];	
			for(int i = 0; i < graph.length; i++) graph[i] = "";
			displayHelper(node, graph, 0, 0, true);
			for(int i = 0; i < graph.length; i++){
				totalGraph[i] += graph[i];
			}
			equalizeGraph(totalGraph);
			node = node.getNext();		
		}	
		for(String s : totalGraph){
			System.out.println(s);
		}
		
	}
	private void equalizeGraph(String[] graph){//Sets all strings to an equal level
		int max = maxLength(graph);
		for(int i = 0; i < graph.length; i++){
			while(max > graph[i].length()) graph[i] += ((i == 0) ? "-" : " ");
		}
		graph[0] = graph[0].replace(" ", "-");
	}
	public int maxLength(String[] graph){
		int max = 0;
		for(String s : graph) max = Math.max(max, s.length());
		return max;
	}
	private int displayHelper(Tree<E> tree, String[] graph, int depth, int parentPos, boolean lastChild){
		int pos = 0;
		while(graph[depth * 2].length() < parentPos){//Places first child under parent
			graph[depth * 2] += " ";
		}
		int startLen = graph[depth * 2].length();//How long this level was before any changes were made
		if(tree.treeRank() > 1){//If kids
			int m = maxLength(graph);//How wide the graph is && Tree's starting position
			while(graph[depth * 2].length() < m) graph[depth * 2] += "-"; //Moves parent farther so as not to have its children collide with others
			graph[depth * 2] += tree.root() + (lastChild ? " " : "-");//Removes last childs trailing -
			int childNum = 0;
			for(Tree<E> child : tree.children()){//Recursively prints the trees children
				childNum++;
				pos = displayHelper(child, graph, depth + 1, m, childNum == tree.children().size());
			}
			while(graph[depth * 2 + 1].length() < m) graph[depth * 2 + 1] += " ";
			graph[depth * 2 + 1] += "|";//Adds vertical connecting lines from  parent to child
		}else{
			graph[depth * 2] += tree.root() + (lastChild ? " " : "-");//Removes last childs trailing -
		}
		return startLen;
		
	}	
	//PriorityQueue methods
	public boolean add(E val){
		insert(val);
		return true;
	}
	public boolean offer(E val){
		return add(val);
	}
	public void clear(){
		while(size > 0){
			poll();
		}
	}
	public Comparator<? super E> comparator(){
		if(naturalOrder) return null;
		return comp;
	}
	public boolean contains(Object o){
		E val = (E) o;
		if(contains(val, head)) return true;
		Tree<E> node = head.getNext();
		while(node != head){
			if(contains(val, node)) return true;
		}
		return false;
	}
	public Iterator<E> iterator(){
		ArrayList<E> list = new ArrayList<E>();
		Tree<E> node = head.getNext();
		addToList(head, list);
		while(node != head){
			addToList(node ,list);
			node = node.getNext();
		}
		return list.iterator();
	}	
	private void addToList(Tree<E> tree, ArrayList list){
		list.add(tree.root());
		for(Tree<E> child : tree.children()){
			addToList(child, list);
		}
	}	
	public E peek(){
		return min();
	}
	public E poll(){
		if(size == 0) return null;
		return deleteMin();
	}
	public boolean remove(Object o){
		if(locations.containsKey(o)){
			decreaseKey(locations.get(o), min.root());
			poll();
			return true;
		}
		return false;
	}
	public int size(){
		return size;
	}
	public Object[] toArray(){
		Object[] arr = new Object[size];
		int i = 0;
		for(E val : this){
			arr[i] = val;
			i++;
		}
		return arr;
	}
	public <T> T[] toArray(T[] a){
		int i = 0;
		for(Object o : toArray()){
			a[i] = (T) o;
			i++;
		}
		return a;
	}
	//PriorityQueue helper methods
	public boolean contains(E val, Tree<E> tree){
		if(tree.root().equals(val)) return true;
		if(comp.compare(val, tree.root()) >= 0){
			for(Tree<E> child : tree.children()){
				if(contains(val, child)) return true;
			}
		}
		return false;
	}	
	public static void main(String[] args){
		FibonacciHeap<Integer> fib = new FibonacciHeap<Integer>();
		for(int i = 0; i < Integer.parseInt(args[0]); i ++){
			fib.add(i);
		}
		fib.display();
		System.out.println(Arrays.asList(fib.toArray()));

		for(int i = 0; i < Integer.parseInt(args[1]); i ++){
			System.out.println(fib.deleteMin());
			fib.print();
			fib.display();
		}
		
	}
}
