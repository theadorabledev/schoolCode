import java.util.*;
public class FibonacciHeap<E>{
    private DNode<Tree<E>> head;
    private DNode<Tree<E>> min;
    private Comparator<E> comp;
    private int size = 0;
    public FibonacciHeap(){
	this(null);
    }
    public FibonacciHeap(Comparator<E> comp){
	head = Tree<E>(null, null, null);
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
	}
    }
    public int size(){
	return size;
    }
    public E min(){
	return min.getValue().root();
    }
    public void insert(Tree<E> t){
	Tree<E> node = new Tree<E>(t, head, head.getNext());
	head.getNext().setPrevious(node);
	head.setNext(node);
	if(comp.compare(t.root(), min()) < 0){
	    min  = node;
	}
	size++;
    }
    public void insert(E val){
	if(size == 0){
	    head.setRoot(val);
	    size++;
	}else{
	    Tree<E> t = new Tree<E>(val);
	    insert(t);
	}
    }
    public void add(E val){
	insert(val);
    }
    public DNode<Tree<E>> merge(Tree<E> a, Tree<E> b){ //returns the new parent
	if(compareNodes(a, b) > 0){
	    b.addChild(remove(a));
	    return b;
	}
	else{
	    a.addChild(remove(b));
	    return a;
	}
    }
    public DNode<Tree<E>> remove(DNode<Tree<E>> node){
	DNode<Tree<E>> next = node.getNext();
	DNode<Tree<E>> last = node.getPrevious();
	next.setPrevious(last);
	last.setNext(next);
	if(node == head) head = node.getPrevious();
	return node;
    }
    public E deleteMin(){
	//Move min's children to list
	for(Tree<E> child : min.getValue().children()){
	    insert(child);
	}
	//Remove min
	DNode<Tree<E>> m = min;
	remove(min);
	///Update min
	DNode<Tree<E>> node = head.getNext();
	if(compareNodes(head, min) < 0) min = head;
	while(node != head){
	    if(compareNodes(node, min) < 0) min = node;
	    node = node.getNext();
	}
	//Consolidate trees
	HashMap<Integer, DNode<Tree<E>>> ranks = new HashMap<Integer, DNode<Tree<E>>>();
	node = head.getNext();
	ranks.put(head.getValue().treeRank(), head);
	while(node != head){
	    DNode<Tree<E>> nextNode = node.getNext();
	    DNode<Tree<E>> compNode = ranks.get(node.getValue().treeRank());
	    System.out.println("\n\n\n\n");
	    System.out.println("head, node, next : " + head + " " + node + " " + nextNode);
	    System.out.println(ranks);
	    print();
			
			
	    if(compNode != null && compNode != node){
		ranks.remove(compNode.getValue().treeRank());
		DNode<Tree<E>> newNode = merge(compNode, node);
		if(newNode == compNode){
		    remove(node);
		}else{
		    remove(compNode);
		}
		if(compNode == head || node == head) head = newNode;
		//node = nextNode;//newNode;
		if(ranks.get(newNode.getValue().treeRank()) == null){
		    ranks.put(newNode.getValue().treeRank(),newNode);
		    node = nextNode;
		}else{
		    //node = newNode;
		}
	    }else{
		ranks.put(node.getValue().treeRank(), node);
		node = nextNode;
	    }
	    print();
	    System.out.println("head, node, next : " + head + " " + node + " " + nextNode);

			
			
	}
	return m.getValue().root();
    }
    public int compareNodes(DNode<Tree<E>> a, DNode<Tree<E>> b){
	return comp.compare(a.getValue().root(), b.getValue().root());
    }
    public void decreaseKey(Tree<E> tree, E val){
	if(tree.parent() == null){
	    tree.setRoot(val);
	    DNode<Tree<E>> node = head.getNext();
	    if(compareNodes(head, min) < 0) min = head;
	    while(node != head){
		if(compareNodes(node, min) < 0) min = node;
		node = node.getNext();
	    }
	}else{
	    tree.setRoot(val);
	    if(comp.compare(val, tree.parent.root()) < 0){
		cut(tree);
		
	    }
	}
    }
    public void cut(Tree<E> tree){
	Tree<E> parent = tree.parent();
	parent.children.remove(tree);
	tree.unmark();
	insert(tree);
	tree.setParent(null);
	if(!parent.marked() && parent.parent() != null){
	    parent.mark();
	}else{
	    cut(parent);
	}
    }
    public void print(){
	System.out.println("--------------");
	DNode<Tree<E>> node = head.getNext();
	System.out.println(head);
	while(node != head){
	    System.out.println(node);
	    node = node.getNext();
	}
	System.out.println("--------------");
    }
    public static void main(String[] args){
	FibonacciHeap<Integer> fib = new FibonacciHeap<Integer>();
	for(int i = 0; i < 10; i ++){
	    fib.add(i);
	}
	fib.print();
	//System.out.println(fib.min());
	System.out.println(fib.deleteMin());
	fib.print();
    }
}
