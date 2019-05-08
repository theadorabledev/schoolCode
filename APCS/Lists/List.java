import java.util.Iterator;
//import java.util.Iterable;
public interface List<E> extends Iterable<E>{

    public int size();
    public boolean isEmpty();
    public E get(int i) throws IndexOutOfBoundsException;
    public E set(int i, E value)throws IndexOutOfBoundsException;
    public boolean add(E value);
    public void add(int i, E value)throws IndexOutOfBoundsException;
    public E remove (int i)throws IndexOutOfBoundsException;
    Iterator<E> iterator();
}
