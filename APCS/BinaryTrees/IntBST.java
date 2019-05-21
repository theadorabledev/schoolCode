public class IntBST{

    // instance variables
    private TreeNode<Integer> root;
    private int size; 

    private static class TreeNode<Integer> {
	private Integer value;
	private TreeNode<Integer> left, right;

	public TreeNode(Integer value, TreeNode<Integer> left,
			TreeNode<Integer> right){
	    this.value = value;
	    this.left = left;
	    this.right = right;
	}


	// accessors
	public Integer getValue() { return value;}
	public TreeNode<Integer> getLeft() {return left;}
	public TreeNode<Integer> getRight() {return right;}
	public boolean isLeaf(){
	    return (left == null && right == null);
	}
	// mutators
	public void setLeft(TreeNode<Integer> n) { left = n;}
	public void setRight(TreeNode<Integer> n) { right = n;}
	public void setValue(Integer x){ value = x;}
	

    }

    public IntBST(){
	root = null;
	size = 0;
    }

    private TreeNode<Integer> maxNode(TreeNode<Integer> rt){
	if(rt == null) return null;
	while(rt.getRight() != null){
	    rt = rt.getRight();
	}
	return rt;
    }
    private TreeNode<Integer> minNode(TreeNode<Integer> rt){
	if(rt == null) return null;
	while(rt.getLeft() != null){
	    rt = rt.getLeft();
	}
	return rt;
    }
    public TreeNode<Integer> find(Integer x){
	TreeNode<Integer> rt = root;
	while(rt != null){
	    if(rt.getValue() == x)return rt;
	    else if(rt.getValue() <= rt.getValue()) rt = rt.getRight();
	    else rt = rt.getLeft();
	}
	return null;
    }
    public TreeNode<Integer> findParent(TreeNode<Integer> child){
	TreeNode<Integer> rt = root;
	while(rt != null){
	    if(rt.getLeft() == child || rt.getRight() == child )return rt;
	    else if(rt.getValue() <= rt.getValue()) rt = rt.getRight();
	    else rt = rt.getLeft();
	}
	return null;
    }
    public boolean remove(Integer x){
	TreeNode<Integer> root = find(x);
	if(root == null) return false;
	TreeNode<Integer> parent = findParent(root);
       
	if(root.isLeaf()){
	    if(parent.getValue() >= root.getValue()) parent.setLeft(null);
	    else parent.setRight(null);
	}else{
	    if(root.getRight() == null){
		TreeNode<Integer> max = maxNode(root.getLeft());
		root.setValue(max.getValue());
		remove(max.getValue());
	    }else{
		TreeNode<Integer> min = minNode(root.getRight());
		root.setValue(min.getValue());
		remove(min.getValue());
	    }
	}
	return true;
    }
    
    private TreeNode<Integer> add(Integer x, TreeNode<Integer> rt){
	if (rt == null)
	    rt = new TreeNode<Integer>(x,null,null);
	else if (x <= rt.getValue())
	    rt.setLeft(add(x,rt.getLeft()));
	else 
	    rt.setRight(add(x,rt.getRight()));
	return rt;
    }
    public void add(Integer x){
	size++;
	TreeNode<Integer> leaf = new TreeNode<Integer>(x, null, null);
	if(root == null){
	    root = leaf;
	    return;
	}
	TreeNode<Integer> trailer = root;
	TreeNode<Integer> node = root;
	while(true){
	    if(node == null){
		if(trailer.getValue() > x) trailer.setLeft(leaf);
		else trailer.setRight(leaf);
		return;
	    }
	    trailer = node;
	    if(node.getValue() > x){
		node = node.getLeft();
	    }else{
		node = node.getRight();
	    }
	}
    }
    public void printSideways(){
	printSideways(root, 0);
    }
    private void printSideways(TreeNode<Integer> node, int depth){
	if(node == null) return;
	String s = "";
	for(int i = 0; i < depth; i++)s+= "---";
	printSideways(node.getRight(), depth + 1);
	System.out.println(s + node.getValue());
	printSideways(node.getLeft(), depth + 1);

    }
    public void inorder(){
	inorder(root);
	System.out.println();
    }
    private void inorder(TreeNode<Integer> rt){
	if (rt != null){
	    inorder(rt.getLeft());
	    System.out.print(rt.getValue() + " ");
	    inorder(rt.getRight());

	}
    }
	public void preorder(){
	preorder(root);
	System.out.println();
    }
    private void preorder(TreeNode<Integer> rt){
	if (rt != null){
	    System.out.print(rt.getValue() + " ");
		preorder(rt.getLeft());
	    preorder(rt.getRight());

	}
    }
	public void postorder(){
	postorder(root);
	System.out.println();
    }
    private void postorder(TreeNode<Integer> rt){
	if (rt != null){
	    postorder(rt.getLeft());
	    postorder(rt.getRight());
	    System.out.print(rt.getValue() + " ");

	}
    }


    public static void main(String [] args){
	IntBST bst = new IntBST();
	bst.add(5);
	bst.add(7);
	bst.add(4);
	bst.add(2);
	bst.add(6);
	bst.add(3);
	bst.add(8);
	bst.add(1);
	bst.add(9);
	bst.inorder();
	bst.preorder();
	bst.postorder();
	bst.printSideways();
    }


}
