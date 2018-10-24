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
		HashMap<Integer, ArrayList<Integer []>> djikstraPath = djikstra(myMaze.connections, myMaze.startNode, myMaze.endNode);
		for(Integer n : djikstraPath.keySet()){
			for(Integer [] o : djikstraPath.get(n)){
				//System.out.println(n + " " + Arrays.toString(o));
			}
		}
		//System.out.println(djikstraPath);
	}
	public static HashMap<Integer, ArrayList<Integer[]>> djikstra(HashMap<Integer, ArrayList<Integer[]>> connections, int startNode, int endnode){
		return connections;
	}
}