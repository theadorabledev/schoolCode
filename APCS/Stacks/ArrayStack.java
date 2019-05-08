//import java.util.RuntimeException;
import java.util.EmptyStackException;

public class ArrayStack<E> implements MyStack<E>{
    private E[] stack;
    private int pos = -1;
    public ArrayStack(int size){
        stack = (E[]) new Object[size];
    }
    @Override
    public boolean empty(){
	return (pos == -1);
    }
    @Override
    public int size(){
	return pos + 1;
    }
    @Override
    public void push(E val) throws FullStackException{
	if(size() == stack.length) throw new FullStackException("Full stack");
	pos++;
	stack[pos] = val;
    }
    @Override
    public E peek() throws EmptyStackException{
	if(empty()) throw new EmptyStackException();
	return stack[pos];
    }
    @Override
    public E pop() throws EmptyStackException{
	if(empty()) throw new EmptyStackException();
	E val = stack[pos];
	stack[pos] = null;
	pos--;
	return val;
	
    }
    @Override
    public String toString(){
	String s = "";
	for(int i = 0; i < size(); i++){
	    s += stack[i] + ", "; 
	}
	if(s.length() > 1) s = s.substring(0, s.length() - 2);
	return s;
    }
    public void transferTo(ArrayStack<E> rhs){
	while(!empty()){
	    E p = pop();
	    try{
		rhs.push(p);
	    }catch(FullStackException e){
		push(p);
		break;
	    }
	}
    }
    public static void main(String[] args){
	ArrayStack<String> s = new ArrayStack<String>(3);
	System.out.println(s.size());
	s.push("Hello");
	System.out.println(s.peek());
	//System.out.println(s.pop());
	//System.out.println(s.peek());
	s.push(" - ");
	s.push("World.");
	System.out.println(s);
	ArrayStack<String> s2 = new ArrayStack<String>(4);
	s.transferTo(s2);
	System.out.println(s);
	System.out.println(s2);
    }

    //Q2 Transfer : A -> B , B -> C, C -> A, B -> C, A -> B, B - > C
    // A:       100      95       95      97      97      92       92         
    // B:       0        5        2       2       0       5        4
    // C:       0        0        3       0       2       2        3
}
