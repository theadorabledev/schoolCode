//import java.util.RuntimeException;
import java.util.EmptyStackException;
public class ArrayStack<E> implements MyStack<E>{
    private E[] stack;
    private int pos = 0;
    public ArrayStack(int size){
        stack = (E[]) new Object[size];
    }
    public boolean empty(){
	return (stack[pos] == null);
    }
    public int size(){
	return stack.length;
    }
    public void push(E val) throws FullStackException{
	if(pos == stack.length) throw FullStackException("Full stack");
	stack[pos] = val;
	pos++;
    }
    public E peek() throws EmptyStackException{
	if(empty()) throw EmptyStackException("Empty.");
	return stack[pos];
    }
    public E pop() throws EmptyStackException{
	if(empty()) throw EmptyStackException("Empty.");
	E val = stack[pos];
	stack[pos] = null;
	pos--;
	return val;
	
    }
}
