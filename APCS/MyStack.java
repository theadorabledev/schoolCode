import java.util.EmptyStackException;
public interface MyStack<E>{
    public boolean empty();
    public int size();
    public void push(E val);
    public E peek() throws EmptyStackException;
    public E pop() throws EmptyStackException;
}
