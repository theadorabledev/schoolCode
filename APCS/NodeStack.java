import java.util.EmptyStackException;
public class NodeStack<E> implements MyStack<E>{
    private static class Node<E>{
	private final E VALUE;
	private final Node<E> NEXT;
	public Node(E value, Node<E> next){
	    VALUE = value;
	    NEXT = next;
	}
	public E getValue(){return VALUE;}
	public Node<E> getNext(){return NEXT;}
	public String toString(){
	    return VALUE.toString();
	}
    }
    private Node<E> top;
    private int size;
    public NodeStack(){
	top = null;
	size = 0;
    }
    public boolean empty(){return (size == 0);}
    public int size(){return size;}
    public void push(E val){
	top = new Node<E>(val, top);
	size++;
    }
    public E peek() throws EmptyStackException{
	if(empty()) throw new EmptyStackException();
	return top.getValue();
    }
    public E pop() throws EmptyStackException{
	if(empty()) throw new EmptyStackException();
	E val = top.getValue();
	top = top.getNext();
	size--;
	return val;
    }
    public void print(){
	print(top);
	System.out.println();
    }
    public void print(Node<E> n){
	System.out.print(n.getValue() + " ");
	if(n.getNext() != null) print(n.getNext());
	
    }
    public static void main(String [] args){
	NodeStack<Integer> s = new NodeStack<Integer>();
	s.push(1);
	s.push(2);
	s.push(3);
	s.print();
	System.out.println(s.pop());
	s.print();
    }
    
}
