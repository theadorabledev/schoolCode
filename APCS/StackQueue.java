import java.util.NoSuchElementException;
public class StackQueue<E> implements MyQueue<E>{
    private NodeStack<E> stackA = new NodeStack<E>();
    private NodeStack<E> stackB = new NodeStack<E>();
    private int size = 0;
    public StackQueue(){

    }
    public int size(){
	return size;
    }
    public boolean isEmpty(){
	return (size == 0);
    }
    public boolean offer(E val){//O(1)
	stackA.push(val);
	size++;
	return true;
    }
    public boolean add(E val) throws IllegalStateException{
	return offer(val);
    }
    public E peek(){
	if(isEmpty()) return null;
	while(stackA.size() > 1){
	    stackB.push(stackA.pop());
	}
	E val = stackA.peek();
	while(!stackB.empty()){
	    stackA.push(stackB.pop());
	}
	return val;
    }
    public E poll(){//O(n)
	if(isEmpty()) return null;
	while(stackA.size() > 1){
	    stackB.push(stackA.pop());
	}
	E val = stackA.pop();
	while(!stackB.empty()){
	    stackA.push(stackB.pop());
	}
	size--;
	return val;
    }
    public E remove() throws NoSuchElementException{
	if(isEmpty()) throw new NoSuchElementException();
	return poll();
    }
    public String toString(){
	String s = "[ ";
	while(!stackA.empty()){
	    stackB.push(stackA.pop());
	}
	while(!stackB.empty()){
	    E val = stackB.pop();
	    s += val + ", ";
	    stackA.push(val);
	}
	s += "]";
	return s.replace(", ]", " ]");
    }
    public static void main(String[] args){
	StackQueue<String> q = new StackQueue<String>();
	q.offer("A");
	System.out.println(q + " " + q.size());
	q.offer("B");
	System.out.println(q + " " + q.size());
	q.offer("C");
	System.out.println(q + " " + q.size());
	q.offer("D");
	System.out.println(q + " " + q.size());
	q.offer("E");
	System.out.println(q + " " + q.size());
	q.offer("F");
	System.out.println(q + " " + q.size());
	System.out.println(q.poll());
	System.out.println(q + " " + q.size());
	q.offer("G");
	System.out.println(q + " " + q.size());
    }
    
}
