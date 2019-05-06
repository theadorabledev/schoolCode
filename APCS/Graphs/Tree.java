public class Tree<E>{
    private E root;
    private ArrayList<Tree<E>> children;
    public Tree(E root){
	this.root = r;
    }
    public void addChild(E child){
	children.add(new Tree<E>(child));
    }
    public void addChild(Tree<E> child){
	children.add(child);
    }
    public int rank(){
	return children.size();
    }
    public E root(){
	return root;
    }
    public ArrayList(<Tree<E>> children){
	return children;
    }
    
}
