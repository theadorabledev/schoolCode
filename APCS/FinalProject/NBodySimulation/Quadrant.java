public class Quadrant{
	private double length;
	private double radius;
	private double x;
	private double y;
	private Quadrant NW;
	private Quadrant NE;
	private Quadrant SE;
	private Quadrant SW;
	public Quadrant(double x, double y, double length){
		this.x = x;
		this.y = y;
		this.length = length;
		radius = length / 2;
	}
	public double length(){
		return length;
	}
	public boolean contains(double xPos, double yPos){
		return(x - radius <= xPos &&  xPos <= x + radius && y - radius <= yPos && yPos <= y + radius);
	}
	public boolean contains(Particle p){
		return contains(p.xPosition, p.yPosition);
	}
	public Quadrant NW(){
		return new Quadrant(x - radius / 2, y + radius / 2, radius);
	}
	public Quadrant NE(){
		return new Quadrant(x + radius / 2, y + radius / 2, radius);
	}
	public Quadrant SE(){
		return new Quadrant(x + radius / 2, y - radius / 2, radius);
	}
	public Quadrant SW(){
		return new Quadrant(x - radius / 2, y - radius / 2, radius);
	}
}