import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class NBodySimulation extends Applet{
    public int M;
    public Particle [] Particles;
    public void init(){
    }
    public static String getTextFromStdin(){
	Scanner s = new Scanner(System.in).useDelimiter("\\A");
	String text = s.hasNext() ? s.next() : "";
	//text.replace("\n","");
	return text;
    }
    public void parseStdin(){
	//Scanner s = new Scanner(System.in).useDelimiter("\\A");
	//String [] lines = new String [];
	//String text = s.hasNext() ? s.next() : "";
    }
    public static void animate(){
     double rx = 0.480, ry = 0.860;     // position
        double vx = 0.015, vy = 0.023;     // velocity
        double radius = 0.03;              // a hack since "earth.gif" is in pixels

        // set the scale of the coordinate system
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();
	StdDraw.picture(0, 0, "resources/nightsky.jpg"); 
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
	animate();
    }
	
}
