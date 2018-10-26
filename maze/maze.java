import java.lang.Math; 
import java.util.Arrays;
import java.util.*; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class maze{
	
	public int test = 10;
	public ArrayList<Integer> data = new ArrayList<>();
	public int height = 0;
	public int width = 0;
	private int white = -1;
	private int black = -16777216;
	private HashMap<Integer, Integer> colorFix = new HashMap<Integer, Integer>();
	public HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
	public HashMap<Node, Integer> rNodes = new HashMap<Node, Integer>();
	private int count = 2;
	private Set<Node> blacks;
	public HashMap<Integer, ArrayList<Integer []>> connections = new HashMap<>();
	public int startNode = 1;
	public int endNode = 0;
	public maze(BufferedImage image){
		width = image.getWidth();
		height = image.getHeight();
		colorFix.put(-1, 1);
		colorFix.put(-16777216, 0);
		fixData(image);
		blacks = getBlacks();
		System.out.println("	Defining nodes.");
		for(int x = 0; x < width; x++){
			if(data.get(x).equals(1)){
					Node n = new Node(x, 0);
					nodes.put(1, n);
					rNodes.put(n, 1);
					break;
			}
		}
		for(int y = 1; y < height - 1; y++){
			int rowChange = y * width;
			int rowAbove = rowChange - width;
			int rowBelow = rowChange + width;
			int prv = 0;
			int cur = 0;
			int nxt = (int) data.get(rowChange + 1);
			for(int x = 1; x < width; x++){
				prv = cur;
				cur = nxt;
				nxt = (int) data.get(rowChange + x + 1);
				boolean aboveBlack = data.get(rowAbove + x).equals(0);
				boolean belowBlack = data.get(rowBelow + x).equals(0);
				boolean XOX = (prv == 0) && (nxt == 0);
				boolean OOO = (prv == 1) && (cur == 1) && (nxt == 1);
				if(cur !=0 && !(OOO && aboveBlack && belowBlack) && !(XOX && !(aboveBlack || belowBlack)) ){
					Node n = new Node(x, y);
					nodes.put(count, n);
					rNodes.put(n, count);
					count++;
				}
			}
		}
		for(int x = 0; x < width; x++){
			if(data.get(width * height - height + x).equals(1)){
					endNode = count;
					Node n = new Node(x, height - 1);
					nodes.put(count, n);
					rNodes.put(n, count);
					break;
			}
		}
		System.out.println("		Connecting nodes.");
		updateConnections();

	}
	private boolean getConnectedNode(Node node, int x, int y){
		for(Node xNode : rNodes.keySet()){
			if(xNode.x == x && xNode.y == y){
				Integer [] arr  = {rNodes.get(xNode), Math.abs(xNode.x + xNode.y - x - y)};
				node.connections.add(arr);
				return true;
			}
		}
		for(Node blackNode : blacks){
			if(blackNode.x == x && blackNode.y == y){
				return true;
			}
		
		}
			
		return false;
	}
	private void updateConnections(){		
		for(Integer iNode : nodes.keySet()){
			//System.out.print("Node : " + iNode);
			Node node = nodes.get(iNode);
			
			for(int x = node.x + 1; x < width; x++){
				//Node n = new Node(x, node.y);
				if(getConnectedNode(node, x, node.y)){
					break;
				}		
			}
			for(int x = node.x - 1; x > 0; x--){
				//Node n = new Node(x, node.y);
				if(getConnectedNode(node, x, node.y)){
					break;
				}		
			}
			for(int y = node.y + 1; y < height + 1; y++){
				//Node n = new Node(node.x, y);
				if(getConnectedNode(node, node.x, y)){
					break;
				}		
			}
			for(int y = node.y - 1; y > -1; y--){
				//Node n = new Node(node.x, y);
				
				if(getConnectedNode(node, node.x, y)){					
					break;
				}	
								
			}
			//System.out.println(iNode + " ("+node.x +","+node.y +") " + nodes.get(iNode).connections);	
				
		}
		for(Integer node : nodes.keySet()){
			connections.put(node, nodes.get(node).connections);
			//System.out.println(" " + node.y +" " + node.x);		
		}
		//System.out.println(connections);
	}
	public void fixData(BufferedImage image){
		for(int y = 0;y < height; y++){
			for(int x = 0; x < width; x++){
				data.add(colorFix.get(image.getRGB(x, y)));
			}
		}
		
	}
	private Set<Node> getBlacks(){
		Set<Node> blacks = new HashSet<Node>();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(data.get(y * width + x).equals(0)){
					blacks.add(new Node(x, y));
				}
			}
		}
    return blacks;
	}
	
}
