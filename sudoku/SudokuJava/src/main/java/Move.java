public class Move{
	public Coordinate coord;
	public int value;
	public int lastValue;
	public Move(Coordinate coord, int value, int lastValue){
		this.coord = coord;
		this.value = value;
		this.lastValue = lastValue;
	}
    public Move(String str){
	str = str.substring(1, str.length() - 1);
	String [] items = str.split(", ");
	this.coord = new Coordinate(Integer.valueOf(items[0]), Integer.valueOf(items[1]));
	this.value = Integer.valueOf(items[2]);
	this.lastValue = Integer.valueOf(items[3]);
	
    }
	@Override
	public String toString(){
		return "("+ coord.x + ", " + coord.y + ", " + value + ", " + lastValue + ")";
		
	}
}
