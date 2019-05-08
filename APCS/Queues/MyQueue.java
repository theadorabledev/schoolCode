import java.util.NoSuchElementException;
public interface MyQueue<E>{
    public int size();
    public boolean isEmpty();
    public boolean add(E val) throws IllegalStateException;
    public boolean offer(E val); //false if full
    public E peek(); // null if empty
    public E remove() throws NoSuchElementException;
    public E poll();//null if empty
}
