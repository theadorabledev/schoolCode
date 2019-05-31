import java.util.*;
public class JumpingLeprechauns{
    private static class Leprechaun{
	private int gold = 1000000;
	private int number;
	public Leprechaun(int number){
	    number = this.number;
	}
	public int stealHalf(){
	    gold /= 2;
	    return gold;
	}
	public int gold(){
	    return gold;
	}
	public int number(){
	    return number;
	}
    }
    private HashMap<Double, Integer> horizon = new HashMap<Double, Integer>();
    public JumpingLeprechauns(){
	
    }
}
