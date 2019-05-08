import java.util.Iterator;

public interface List<E>{

    public int size();
    public boolean isEmpty();
    public E get(int i) throws IndexOutOfBoundsException;
    public E set(int i, E value)throws IndexOutOfBoundsException;
    public boolean add(E value);
    public void add(int i, E value)throws IndexOutOfBoundsException;
    public E remove (int i)throws IndexOutOfBoundsException;
    Iterator<E> iterator();
}