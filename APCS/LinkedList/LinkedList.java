

public class LinkedList<E>{
    
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

    // *************************** Exercise *******************************

    // returns true if v is not the header node
    public boolean hasPrevious(DNode<E> v){
	return(v != header);
    }
    // *************************** Exercise *******************************
    // returns true if v is not the trailer node
    public boolean hasNext(DNode<E> v){
	return(v != trailer);
    }
    

    // *************************** Exercise *******************************
    public DNode<E> getFirst() throws IllegalStateException {
	if (isEmpty()) throw new IllegalStateException("empty list");
	return header.getNext();
    }
    // *************************** Exercise *******************************
    public DNode<E> getLast() throws IllegalStateException{
	if (isEmpty()) throw new IllegalStateException("empty list");
	return trailer.getPrevious();
    }

    // *************************** Exercise *******************************
    // returns the node that comes directly before the current node.
    public DNode<E> getPrevious(DNode <E> current) throws IllegalArgumentException{
	if(!hasPrevious(current)) throw new IllegalArgumentException();
	return current.getPrevious();
    }

    // *************************** Exercise *******************************
    // returns the node that comes directly after the current node.
    public DNode<E> getNext(DNode<E> current) throws IllegalArgumentException{
	if(!hasNext(current)) throw new IllegalArgumentException();
	return current.getNext();

    }

    // *************************** Exercise *******************************
    //*******************************************************************
    // inserts node a before node b. 
    // An exception is thrown if b is the header
    public void addBefore(DNode<E> b, DNode<E> a){
	DNode<E> prev = getPrevious(b);
	prev.setNext(a);
	a.setPrevious(prev);
	a.setNext(b);
	b.setPrevious(a);
	
    }

    // *************************** Exercise *******************************
    public void addLast(DNode<E> node){
	addBefore(trailer, node);
    }

 
    // *************************** Exercise *******************************
    //*********************************************************************
    // inserts node b after node a
    // throw exception if b is the trailer node
    public void addAfter(DNode<E> a, DNode<E> b){
	DNode<E> next = getNext(a);
	next.setPrevious(b);
	b.setNext(next);
	a.setNext(b);
	b.setPrevious(a);
    }

    // *************************** Exercise *******************************
    public void addFirst(DNode<E> current){
	addAfter(header, current);
    }
    // *************************** Exercise *******************************
    public void addFirst(E value){
	addAfter(header, new DNode<E>(value, null, null));
    }

    // *************************** Exercise *******************************
    //*******************************************************************
    //precondition: v != null and is a node in the list
    //postconditon: removes the node v refers to.
    //              Throws exception if v is header or trailer.
    public void remove(DNode<E> v){
	DNode<E> prev = getPrevious(v);
	DNode<E> next = getNext(v);
	prev.setNext(next);
	next.setPrevious(prev);
    }

    //*******************************************************************   
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

	
    }

}
