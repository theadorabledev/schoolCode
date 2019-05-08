import java.util.*;
public class Roller{
	public static void roll(Stack<String> stack, int n, int k) throws RuntimeException{
		if(n < 0 || k < 0 || n > stack.size()) throw new RuntimeException("Error: Argument out of range.");
		//Stack<String> roller = new Stack<String>();
		ArrayDeque<String> roller = new ArrayDeque<String>();
		for(int i = 0; i < n; i++){
			roller.push(stack.pop());
		}
		for(int i = 0; i < k; i++){
			String s = roller.removeLast();
			roller.addFirst(s);
		}
		ArrayList<String> arr = new ArrayList<String>(roller);
		//Collections.reverse(arr);
		for(int i = 0 ; i < roller.size(); i++){
			stack.push(arr.get(i));
		}
	}
	public static void main(String [] args){
		Stack<String> stack = new Stack<String>();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		System.out.println(stack);
		roll(stack, 2, 4);
		System.out.println(stack);
	}
} 
