public class NodeDeque<E> implements Deque<E>{
    private static class Node<E>{
	private final E VALUE;
	private Node<E> NEXT = null;
	private Node<E> LAST = null;
	public Node(E value, Node<E> last, Node<E> next){
	    VALUE = value;
	    NEXT = next;
	    LAST = last;
	}
	public Node(){
	    VALUE = null;
	}
	public boolean isDummy(){return (VALUE == null);}
	public E getValue(){return VALUE;}
	public Node<E> getNext(){return NEXT;}
	public Node<E> getLast(){return LAST;}
	public void setNext(Node<E> n){
	    NEXT = n; 
	}
	public void setLast(Node<E> n){
	    LAST = n;
	}
	public String toString(){
	    return VALUE.toString();
	}
    }
    private final Node<E> head = new Node<E>();
    private final Node<E> tail = new Node<E>();
    private int size = 0;
    public NodeDeque(){
	head.setNext(tail);
	tail.setLast(head);
    }
    public int size(){
	return size;
    }
    public boolean isEmpty(){
	return (size == 0);
    }
    public E getFirst() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	return head.getNext().getValue();
    }
    public E getLast() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	return tail.getLast().getValue();
    }
    public void addFirst(E val){
	head.setNext(new Node<E>(val, head, head.getNext()));
	head.getNext().getNext().setLast(head.getNext());
	size++;
    }
    public void addLast(E val){
	tail.setLast(new Node<E>(val, tail.getLast(), tail));
	tail.getLast().getLast().setNext(tail.getLast());
	size++;
    }
    public E removeFirst() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	E val = head.getNext().getValue();
	head.setNext(head.getNext().getNext());
	size--;
	return val;
    }
    public E removeLast() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	E val = tail.getLast().getValue();
	tail.setLast(tail.getLast().getLast());
	size--;
	return val;	
    }
    public E get(int index) throws IndexOutOfBoundsException{
	if(index >= size || index < 0) throw new IndexOutOfBoundsException();
	Node<E> n = head;
	for(int i = 0; i < index + 1; i++){
	    n = n.getNext();
	}
	return n.getValue();
    }
    public String toString(){
	String s = "[ ";
	Node<E> n = head;
	for(int i = 0; i < size; i++){
	    n = n.getNext();
	    if(n!= null && !n.isDummy()) s += n + ", ";
	}
	s += "]";
	return s.replace(", ]", " ]");
    }
    public static void main(String[] args){
	NodeDeque<String> d = new NodeDeque<String>();
	System.out.println(d + " " + d.size());
	//
	d.addFirst("A");
	System.out.println(d + " " + d.size());
	// A
	d.addLast("B");
	System.out.println(d + " " + d.size());
	// A B
	d.addFirst("C");
	System.out.println(d + " " + d.size());
	// C A B
	d.addLast("D");
	System.out.println(d + " " + d.size());
	// C A B D
	System.out.println(d.get(2));
	// B
	d.addFirst("E");
	System.out.println(d + " " + d.size());
	// E C A B D
	d.removeFirst();
	System.out.println(d + " " + d.size());
	// C A B D
	d.removeFirst();
	System.out.println(d + " " + d.size());
	// A B D
	d.removeLast();
	System.out.println(d + " " + d.size());
    }
    
}
