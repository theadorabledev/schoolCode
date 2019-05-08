import java.util.NoSuchElementException;

public class NodeQueue<E> implements MyQueue<E>{
    private static class Node<E>{
	private final E VALUE;
	private Node<E> NEXT;
	public Node(E value, Node<E> next){
	    VALUE = value;
	    NEXT = next;
	}
	public E getValue(){return VALUE;}
	public Node<E> getNext(){return NEXT;}
	public void setNext(Node<E> n){
	    NEXT = n; 
	}
	public String toString(){
	    return VALUE.toString();
	}
    }
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    public NodeQueue(){
       
    }
    public int size(){
	return size;
    }
    public boolean isEmpty(){
	return (size == 0);
    }
    public boolean offer(E val){
	if(head == null){
	    head = tail = new Node<E>(val, null);
	}else{
	    tail.setNext(new Node<E>(val, null));
	    tail = tail.getNext();
	}
	size++;
	return true;
    }
    public boolean add(E val) throws IllegalStateException{
	return offer(val);
    }
    public E peek(){
	return (head == null)?null:head.getValue();
    }
    public E poll(){
	if(head == null) return null;
	E val = head.getValue();
	head = head.getNext();
	size--;
	return val;
    }
    public E remove() throws NoSuchElementException{
	if(head == null) throw new NoSuchElementException();
	return poll();
    }
    public String toString(){
	String s = " [ ";
	Node<E> n = head;
	while(n != null){
	    s += n + ", ";
	    n = n.getNext();
	}
	s += "]";
	return s.replace(", ]", " ]");
    }
    public static void main(String[] args){
	NodeQueue<String> q = new NodeQueue<String>();
	System.out.println(q + " " + q.size());
	q.offer("A");
	System.out.println(q + " " + q.size());
	q.offer("B");
	System.out.println(q + " " + q.size());
	q.offer("C");
	System.out.println(q + " " + q.size());
	q.offer("D");
	System.out.println(q + " " + q.size());
	System.out.println(q.peek());
	System.out.println(q + " " + q.size());
	System.out.println(q.poll());
	System.out.println(q + " " + q.size());
	System.out.println(q.poll());
	System.out.println(q + " " + q.size());
	System.out.println(q.poll());
	System.out.println(q + " " + q.size());
	System.out.println(q.remove());
	System.out.println(q + " " + q.size());
	//System.out.println(q.remove());
	//System.out.println(q + " " + q.size());
    }
    

}
