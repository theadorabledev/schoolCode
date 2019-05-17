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
	///Update min
	Tree<E> node = head.getNext();
	if(compareNodes(head, min) < 0) min = head;
	while(node != head){
	    if(compareNodes(node, min) < 0) min = node;
	    node = node.getNext();
	}
	//Consolidate trees
	HashMap<Integer, Tree<E>> ranks = new HashMap<Integer, Tree<E>>();
	node = head.getNext();
	ranks.put(head.treeRank(), head);
	while(node != head){
	    //System.out.println(ranks);
	    Tree<E> nextNode = node.getNext();
	    Tree<E> compNode = ranks.get(node.treeRank());
	    if(compNode != null && compNode != node){
		ranks.remove(compNode.treeRank());
		Tree<E> newNode = merge(compNode, node);
		if(newNode == compNode){
		    remove(node);
		}else{
		    remove(compNode);
		}
		ranks.remove(compNode);
		ranks.remove(node);
		if(compNode == head || node == head) head = newNode;
		if(ranks.get(newNode.treeRank()) == null){
		    ranks.put(newNode.treeRank(), newNode);
		    node = nextNode;
		}else{
		    node = newNode;
		}
	    }else{
		ranks.put(node.treeRank(), node);
		node = nextNode;
	    }
	    //System.out.println(ranks);
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
	int maxDepth = head.treeRank();
	Tree<E> node = head.getNext();
	while(node != head){
	    maxDepth = Math.max(maxDepth, node.treeRank());
	    node = node.getNext();
	}
	String[] totalGraph = new String[maxDepth * 3];
	String[] graph = new String[maxDepth * 3];	
	for(int i = 0; i < graph.length; i++) totalGraph[i] = "";
	displayHelper(head, totalGraph, 0, 0);
	for(String s : totalGraph){
	    System.out.println(s + "");
	}
	equalizeGraph(totalGraph);
	node = head.getNext();
	while(node != head){		
	    graph = new String[maxDepth * 3];	
	    for(int i = 0; i < graph.length; i++) graph[i] = "";
	    displayHelper(node, graph, 0, 0);
	    for(String s : graph){
		System.out.println(s + "");
	    }
	    for(int i = 0; i < graph.length; i++){
		totalGraph[i] += graph[i];
	    }
	    equalizeGraph(totalGraph);
	    node = node.getNext();
			
	}		
	for(String s : totalGraph){
	    System.out.println(s + "");
	}
		
    }
    private void equalizeGraph(String[] graph){
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
    private int displayHelper(Tree<E> tree, String[] graph, int depth, int parentPos){
	int pos = 0;
	if(graph[depth * 2].length() < parentPos){
	    for(int i = 0; i < parentPos - graph[depth * 2].length() + 1; i++){
		graph[depth * 2] += "  ";
	    }
	}
	
	int startLen = graph[depth * 2].length();
	if(tree.treeRank() > 1){
	    int m = maxLength(graph);
	    if(graph[depth * 2].length() < m){
		for(int i = graph[depth * 2].length(); i < m; i++){
		    graph[depth * 2] += "-"; 
		}
	    }
	    graph[depth * 2] += tree.root() + ((depth == 0)? " " : "-");
	    int s = graph[depth * 2].length() - 2;
	    for(Tree<E> child : tree.children()){
		pos = displayHelper(child, graph, depth + 1, s);
	    }
	    while(graph[depth * 2].length() < pos) graph[depth * 2] += " "; 
	    String next = graph[depth * 2 + 2];
	    try{
		graph[depth * 2 + 2] = next.substring(0, next.length() - 1) + " ";
		//graph[depth * 2 + 2] = (next.substring(0, s + tree.children().size() * 2 - 1) + " " + next.substring(s + tree.children().size() * 2));
	    }catch (Exception e){
		System.out.println("Error : \n" + next);
	    }
	    while(graph[depth * 2 + 1].length() < s) graph[depth * 2 + 1] += " ";
	    graph[depth * 2 + 1] += "|";
	}else{
	    graph[depth * 2] += tree.root() + ((depth == 0)? " " : "-");
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
	for(int i = 0; i < 20; i ++){
	    fib.add(i % 9);
	}
	//fib.display();
	System.out.println(Arrays.asList(fib.toArray()));

	for(int i = 0; i < 1; i ++){
	    System.out.println(fib.deleteMin());
	    fib.print();
	    fib.display();
	}
		
    }
}
