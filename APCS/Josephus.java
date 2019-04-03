import java.util.*;
public class Josephus{
    public static<E> MyQueue<E> buildQueue(ArrayList<E> players){
	MyQueue<E> ans = new ArrayQueue<E>(players.size());
	for (E player : players)
	    ans.add(player);
	return ans;
    }
    
    // Pre: k > 0
    // Post: Uses a round robin scheduler to determine 
    //       the winner.
    public static<E> E josephus(MyQueue<E> q, int k){
	while(q.size() > 1){
	    for(int i = 0; i < k; i++){
		q.offer(q.remove());
	    }
	}
	return q.remove();
    }
    public static<E> NodeQueue<ArrayList<E>> subsets(ArrayList<E> arr){
	NodeQueue<ArrayList<E>> nQ = new NodeQueue<ArrayList<E>>();
	NodeQueue<ArrayList<E>> q = new NodeQueue<ArrayList<E>>();
	for(E val : arr){
	    q.add(new ArrayList<E>(){{add(val);}});
	}
	System.out.println(q);
	int N = q.size();
	for(int i = 0; i < N; i++){
	    ArrayList<E> a = q.remove();
	    
	    for(int k = 0; k < q.size(); k++){
		ArrayList<E> sub = new ArrayList<E>(a);
		ArrayList<E> t = q.remove();
		sub.addAll(t);
		q.add(sub);
		System.out.println(sub);
		q.add(t);
		
	    }
	    nQ.add(a);
	}
	return q;
	    
    }
    public static void main(String[] args){
	//String[] s = {"Bobby", "Sue", "Amy", "Mark", "Danny", "Kelly", "Yuki"};
	//MyQueue<String> q = buildQueue(new ArrayList<String>(Arrays.asList(s)));
	//System.out.println(josephus(q, 2));
	System.out.println(subsets(new ArrayList<Integer>(Arrays.asList(new Integer[] {1, 2, 3, 4}))));
    }
}
    
