import java.util.*;
public class Tree<E>{
    private E root;
    private ArrayList<Tree<E>> children = new ArrayList<Tree<E>>();
    private int treeRank = 1;
    private Tree<E> parent;
    private boolean marked = false;
    private Tree<E> previous, next;
    public Tree(E root){
	this(root, null, null);
    }
    public Tree(E root, Tree<E> next, Tree<E> previous){
	this.root = root;
	parent = null;
	this.next = next;
	this.previous = previous;
    }
    public void addChild(E child){
	addChild(new Tree<E>(child));
    }
    public void addChild(Tree<E> child){
	children.add(child);
	treeRank += child.treeRank();
	child.setParent(this);
    }
    public int rank(){
	return children.size();
    }
    public int treeRank(){
	return treeRank;
    }
    public E root(){
	return root;
    }
    public void setRoot(E val){
	root = val;
    }
    public Tree<E> parent(){
	return parent;
    }
    public void setParent(Tree<E> t){
	parent = t;
    }
    public ArrayList<Tree<E>> children(){
	return children;
    }
    public boolean marked(){
	return marked;
    }
    public void mark(){
	marked = true;
    }
    public void unmark(){
	marked = false;
    }
    public Tree<E> getNext(){
	return next;
    }
    public Tree<E> getPrevious(){
	return previous;
    }
    public void setNext(Tree<E> t){
	next = t;
    }
    public void setPrevious(Tree<E> t){
	previous = t;
    }
    public String toString(){
	String s = root + " : [ ";
	for(Tree<E> child : children){
	    s += child + ", ";
	}
	s += "]";
	return s.replace(", ]", " ]").replace(" : [ ]", "");
    }
	
}
