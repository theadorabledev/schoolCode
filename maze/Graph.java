import java.lang.Math; 
import java.util.Arrays;
import java.util.*; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Graph{
	public HashMap<Integer, ArrayList<Integer []>> connections;
	public Graph(HashMap<Integer, ArrayList<Integer []>> arg){
		connections = arg;
	}
	public ArrayList<Object> djikstra(Integer startNode, Integer endNode){
		HashMap<Integer, Integer> dist = new HashMap<>();
		HashMap<Integer, Integer> lastNode = new HashMap<>();		
		for(Integer node : connections.keySet()){
			dist.put(node, Integer.MAX_VALUE);		
		}
		while(connections.size() > 0){
			int node = dictMin(dist);
			for(Integer conNode: connections.get(node)){
				int newVal = dist.get(conNode[0])+dist.get(conNode[1])	;		
			}
					
		}	
		dist.put(startNode, 0);
		System.out.println(dist);	
		return new ArrayList<Object>();
	}
	public Integer dictMin(HashMap<Integer, Integer> dict){
		int minKey = 0	;		
		int min = 0;
		for(Integer key : dict.keySet()){
			if (dict.get(key) <= min){
				minKey = 0;			
			}			
		}
		return minKey;
	}
}
