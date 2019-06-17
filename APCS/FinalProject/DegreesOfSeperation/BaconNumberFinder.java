import java.io.*;
import java.util.*;
public class BaconNumberFinder{
	private HashSet<String> set = new HashSet<String>();
	private HashMap<String, HashSet<String>> connections = new HashMap<String, HashSet<String>>();
	private HashMap<String, Integer> distances = new HashMap<String, Integer>();
	private HashMap<String, ArrayList<String>> paths = new HashMap<String, ArrayList<String>>();
	private HashMap<String, HashMap<String, String>> movieConnections = new HashMap<String, HashMap<String, String>>();
	public BaconNumberFinder(String fileName) throws IOException{
		readFile(fileName);
		getShortestPaths();	
	}
	public void readFile(String fileName) throws IOException{
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String [] arr = line.split("/");
				//System.out.println(line);
				for(int i = 1; i < arr.length; i++){
					movieConnections.putIfAbsent(arr[i], new HashMap<String, String>());
					connections.putIfAbsent(arr[i], new HashSet<String>());
					distances.putIfAbsent(arr[i], Integer.MAX_VALUE);
					paths.putIfAbsent(arr[i], new ArrayList<String>());
					for(int k = 1; k < arr.length; k++){
						if(k != i){ 
							connections.get(arr[i]).add(arr[k]);
							movieConnections.get(arr[i]).put(arr[k], arr[0]);
						}
					}
				}
			}
		}
	}
	public void getShortestPaths(){
		String bacon = "Bacon, Kevin";
		distances.put(bacon, 0);
		LinkedList<String> q = new LinkedList<String>();
		q.add(bacon);
		//System.out.println(connections.get(bacon));
		
		while(!q.isEmpty()){
			//System.out.println(q.size());
			String current = q.poll();
			set.add(current);
			for(String s : connections.get(current)){
				if(!set.contains(s)) q.add(s);
				if(distances.get(current) + 1 < distances.get(s) ){
					distances.put(s, distances.get(current) + 1);
					ArrayList<String> path = (ArrayList<String>) paths.get(current).clone();
					path.add(current);
					paths.put(s, path);
				}
			}
		}
		//System.out.println(connections);
	}
	public void printDistances(){
		HashMap<Integer, Integer> d = new HashMap<Integer, Integer>();
		for(String s : distances.keySet()){
			int i = distances.get(s);
			d.putIfAbsent(i, 0);
			d.put(i, d.get(i) + 1);
		}
		System.out.println("Bacon Number : Frequency");
		for(Integer i : d.keySet()){
			if(i != Integer.MAX_VALUE) System.out.println(i + " : " + d.get(i));
		}
		System.out.println("Infinity : " + d.get(Integer.MAX_VALUE));
	}
	public void printPath(String name){
		if(!connections.keySet().contains(name)){
			System.out.println("That actor is not in our database.");
			return;
		}
		ArrayList<String> path = paths.get(name);
		String last = name;
		if(distances.get(name) == Integer.MAX_VALUE){
			System.out.println(name + " has a Kevin Bacon number of : Infinity");
			return;
		}
		System.out.println(name + " has a Kevin Bacon number of : " + distances.get(name));
		
		for(int i = path.size() - 1; i >= 0; i--){
			System.out.println(last + " was in \"" + movieConnections.get(last).get(path.get(i)) + "\" with " + path.get(i));
			last = path.get(i);
		}
	}
	public static void main(String [] args) throws IOException{
		String fileName = args[0];
		BaconNumberFinder b = new BaconNumberFinder(fileName);
		b.printDistances();
		System.out.println("Please input an actor's name : ");
		Scanner scanner = new Scanner(System.in);
		try{
			while (scanner.hasNextLine()) {
				
				String name = scanner.nextLine();
				if(name.equals("")) scanner.close();
				else{
					b.printPath(name);
					System.out.println();
					System.out.println("Please input an actor's name : ");
				}
			}

			scanner.close();
		}catch (IllegalStateException e){
			System.out.println("Program Terminated.");
		}
	}
	
	
}