public class Move{
	public Coordinate coord;
	public int value;
	public int lastValue;
	public Move(Coordinate coord, int value, int lastValue){
		this.coord = coord;
		this.value = value;
		this.lastValue = lastValue;
	}
	@Override
	public String toString(){
		return "("+ coord + ", " + value + ", " + lastValue + ")";
		
	}
}