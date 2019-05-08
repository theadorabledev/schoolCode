import java.util.*;
public class Tree<E>{
	private E root;
	private ArrayList<Tree<E>> children = new ArrayList<Tree<E>>();
	private int treeRank = 1;
	public Tree(E root){
		this.root = root;
	}
	public void addChild(E child){
		addChild(new Tree<E>(child));
	}
	public void addChild(Tree<E> child){
		children.add(child);
		treeRank += child.treeRank();
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
	public ArrayList<Tree<E>> children(){
		return children;
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
