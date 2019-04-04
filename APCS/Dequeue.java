public interface Deque<E>{    
    
    public int size();
    public boolean isEmpty();
    
    
    public E getFirst() throws EmptyDequeException;
    public E getLast() throws EmptyDequeException;
    
    public void addFirst(E val);
    public void addLast(E val);
    
    
    public E removeFirst() throws EmptyDequeException;
    public E removeLast()  throws EmptyDequeException;
    
    
}
