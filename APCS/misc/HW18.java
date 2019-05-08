import java.util.Scanner;
public class HW18{
	public static void reverse(){
		Scanner s = new Scanner(System.in);
		NodeStack<Integer> stack = new NodeStack<Integer>();
		for(int i = s.nextInt(); i != 0; i = s.nextInt()){
			stack.push(i);
		}
		System.out.println(0);
		while(!stack.empty()){
			System.out.println(stack.pop());
		}
	}
	public static boolean matched(String s){
		NodeStack<String> stack = new NodeStack<String>();
		String[] chars = s.split("");
		for(String c : chars){
			if(c.equals("(") || c.equals("[") || c.equals("{")){
				stack.push(c);
			}else if(c.equals("}") || c.equals("]") || c.equals(")")){
				if(stack.empty()) return false;
				if((c.equals("}") && stack.peek().equals("{")) || (c.equals("]") && stack.peek().equals("[")) || (c.equals(")") && stack.peek().equals("("))){
					stack.pop();
				}else{
					return false;
				
				}
			}
		}
		return true;
	}
	public static void main(String [] args){
		System.out.println(matched(args[0]));
	}
}