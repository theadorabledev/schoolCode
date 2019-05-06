public class FibonacciHeap<E>{
    private DNode<Tree<E>> head;
    private DNode<Tree<E>> min;
    private int size = 0;
    public FibonacciHeap(E val){
	head = new DNode<Tree<E>>(new Tree(val), null, null);
	head.setNext(head);
	head.setPrevious(head);
	min = head;
    }
    public int size(){
	return size;
    }
    public E min(){
	return min.getValue.root();
    }
    public void insert(Tree<E> t){
	Dnode<Tree<E>> node = new Dnode<Tree<E>>(t, head, head.getNext());
	head.getNext().setPrevious(node);
	head.setNext(node);
	if(t.root() < min()){
	    min  = node;
	}
    }
    public void insert(E val){
	Tree<E> t = new Tree<E>(val);
	insert(t);
    }
    public void DeleteMin(){
	for(Tree<E> child : min.getValue().children()){
	    insert(child);
	}
	DNode<E> next = min.getNext();
	DNode<E> last = min.getPrevious();
	next.setPrevious(last);
	last.setNext(next);
    }
}
