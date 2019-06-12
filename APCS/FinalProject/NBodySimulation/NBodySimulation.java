import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class NBodySimulation extends Applet{
    private Particle [] particles;
    private double radius;
	private Quadrant q;
	public NBodySimulation(double radius, Particle[] particles){
		this.radius = radius;
		this.particles = particles;
		q = new Quadrant(0, 0, radius * 2);
		for(Particle p : particles){
			System.out.println(p);
		}
	}
	public void iterate(){
		BarnesHutTree t = new BarnesHutTree(q);
		for(Particle p : particles) {
			if(q.contains(p)) t.insert(p);
		}
		for(Particle p : particles){
				p.clearForce();
				if(q.contains(p)){
					t.updateForces(p);
					p.move(1e5);
				}
		}
	}
    public static String getTextFromStdin(){
		Scanner s = new Scanner(System.in).useDelimiter("\\A");
		String text = s.hasNext() ? s.next() : "";
		//text.replace("\n","");
		return text;
    }
    public static String [] lines(){
		ArrayList<String> lines = new ArrayList<String>();
		Scanner s = new Scanner(System.in).useDelimiter("\\A");
		return s.next().split("\n");
    }
	public static Particle[] spawnParticles(String [] lines){
		Particle [] particles = new Particle[Integer.parseInt(lines[0])]; 
		for(String s : lines){
			System.out.println(s);
		}
		for(int i = 2; i < particles.length + 2; i++){
			String[] data = lines[i].split("\\s+");
			System.out.println(Arrays.asList(data));
			Particle p = new Particle(Double.parseDouble(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]),Double.parseDouble(data[5]));
			particles[i - 2] = p;
		}
		return particles;
		
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
			
			//StdDraw.clear();
			for(Particle p : particles){
				System.out.print(p + " ");
				StdDraw.point(p.xPosition, p.yPosition);
				
			}
			System.out.println();
			//StdDraw.clear();
			StdDraw.show();
            StdDraw.pause(10);
			StdDraw.clear();
			//System.out.println(particles);
			System.out.println("Next");
			iterate();
		}
		//double rx = 0.480, ry = 0.860;     // position
        //double vx = 0.015, vy = 0.023;     // velocity
        //double radius = 0.03;              // a hack since "earth.gif" is in pixels

        // set the scale of the coordinate system
        //StdDraw.setXscale(-1.0, 1.0);
        //StdDraw.setYscale(-1.0, 1.0);
        //StdDraw.enableDoubleBuffering();
		//StdDraw.picture(0, 0, "resources/starfield.jpg"); 
		//StdDraw.show();
        // main animation loop
	/*
        while (true) { 
            if (Math.abs(rx + vx) + radius > 1.0) {
                vx = -vx;
                StdAudio.play("resources/laser.wav");
            }
            if (Math.abs(ry + vy) + radius > 1.0) {
                vy = -vy;
                StdAudio.play("resources/pop.wav");
            }
            rx = rx + vx; 
            ry = ry + vy; 
            StdDraw.clear();
            StdDraw.picture(rx, ry, "resources/earth.gif");
            StdDraw.show();
            StdDraw.pause(20); 
	    }*/
    }
	
    public static void main(String [] args){
		//double t = Double.parseDouble(args[0]);
		//double dt = Double.parseDouble(args[1]);
		String[] lines = lines();
		
		NBodySimulation s = new NBodySimulation(getRadius(lines), spawnParticles(lines));
		s.animate();
    }
	
}
