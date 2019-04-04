public class ArrayDequeue<E> implements Dequeue<E>{
    private int size = 0;
    private E[] q;
    private final int CAPACITY;
    private int head = 0;
    private int tail = 0;
    public ArrayDequeue(int size){
	CAPACITY = size;
	queue = (E[]) new Object[size + 1];
    }
    public int size(){
	return size;
    }
    public boolean isEmpty(){
	return (size == 0);
    }
    public E getFirst() throws EmptyDequeueException{
	if(isEmpty()) throw new EmptyDequeueException();
	return q[head];
    }
    public E getFirst() throws EmptyDequeueException{
	if(isEmpty()) throw new EmptyDequeueException();
	return q[tail];
    }
    public void addFirst(E val) throws IllegalStateException{
	if(size == CAPACITY) throw new IllegalStateException();
	head--;
	if(head == -1) head = CAPACITY;
	q[head] = val;
	size++;
    }
    public void addLast(E val) throws IllegalStateException{
	if(size == CAPACITY) throw new IllegalStateException();
	tail++;;
	tail %= CAPACITY;
	q[tail] = val;
	size++;
    }
    public E removeFirst() throws EmptyDequeueException(){}
    
}
