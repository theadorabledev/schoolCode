import java.util.*;
public class JumpingLeprechauns{
	private static class Leprechaun implements Comparable{
		private int gold = 1000000;
		private int number;
		private double pos;
		public Leprechaun(int number){
			this.number = number;
			pos = number;
		}
		public int stealHalf(){
			gold = gold / 2;
			return gold;
		}
		public void stealFrom(Leprechaun other){
			if(this == other) return;
			gold += other.stealHalf();
		}
		public int gold(){
			return gold;
		}
		public int number(){
			return number;
		}
		public int compareTo(Object o){
			return ((Leprechaun) o).gold () - gold;
		}
		public String toString(){
			return "(#" + number + " : " + gold + "g)";// : @" + pos + ")";
		}
	}
	private TreeMap<Double, Leprechaun> Horizon = new TreeMap<Double, Leprechaun>();
	private Random rand = new Random();
	public JumpingLeprechauns(int numLeprechauns){
		for(int i = 0; i < numLeprechauns; i++){
			Horizon.put(Math.random(), new Leprechaun(i));
		} 
	}
	public void iterate(int rounds){
		for(int i = 0; i < rounds; i++){
			iterate();
		}
	}
	public void iterate(){
		System.out.println("----------------\nStart : ");
		print();
		List<Double> list = new ArrayList<Double>(Horizon.keySet());
		for( Double pos : list) {
			Leprechaun Henderson = Horizon.get(pos);
			Horizon.remove(pos);
			double dest = pos + (Math.random() * Henderson.gold());
			double previous = dest;
			double last = previous;
			Horizon.put(dest, Henderson);
			for(Double oPos: Horizon.keySet()){
				if(previous == dest){
					Henderson.stealFrom(Horizon.get(oPos));
					Henderson.stealFrom(Horizon.get(last));
					break;
				} 
				last = previous;
				previous = oPos;
			}			
		}		
		System.out.println("Finish : ");
		print();
		System.out.println("----------------");
		

	}
	public void print(){
		//for(Double pos : Horizon.keySet()){
		//	System.out.print(pos + ", ");
		//}	
		//System.out.println();
		for(Double pos : Horizon.keySet()){
			System.out.print(Horizon.get(pos) + ", ");
		}	
		System.out.println();
	}
	public static void main(String[] args){
		JumpingLeprechauns horizon = new JumpingLeprechauns(Integer.parseInt(args[0]));
		horizon.iterate(Integer.parseInt(args[1]));
	}
}
