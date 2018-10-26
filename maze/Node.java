import java.lang.Math; 
import java.util.Arrays;
import java.util.*; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Node{
	public int x;
	public int y;
	public ArrayList<Integer []> connections = new ArrayList<Integer []>();
	public Node(int a, int b){
		x = a;
		y = b;
	}
	public boolean equals(Node node){
		if(node.x == x && node.y == y){
			return true;
		}
		return false;
	}
}