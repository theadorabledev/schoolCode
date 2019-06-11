public class Particle{
	public static final double G = 6.673e-11;
	public double xPosition;
	public double yPosition;
	public double xVelocity;
	public double yVelocity;
	public double xForce;
	public double yForce;
	public double mass;
	public Particle(double xP, double yP, double xV, double yV, double m){
		xPosition = xP;
		yPosition = yP;
		xVelocity = xV;
		yVelocity = yV;
		mass = m;
	}
	public double distanceTo(Particle p){
		return Math.sqrt(Math.pow(p.xPosition - xPosition, 2) + Math.pow(p.yPosition - yPosition, 2));
	}
	public void addForce(Particle p){
		double xDist = p.xPosition - xPosition;
		double yDist = p.yPosition - yPosition;
		double dist = distanceTo(p);
		double Force = (G * p.mass * mass) / Math.pow(dist, 2);
		xForce += Force * xDist / dist;
		yForce += Force * yDist / dist;
	}
	public void clearForce(){
		xForce = 0;
		yForce = 0;
	}
	public void move(double t){
		xVelocity += t * xForce / mass;
		yVelocity += t * yForce / mass;
		xPosition += t * xVelocity;
		yPosition += t * yVelocity;
	}
	public static Particle centerOfMass(Particle a, Particle b){
		double m = a.mass + b.mass;
		double x = (a.xPosition * a.mass + b.xPosition * b.mass) / m;
		double y = (a.yPosition * a.mass + b.yPosition * b.mass) / m;
		return new Particle(x, y, 0, 0, m);
	}
}