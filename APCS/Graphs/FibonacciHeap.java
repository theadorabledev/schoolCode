import java.util.*;
public class FibonacciHeap<E>{
    private DNode<Tree<E>> head;
    private DNode<Tree<E>> min;
    private Comparator<E> comp;
    private int size = 0;
    public FibonacciHeap(E val, Comparator<E> comp){
	head = new DNode<Tree<E>>(new Tree(val), null, null);
	head.setNext(head);
	head.setPrevious(head);
	min = head;
	if(comp != null){
	    this.comp = comp;
	}else{
	    class MyComparator implements Comparator<E> {
		public int compare(E a, E b) {
		    return a.compareTo(b);
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
	DNode<Tree<E>> node = new DNode<Tree<E>>(t, head, head.getNext());
	head.getNext().setPrevious(node);
	head.setNext(node);
	if(t.root() < min()){
	    min  = node;
	}
    }
    public void insert(E val){
	Tree<E> t = new Tree<E>(val);
	insert(t);
    }
    public void merge(DNode<Tree<E>> a, DNode<Tree<E>> b){
	if(a.getValue().root() > b.getValue().root()){
	    b.addChild(remove(a).getValue());
	}
	else{
	    a.addChild(remove(b).getValue());
	}
    }
    public DNode<Tree<E>> remove(DNode<Tree<E>> node){
	DNode<Tree<E>> next = node.getNext();
	DNode<Tree<E>> last = node.getPrevious();
	next.setPrevious(last);
	last.setNext(next);
    }
    public void DeleteMin(){
	for(Tree<E> child : min.getValue().children()){
	    insert(child);
	}
	remove(min);
    }
    public void print(){
	DNode<Tree<E>> node = head.getNext();
	System.out.println(head);
	while(node != head){
	    System.out.println(node);
	    node = node.getNext();
	}
    }
    public static void main(String[] args){
	FibonacciHeap<Integer> fib = new FibonacciHeap<Integer>();
    }
}
