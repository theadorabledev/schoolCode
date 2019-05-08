import java.util.Iterator;
public class LinkedList<E> implements List<E>{
	
	private DNode<E> header, trailer;  // dummy (sentinel) nodes
	private int size;

	// constructor
	// instantiates an empty LinkedList object
	public LinkedList(){
		header = new DNode<E>(null, null, null);
		trailer = new DNode<E>(null,header, null);
		header.setNext(trailer);
		size  = 0;
	}

	//********  accessor methods *******
	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	// returns true if v is not the header node
	public boolean hasPrevious(DNode<E> v){
		return(v != header);
	}
	// returns true if v is not the trailer node
	public boolean hasNext(DNode<E> v){
		return(v != trailer);
	}
	public DNode<E> getFirst() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("empty list");
		return header.getNext();
	}
	public DNode<E> getLast() throws IllegalStateException{
		if (isEmpty()) throw new IllegalStateException("empty list");
		return trailer.getPrevious();
	}
	// returns the node that comes directly before the current node.
	public DNode<E> getPrevious(DNode <E> current) throws IllegalArgumentException{
		if(!hasPrevious(current)) throw new IllegalArgumentException();
		return current.getPrevious();
	}
	// returns the node that comes directly after the current node.
	public DNode<E> getNext(DNode<E> current) throws IllegalArgumentException{
		if(!hasNext(current)) throw new IllegalArgumentException();
		return current.getNext();

	}
	// inserts node a before node b. 
	// An exception is thrown if b is the header
	public void addBefore(DNode<E> b, DNode<E> a){
		DNode<E> prev = getPrevious(b);
		prev.setNext(a);
		a.setPrevious(prev);
		a.setNext(b);
		b.setPrevious(a);
		size++;
	}
	// inserts node b after node a
	// throw exception if b is the trailer node
	public void addAfter(DNode<E> a, DNode<E> b){
		DNode<E> next = getNext(a);
		next.setPrevious(b);
		b.setNext(next);
		a.setNext(b);
		b.setPrevious(a);
		size++;
	}

	public void addFirst(DNode<E> current){
		addAfter(header, current);
	}
	public void addFirst(E value){
		addAfter(header, new DNode<E>(value, null, null));
	}
	public void addLast(DNode<E> node){
		addBefore(trailer, node);
	}
	public void addLast(E value){
		addBefore(trailer, new DNode<E>(value, null, null));
	}
	public E removeFirst(){
		DNode<E> first = getFirst();
		remove(first);
		size--;
		return first.getValue();
	}
	public E removeLast(){
		DNode<E> last = getLast();
		remove(last);
		size--;
		return last.getValue();
	}
	public DNode<E> getNode(int i){
		//if(i < 0 || i >= size) throw new IndexOutOfBoundsException();
		DNode<E> node = getFirst();
		for(int k = 0; k < i; k++){
			node = node.getNext();
		}
		return node;
	}
	public void swap(DNode<E> x, DNode<E> y){
		if(!hasNext(x) || !hasNext(y) || !hasPrevious(x) || !hasPrevious(y) || x == y) return;
		DNode<E> xLast = x.getPrevious();
		DNode<E> yNext = y.getNext();
		if(xLast == y){
			swap(y, x);
			return;
		}
		remove(x);
		remove(y);
		addAfter(xLast, y);
		addBefore(yNext, x);
		
	}
	//precondition: v != null and is a node in the list
	//postconditon: removes the node v refers to.
	//              Throws exception if v is header or trailer.
	public void remove(DNode<E> v){
		DNode<E> prev = getPrevious(v);
		DNode<E> next = getNext(v);
		prev.setNext(next);
		next.setPrevious(prev);
		size--;
	}
	public E remove(int i){
		DNode<E> n = getNode(i);
		remove(n);
		return n.getValue();
	}
	public void addAll(LinkedList<E> L){
		if(this == L) return;
		DNode<E> first = L.getFirst();
		DNode<E> last = L.getLast();
		first.setPrevious(getLast());
		getLast().setNext(first);
		trailer.setPrevious(last);
		last.setNext(trailer);
	}
	public void add(int i, E value) throws IndexOutOfBoundsException{
		if(i < 0 || i >= size) throw new IndexOutOfBoundsException();
		addBefore(getNode(i), new DNode<E>(value, null, null));
	}
	public boolean add(E value){
		addLast(value);
		return true;
	}
    public E set(int i, E value)throws IndexOutOfBoundsException{
		DNode<E> n = getNode(i);
		E v = n.getValue();
		n.setValue(value);
		return v;	
	}
	public E get(int i){
		return getNode(i).getValue();
	}
	public String toString(){
		String ans = "";
		DNode current = header.getNext();
		while (current != trailer){
			ans += current.getValue() + ", ";
			current = current.getNext();
		}
		int len = ans.length();
		if (len > 0) ans = ans.substring(0,len - 2);
		ans = "[ " + ans + " ]";
		return ans;
	}

	public static void main(String [] args){
		LinkedList<Integer> L = new LinkedList<Integer>();

		System.out.println("L : " + L);
		for (int i = 0; i < 10; i++){
			L.addFirst(i);
			System.out.println("add " + i + " : " + L);
		}
		for(int i = 0; i < 10; i ++){
			System.out.println("L[" + i + "] = " + L.getNode(i));
		}
		for(int i = 0; i < 10; i++){
			L.swap(L.getNode(i), L.getNode(9 - i));
		}
		System.out.println(L);
		L.removeFirst();
		L.removeLast();
		System.out.println(L);
		
	}
	public Iterator iterator(){
		return new ListIterator<E>(this);
	}
	
	private class ListIterator<E> implements Iterator<E>{
		private LinkedList<E> myList;
		private DNode<E> current;
		
		public ListIterator(LinkedList<E> list){
			myList = list;
			current = list.header;
		}

		public boolean hasNext(){
			return myList.hasNext(current.getNext());
		}

		// precondition: current is positioned at the node that precedes the 
		//               item to be returned.
		// postcondition: advances current then returns the item
		public E next(){
			current = myList.getNext(current);
			return current.getValue();
		}


	}

}
