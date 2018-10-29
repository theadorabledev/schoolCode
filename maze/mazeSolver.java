import java.lang.Math; 
import java.util.Arrays;
import java.util.*; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
public class mazeSolver{
	public static void main(String [] args) throws IOException{
		String readFile = args[0];
		String writeFile = readFile.substring(0, readFile.length() - 4) + "-solved.png";
		if(args.length > 1){
			writeFile = args[1];
		}
		BufferedImage image = ImageIO.read(new File(readFile));
		System.out.println("Initializing maze.");
		maze myMaze = new maze(image);
		System.out.println("Converting to graph");
		Graph graph = new Graph(myMaze.connections);
		System.out.println("Finding shortest route.");
		ArrayList<Integer> djikstraPath= graph.djikstra(myMaze.startNode, myMaze.endNode);
		//System.out.println(djikstraPath);
		System.out.println("Writing information to image.");
		ArrayList<Node> pathCoords = new ArrayList<Node>();
		ArrayList<Node> allPathCoords = new ArrayList<Node>();
		for(Integer node: djikstraPath){
			pathCoords.add(myMaze.nodes.get(node));
			//System.out.println(node + " " + myMaze.nodes.get(node).y + " " + myMaze.nodes.get(node).x);
		}
		for(int i = 0; i < pathCoords.size() - 1; i++){
			Node node = pathCoords.get(i);
			Node nextNode = pathCoords.get(i + 1);
			if(node.x == nextNode.x){
				int vMax = Math.max(node.y, nextNode.y);
				int vMin = Math.min(node.y, nextNode.y);
				for(int z = vMin; z <= vMax; z ++){
					allPathCoords.add(new Node(node.x, z));
					//System.out.println(z + " " + node.x);
				}
			}
			if(node.y == nextNode.y){
				int vMax = Math.max(node.x, nextNode.x);
				int vMin = Math.min(node.x, nextNode.x);
				for(int z = vMin; z <= vMax; z ++){
					allPathCoords.add(new Node(z, node.y));
					//System.out.println(node.y + " " + z);

				}
			}
		}
		for(Node node: allPathCoords){
			int p = image.getRGB(node.x, node.y);
			System.out.println(node.y + " " + node.x + " " + p);
			Color colour = new Color(255, 0, 0);//Color.red();
			//imgage.setRGB(colour.getRGB());
			//image.setRGB(node.x, node.y, colour.getRGB());
			p = (0<<24) | (255<<16) | (0<<8) | 0;
			image.setRGB(node.x, node.y, p);
			System.out.println(image.getRGB(node.x, node.y));
		}
		System.out.println(image);
		ImageIO.write(image, "png", new File(writeFile));
		
	}

}
