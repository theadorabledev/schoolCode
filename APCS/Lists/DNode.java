public class DNode<E>{
    private E value;
    private DNode<E> previous, next;

    public DNode(E value, DNode<E> previous, DNode<E> next){
	this.value = value;
	this.previous = previous;
	this.next = next;

    }

    public E getValue(){
	return value;
    }

    public DNode<E> getPrevious(){
	return previous;
    }
    
    public DNode<E> getNext(){
	return next;
    }
    
    public E setValue(E newValue){
	E ans = value;
	value = newValue;
	return ans;
    }

    public DNode<E> setPrevious(DNode<E> newPrevious){
	DNode<E> ans = getPrevious();
	previous = newPrevious;
	return ans;
    }

    public DNode<E> setNext(DNode<E> newNext){
	DNode<E> ans = getNext();
	next = newNext;
	return ans;
    }



    public String toString(){
	return value  + "";
    }


    public static void main(String [] args){
	DNode<String> a = new DNode<String>("Amy", null, null);
	DNode<String> b = new DNode<String>("Bill", a, null);
	DNode<String> c = new DNode<String>("Carol", b, null);
	a.setNext(b);
	b.setNext(c);
	
        DNode<String> current = a;
	while (current != null){
	    System.out.println(current);
	    current = current.getNext();
	}
	current = c;
	while( current != null){
	    System.out.println(current);
	    current = current.getPrevious();

	}


    }


}
