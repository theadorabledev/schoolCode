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
	public ArrayList<Integer> djikstra(Integer startNode, Integer endNode){
		HashMap<Integer, Integer> dist = new HashMap<>();
		HashMap<Integer, Integer> lastNode = new HashMap<>();	
		ArrayList<Integer> order = new ArrayList<Integer>();
		order.add(endNode);
		for(Integer node : connections.keySet()){
			dist.put(node, Integer.MAX_VALUE);
			lastNode.put(node, null);
		}
		dist.put(startNode, 0);
		lastNode.put(startNode, 0);
		while(connections.size() > 0){
			int node = dictMin(dist);
			if(node == endNode){
				break;
			}
			//System.out.println("\n\n");
			for(Integer[] conNode: connections.get(node)){
				//System.out.println(node + " " + conNode[0]);
				int newVal = dist.get(node)	+ conNode[1];	
				if(dist.get(conNode[0]) > newVal){
					dist.put(conNode[0], newVal);
					lastNode.put(conNode[0], node);
				}
			}	
			connections.remove(node);
		}	
		int curNode = lastNode.get(endNode);
		while(curNode != 0){
			order.add(0,curNode);
			curNode = lastNode.get(curNode);
		}
		//System.out.println(dist);	
		return order;// new ArrayList<Object>();
	}
	public Integer dictMin(HashMap<Integer, Integer> dict){
		int minKey = 0;		
		int min = Integer.MAX_VALUE;
		for(Integer key : dict.keySet()){
			//System.out.println(key + " " + dict.get(key) + " " + connections.keySet().contains(key));
			if ((dict.get(key) <= min) && connections.keySet().contains(key)){
				minKey = key;	
				min = dict.get(key);
			}			
		}
		return minKey;
	}
}
