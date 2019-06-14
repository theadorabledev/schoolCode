import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class NBodySimulation extends Applet{
	private Body [] bodies;
	private double radius;
	private Quadrant q;
	private double time;
	private double dt;
	private double sigMin;
	private double sigMax;
	public NBodySimulation(double radius, Body[] bodies, double time, double dt){
		this.radius = radius;
		this.bodies = bodies;
		this.time = time;
		this.dt = dt;
		q = new Quadrant(0, 0, radius * 2);
		for(Body b : bodies){
			System.out.println(b);
		}
		setupSigmoid();
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
				b.move(dt);
			}
		}
	}
	public static String [] lines(){
		Scanner s = new Scanner(System.in).useDelimiter("\\A");
		String[] arr = s.next().split("\n");
		ArrayList<String> lines = new ArrayList<String>(Arrays.asList(arr));
		while(lines.contains("")) lines.remove("");
		return lines.toArray(new String [lines.size()]);
	}
	public static Body[] spawnBodies(String [] lines){
		Body [] bodies = new Body[Integer.parseInt(lines[0].trim())]; 
		for(String s : lines){
			System.out.println(s);
		}
		for(int i = 2; i < bodies.length + 2; i++){
			String[] data = lines[i].split("\\s+");
			ArrayList<String> d = new ArrayList<String>(Arrays.asList(data));
			while(d.contains("")) d.remove("");
			data = d.toArray(new String[d.size()]);
			System.out.println(Arrays.asList(data));
			Body b = new Body(Double.parseDouble(data[0]),Double.parseDouble(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]));
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
		while(time > 0){
			for(Body b : bodies){
				//System.out.print(b + " ");
				StdDraw.setPenRadius(0.001 * Math.log10(b.mass));
		//System.out.println(sigMin + " " + sigMax + " "  + (int) ((sigMax - Math.log10(b.mass)) / (sigMax - sigMin) * 255));
				//StdDraw.setPenColor((int) ((sigMax - Math.log10(b.mass)) / (sigMax - sigMin) * 255), 0, 0);
				if(q.contains(b)) StdDraw.point(b.xPosition, b.yPosition);
			}
			//System.out.println();
			StdDraw.show();
			StdDraw.pause(10);
			StdDraw.clear();
			System.out.println(time);
			iterate();
			time -= dt;
		}
	}
	private void setupSigmoid(){
		double sigMin = bodies[0].mass;
		double sigMax = bodies[0].mass;
		for(Body b : bodies){
			sigMin = (b.mass < sigMin) ? b.mass : sigMin;
			sigMax = (b.mass > sigMax) ? b.mass : sigMax;
			//if(b.mass > maxMass) maxMass = b.mass; 
		}
		sigMin = Math.log10(sigMin);
		sigMax = Math.log10(sigMax);
		System.out.println("min" + sigMin);
	}

	private Color getBodyColor(Body b){
		System.out.println(sigMin + " " + sigMax + " "  + (int) ((sigMax - Math.log10(b.mass)) / (sigMax - sigMin) * 255));
		return new Color((int) ((sigMax - Math.log10(b.mass)) / (sigMax - sigMin) * 255), 0, 0);
	}
	public static void main(String [] args){
		double time = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String[] lines = lines();	
		NBodySimulation s = new NBodySimulation(getRadius(lines), spawnBodies(lines), time, dt);
		s.animate();
	}
	
}
