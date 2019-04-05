public class NodeDeque<E> implements Deque<E>{
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
    public NodeDeque(){
       
    }
    public int size(){
	return size;
    }
    public boolean isEmpty(){
	return (size == 0);
    }
    public E getFirst() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	return head.getValue();
    }
    public E getLast() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	return tail.getValue();
    }
    public void addFirst(E val){
	head = new Node<E>(val, head);
    }
    public void addLast(E val){
	tail.setNext(new Node<E>(val, null));
	tail = node.getNext();
    }

    public E removeFirst() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	E val = head.getValue();
	head = head.getNext();
	return val;
}
