public class Body{
	public static final double G = 6.673e-11;
	public double xPosition;
	public double yPosition;
	public double xVelocity;
	public double yVelocity;
	public double xForce;
	public double yForce;
	public double mass;
	public Body(double xP, double yP, double xV, double yV, double m){
		xPosition = xP;
		yPosition = yP;
		xVelocity = xV;
		yVelocity = yV;
		mass = m;
	}
	public double distanceTo(Body b){
		return Math.sqrt(Math.pow(b.xPosition - xPosition, 2) + Math.pow(b.yPosition - yPosition, 2));
	}
	public void addForce(Body b){
		double xDist = b.xPosition - xPosition;
		double yDist = b.yPosition - yPosition;
		double dist = distanceTo(b);
		double Force = (G * b.mass * mass) / Math.pow(dist, 2);
		xForce += Force * xDist / dist;
		yForce += Force * yDist / dist;
	}
	public void clearForce(){
		xForce = 0;
		yForce = 0;
	}
	public void move(double t){
		System.out.println("moving");
		double tiny = 1E4;
		System.out.println(this);
		xVelocity += t * xForce / mass;
		yVelocity += t * yForce / mass;
		xPosition += t * xVelocity;
		yPosition += t * yVelocity;
		System.out.println(this);
	}
	public static Body centerOfMass(Body a, Body b){
		double m = a.mass + b.mass;
		double x = (a.xPosition * a.mass + b.xPosition * b.mass) / m;
		double y = (a.yPosition * a.mass + b.yPosition * b.mass) / m;
		return new Body(x, y, 0, 0, m);
	}
	public String toString(){
		return "(" + xPosition + ", " + yPosition + ", " + xVelocity + ", " + yVelocity + ", " + mass + ")";
	}
}
