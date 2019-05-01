public class HW26{

	public static class Node<E>{
		private Node<E> next;
		private final E VALUE;

		// constructor
		public Node(E value, Node<E> next){
			this.next = next;
			VALUE = value;
		}

		// accessor methods
		public Node<E> getNext(){ return next;}
		public E getValue() { return VALUE;}

		// mutator methods
		public void setNext(Node<E> x){ next = x; }

	}
	// precondition: p is either null or refers to a linkedlist of Node<Integer>
	//               objects. Each value held within a node is nonnegative.
	// postcondition: returns the maximum value.
          
	//Rewrite the max method by using recursion.
	public static int max(Node<Integer> p){
		if(p.getNext() != null) return Math.max(p.getValue(), max(p.getNext()));
		return p.getValue();	
	}
	//Write a recursive  method print(Node<Integer> p ) to print the values of the linkedlist starting at p. Print one value per line.
	public static void print(Node<Integer> p){
	    System.out.println(p.getValue());
		if(p.getNext() != null) print(p.getNext());
	}
	// Write a recursive method to print the values of a linked list in reverse order.
	public static void reversePrint(Node<Integer> p){
		if(p.getNext() != null) reversePrint(p.getNext());
		System.out.println(p.getValue());
	}
	// Write a recursive method to compute the length of a linked list.
	public static int length(Node<Integer> p){
		if(p.getNext() == null) return 1;
		return length(p.getNext()) + 1;
	}	     
	public static void main(String[] args){
		// list is a linked list with the values 1,2,3.
		Node<Integer> list = new Node<Integer>(1,new Node<Integer>(2,new Node<Integer>(3,new Node<Integer>(4, null))));
		System.out.println(max(list));
		System.out.println();
		print(list);
		System.out.println();
		reversePrint(list);
		System.out.println();
		System.out.println(length(list));
	}
}