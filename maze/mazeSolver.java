import java.lang.Math; 
import java.util.Arrays;
import java.util.*; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class mazeSolver{
	public static void main(String [] args) throws IOException{

		BufferedImage image = ImageIO.read(new File("maze.png"));
		maze myMaze = new maze(image);
		System.out.println(myMaze.data);
		for(Integer key: myMaze.nodes.keySet()){
			System.out.println(key + "  " + myMaze.nodes.get(key).y + "  " + myMaze.nodes.get(key).x );
		}
		
	}
}