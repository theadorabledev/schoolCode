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
		public double position(){
			return pos;
		}
		public void setPos(double newPos){
			pos = newPos;
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
			Horizon.put((double) i, new Leprechaun(i));
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
		Set<Double> set = Horizon.keySet();
		//Iterator<Map.Entry<Double, Leprechaun>> iterator = Horizon.entrySet().iterator();
		List<Double> list = new ArrayList<Double>(set);
		for( Double pos : list) {
			//Map.Entry<Double, Leprechaun> entry = iterator.next();
			//Double pos = iterator.next().getKey();
			Leprechaun Henderson = Horizon.get(pos);
			//iterator.remove();
			Horizon.remove(pos);
			double dest = pos + (Math.random() * Henderson.gold());
			double previous = dest;
			double last = previous;
			Horizon.put(dest, Henderson);
			Henderson.setPos(dest);
			boolean out = false;
			for(Double oPos: Horizon.keySet()){
				if(previous == dest){
					//System.out.println(oPos + " " + previous+ " " + last);
					//System.out.println(Horizon.get(oPos) + " " + Horizon.get(previous) + " " + Horizon.get(last));
					Henderson.stealFrom(Horizon.get(oPos));
					Henderson.stealFrom(Horizon.get(last));
					//System.out.println(Horizon.get(oPos) + " " + Horizon.get(previous) + " " + Horizon.get(last));
					out=true;
					break;
				} 
				if(out) break;
			
				last = previous;
				previous = oPos;
			}
			//print();
			
		}	
			
		System.out.println("Finish : ");
		print();
		System.out.println("----------------\n");
		

	}
	public void print(){
		//int gold = 0;
		for(Double pos : Horizon.keySet()){
			System.out.print(Horizon.get(pos) + ", ");
			//gold += Horizon.get(pos).gold();
		}	
		System.out.println();
		//System.out.println("   " + (gold / Horizon.keySet().size()));
	}
	public static void main(String[] args){
		JumpingLeprechauns horizon = new JumpingLeprechauns(Integer.parseInt(args[0]));
		horizon.iterate(Integer.parseInt(args[1]));
	}
}
