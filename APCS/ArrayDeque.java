public class ArrayDeque<E> implements Deque<E>{
    private int size = 0;
    private E[] q;
    private final int CAPACITY;
    private int head = 0;
    private int tail = 0;
    @SuppressWarnings("unchecked")
    public ArrayDeque(int size){
	CAPACITY = size;
	q = (E[]) new Object[size + 1];
    }
    public int size(){
	return size;
    }
    public boolean isEmpty(){
	return (size == 0);
    }
    public E getFirst() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	return q[head];
    }
    public E getLast() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	return q[tail];
    }
    public void addFirst(E val) throws FullDequeException{
	if(size == CAPACITY) throw new FullDequeException();
	
	head--;
	if(head == -1) head = CAPACITY;
	q[head] = val;
	size++;
    }
    public void addLast(E val) throws FullDequeException{
	if(size == CAPACITY) throw new FullDequeException();
	q[tail] = val;
	tail++;;
	tail %= CAPACITY + 1;
	//q[tail] = val;
	size++;
    }
    public E removeFirst() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	E val = q[head];
	q[head] = null;
	head++;
	head %= CAPACITY + 1;
	size--;
	return val;
    }
    public E removeLast() throws EmptyDequeException{
	if(isEmpty()) throw new EmptyDequeException();
	E val = q[tail];
	q[tail] = null;
	tail--;
	if(tail == -1) tail = 0;
	size--;
	return val;
    }

    public String toString(){
	//for(Object o : q){
	//System.out.print(o + " ");
	//}
	//System.out.println();
	String s = "[ ";
	for(int i = 0; i < size; i++){
	    s += q[(i + head) % (CAPACITY + 1)] + ", ";
	}
	s += "]";
	return s.replace(", ]", " ]");
    }
    public static void main(String[] args){
	ArrayDeque<String> d = new ArrayDeque<String>(5);
	System.out.println(d + " " + d.size());
	d.addFirst("A");
	System.out.println(d + " " + d.size());
	d.addLast("B");
	System.out.println(d + " " + d.size());
	d.addFirst("C");
	System.out.println(d + " " + d.size());
	d.addLast("D");
	System.out.println(d + " " + d.size());
	d.addFirst("E");
	System.out.println(d + " " + d.size());
	//E C A B D
	d.removeFirst();
	System.out.println(d + " " + d.size());
	// C A B D
	d.removeFirst();
	System.out.println(d + " " + d.size());
	// A B D
	d.removeLast();
	System.out.println(d + " " + d.size());
	// A B
    }
}
