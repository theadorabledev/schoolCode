import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
public class MorseCodeTranslator{
	private static String[] letters = {"A", "B", "C", "D", "E","F","G","H","I", "J", "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 
	private static String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
	 private static HashMap<String,String> dict = new HashMap<String,String>(){{
	 	for(int i = 0; i < 26;i++){
	 		put(letters[i],morse[i]);
	 		put(morse[i],letters[i]);
	 	}
	 }};
	//This function is utilized as a switchboard that 
	//determines which translator function must be utilized 
	public static String Translate(String message){
		message = message.toUpperCase();
		String output = "";
		if(message.substring(0,1).equals(".")||message.substring(0,1).equals(".")){
			String[] keeper = message.split(" ");
			for(int i = 0; i < keeper.length; i++ ){
				if(keeper[i].length() == keeper[i].replaceAll("[a-zA-Z]","").length()){
					output += dict.get(keeper[i]);
				}
			}
		}
		else{
			for(int i = 0; i < message.length(); i++){
				//if() check if it is a letter
				if(Character.isLetter(message.charAt(i))){
					output += dict.get(message.substring(i,i+1));
					output += " ";
				}
			}
		}
		return output;
	}
	// This is main method that processes the program
	public static void main(String[] args) {
	    Scanner sys = new Scanner(System.in);
	    while(true){
			System.out.print("> ");
    		String message = sys.nextLine();
    		if(message.equals("")) break;
    		 System.out.println(Translate(message));
		}
		System.out.println("The program has been terminated by the user.");
	}
}