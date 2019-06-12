import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class NBodySimulation extends Applet{
    private Body [] bodies;
    private double radius;
    private Quadrant q;
    public NBodySimulation(double radius, Body[] bodies){
	this.radius = radius;
	this.bodies = bodies;
	q = new Quadrant(0, 0, radius * 2);
	for(Body b : bodies){
	    System.out.println(b);
	}
	}
    public void iterate(){
	BarnesHutTree t = new BarnesHutTree(q);
	for(Body b : bodies) {
	    if(q.contains(b)) t.insert(b);
	}
	for(Body b : bodies){
	    b.clearForce();
	    if(q.contains(b)){
		t.updateForces(b);
		b.move(1e5);
	    }
	}
    }
    public static String [] lines(){
	ArrayList<String> lines = new ArrayList<String>();
	Scanner s = new Scanner(System.in).useDelimiter("\\A");
	String[] arr = s.next().split("\n");
	return arr;
	for(String s : arr){
	    
	}
    }
    public static Body[] spawnBodies(String [] lines){
		Body [] bodies = new Body[Integer.parseInt(lines[0])]; 
		for(String s : lines){
		    System.out.println(s);
		}
		for(int i = 2; i < bodies.length + 2; i++){
		    String[] data = lines[i].split("\\s+");
		    System.out.println(Arrays.asList(data));
		    Body b = new Body(Double.parseDouble(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]),Double.parseDouble(data[5]));
		    bodies[i - 2] = b;
		}
		return bodies;
		
    }
    public static double getRadius(String []  lines){
	return Double.parseDouble(lines[1]);
    }
    public void animate(){
	StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
	StdDraw.enableDoubleBuffering();
	StdDraw.setPenRadius(0.03);
	while(true){
	    for(Body b : bodies){
		System.out.print(b + " ");
		StdDraw.point(b.xPosition, b.yPosition);
	    }
	    System.out.println();
	    StdDraw.show();
            StdDraw.pause(10);
	    StdDraw.clear();
	    System.out.println("Next");
	    iterate();
	}
    }
	
    public static void main(String [] args){
	//double t = Double.parseDouble(args[0]);
	//double dt = Double.parseDouble(args[1]);
	String[] lines = lines();	
	NBodySimulation s = new NBodySimulation(getRadius(lines), spawnBodies(lines));
	s.animate();
    }
    
}
