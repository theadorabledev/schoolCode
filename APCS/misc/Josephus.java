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
	public static void main(String[] args){
		String[] s = {"Bobby", "Sue", "Amy", "Mark", "Danny", "Kelly", "Yuki"};
		MyQueue<String> q = buildQueue(new ArrayList<String>(Arrays.asList(s)));
		System.out.println(josephus(q, 2));
	}
}

