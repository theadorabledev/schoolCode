import java.util.*;
public class Evaluator{


	// precondition: exp is a String expression that is fully parenthesized (a set
	//               of parentheses for each operator).
	// postcodition: returns a String [] of the tokens of exp.
	//               ex. getTokens("(5-2.2)") -> ["(","5","-","2.2",")"]
	public static String[] getTokens(String exp){
		exp = exp.replaceAll(" ", "");
		String[] tokens = exp.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");
		return tokens;
	}
	

	// precondition: receives an array of the tokens of an infix expression.
	//               tokens must be one of the following:
	//               (),+-*/%,sqrt
	// postcondition: evalutes the expression represented in expTokens
	//             ex. evaluate(["(","5","-","2.2",")"]) returns 2.8
	public static Double evaluate(String[] expTokens){
		//System.out.println(Arrays.asList(expTokens));
		Stack<String> stack = new Stack<String>();
		for(int i = 0; i < expTokens.length; i++){
			if(expTokens[i].equals(")")){
				ArrayList<String> arr = new ArrayList<String>();
				while(!stack.peek().equals("(")){
					arr.add(stack.pop());
				}
				stack.pop();
				stack.push(minEval(arr));
			}else{
				stack.push(expTokens[i]);
			}
		}
		return new Double(stack.pop());
	}
	public static String minEval(ArrayList<String> arr){
		Stack<String> stack = new Stack<String>();
		stack.addAll(arr);
		double d = 0;
		if(stack.size() == 1) return stack.pop();

		String a = stack.pop();
		String b = stack.pop();
		if(b.equals("+")){
			d = Double.valueOf(a) + Double.valueOf(stack.pop());
		}else if(b.equals("-")){
			d = Double.valueOf(a) - Double.valueOf(stack.pop());
		}
		else if(b.equals("*")){
			d = Double.valueOf(a) * Double.valueOf(stack.pop());
		}
		else if(b.equals("/")){
			d = Double.valueOf(a) / Double.valueOf(stack.pop());
		}
		else if(b.equals("%")){
			d = Double.valueOf(a) % Double.valueOf(stack.pop());
		}else if( a.equals("sqrt")){
			d = Math.sqrt(Double.valueOf(b));
		}
		if(stack.size() != 0){
			stack.push(d + "");
			ArrayList<String> newArr = new ArrayList<String>(stack);
			return minEval(newArr);
		}
		return d + "";
	}
	
	public static void main(String [] args){
		
		String[] expressions = {"(2+3)","(4 + (3 - 2))", "((5 *(10 + (32 - 8))/ 2.0))",
			"( sqrt  (16 * 16) )", "(( 25 % 3 ) + 9)"};
		Double[] expectedResults = {5.0,5.0,85.0,16.0,10.0};

		for (int i = 0; i < expressions.length; i++){
			String[] tokens = getTokens(expressions[i]);
			Double  ansObserved = evaluate(tokens);
			Double  ansExpected = expectedResults[i];
			System.out.println(expressions[i] + " = " + evaluate(tokens));
			System.out.println("observed: " + ansObserved + " expected: " + ansExpected);
			System.out.println("correct? " + (ansObserved.equals( ansExpected)));
		}
	}
	
}

