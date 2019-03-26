import java.util.*;
public class PostFix{
    public static double eval(String s){
	List<String> myList = new ArrayList<String>(Arrays.asList(s.split(" ")));
	Stack<Double> stack = new Stack<Double>();
	for(String i : myList){
	    if(i.equals("+")){
		Double b = Double.valueOf(stack.pop());
		double a = Double.valueOf(stack.pop());
		stack.push(a + b);
	    }else if(i.equals("-")){
		Double b = Double.valueOf(stack.pop());
		double a = Double.valueOf(stack.pop());
		stack.push(a - b);
	    }else if(i.equals("/")){
		Double b = Double.valueOf(stack.pop());
		double a = Double.valueOf(stack.pop());
		stack.push(a / b);
	    }else if(i.equals("*")){
		Double b = Double.valueOf(stack.pop());
		double a = Double.valueOf(stack.pop());
		stack.push(a * b);
	    }else{
		stack.push(Double.valueOf(i));
	    }
	}
	return stack.pop();
    }
    public static void main(String[] args){
	System.out.println(eval("10 2.0 +"));
	System.out.println(eval("11 3 - 4 + 2.5 *"));
	System.out.println(eval("8 2 + 99 9 - * 2 + 9 -"));
	System.out.println(eval("1 2 3 4 5 + * - -"));
    }

}
