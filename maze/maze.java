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
	private int count = 2;

	public maze(BufferedImage image){
		width = image.getWidth();
		height = image.getHeight();
		colorFix.put(-1, 1);
		colorFix.put(-16777216, 0);
		fixData(image);
		for(int x = 0; x < width; x++){
			if(data.get(x).equals(1)){
				nodes.put(1, new Node(x, 0));
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
					nodes.put(count, new Node(x, y));
					count++;
				}
			}
		}
		for(int x = 0; x < width; x++){
			if(data.get(width * height - height + x).equals(1)){
				nodes.put(count, new Node(x, height - 1));
				break;
			}
		}
	}

	public void fixData(BufferedImage image){
		for(int y = 0;y < height; y++){
			for(int x = 0; x < width; x++){
				data.add(colorFix.get(image.getRGB(x, y)));
			}
		}
		
	}

	
	public class Node{
		public int x;
		public int y;
		public Node(int a, int b){
			x = a;
			y = b;
		}
	}
}