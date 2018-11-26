import java.util.*;
import java.io.*;
public class SudokuPuzzle implements Serializable{
	private Integer[][] data = new Integer[9][9];
	private boolean solved = false;
	public static final HashMap<Integer, HashSet<Coordinate>> groupCoords =  getCoords("groups");
	public static final HashMap<Integer, HashSet<Coordinate>> rowCoords =  getCoords("rows");
	public static final HashMap<Integer, HashSet<Coordinate>> columnCoords =  getCoords("columns");
	private static final List<Integer> defaultValues = Arrays.asList(1,2,3,4,5,6,7,8,9);
	
	private HashMap<Coordinate, HashSet<Integer>> possibleValues = new HashMap<Coordinate, HashSet<Integer>>();
	private HashMap<Coordinate, HashSet<Integer>> impossibleValues = new HashMap<Coordinate, HashSet<Integer>>();
	private HashMap<Integer, HashSet<Integer>> groups = new HashMap<Integer, HashSet<Integer>>();
	private HashMap<Integer, HashSet<Integer>> rows = new HashMap<Integer, HashSet<Integer>>();
	private HashMap<Integer, HashSet<Integer>> columns = new HashMap<Integer, HashSet<Integer>>();
	private long startTime = System.currentTimeMillis();
	private int knownCount = 0;
	private int backTracks = 0;
	private Coordinate root;
	private int rootVal;
	private ArrayList<SudokuPuzzle> savedStates = new ArrayList<SudokuPuzzle>();
	
	//private Pair[][] rowCoords;
	public static HashMap<Integer, HashSet<Coordinate>> getCoords(String what){
		HashMap<Integer, HashSet<Coordinate>> vals = new HashMap<Integer, HashSet<Coordinate>>();
		for (int y = 0; y < 9; y++){
			for (int x = 0; x < 9; x++){
				
				//switch(what){
				if(what.equals("groups")){
				//case "groups":
					vals.putIfAbsent(getGroup(x, y) , new HashSet<Coordinate>());
					vals.get(getGroup(x, y)).add(new Coordinate(x, y));
				}else if(what.equals("rows")){ 
					//case "rows":
					vals.putIfAbsent(y , new HashSet<Coordinate>());
					vals.get(y).add(new Coordinate(x, y));
				}else if(what.equals("columns")){
					//case "columns":
					vals.putIfAbsent(x , new HashSet<Coordinate>());
					vals.get(x).add(new Coordinate(x, y));
				}
				
			}			
		}
		return vals;
		
	}
	private void copy(SudokuPuzzle other){
		this.possibleValues = other.possibleValues;
		this.impossibleValues = other.impossibleValues;
		this.groups = other.groups;
		this.rows = other.rows;
		this.columns = other.columns;
		this.startTime = other.startTime;
		this.knownCount = other.knownCount;
		this.backTracks = other.backTracks;
		this.root = other.root;
		this.rootVal = other.rootVal;
		
	}
	private void init(){
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9; x++){
				possibleValues.put(new Coordinate(x, y), new HashSet<Integer>(defaultValues));//defaultValues));
				impossibleValues.put(new Coordinate(x, y), new HashSet<Integer>());
			}
			groups.put(y + 1, new HashSet<Integer>(defaultValues));
			rows.put(y, new HashSet<Integer>(defaultValues));
			columns.put(y, new HashSet<Integer>(defaultValues));
			for(int x = 0; x < 9; x++){
				if(data[y][x] != 0){
					knownCount++;
				}
			}
		}
		for(int i = 0; i < 9; i++){
			HashSet<Integer> rowValues = new HashSet<Integer>();
			HashSet<Integer> colValues = new HashSet<Integer>();
			HashSet<Integer> boxValues = new HashSet<Integer>();
			for(Coordinate coord : groupCoords.get(i + 1)){
				boxValues.add(data[coord.y][coord.x]);
				boxValues.remove(0);
			}
			for(Coordinate coord : rowCoords.get(i)){
				rowValues.add(data[coord.y][coord.x]);
				rowValues.remove(0);	
			}
			for(Coordinate coord : columnCoords.get(i)){
				colValues.add(data[coord.y][coord.x]);
				rowValues.remove(0);
			}
			groups.put(i + 1, boxValues);
			rows.put(i, rowValues);
			columns.put(i, colValues);
			
		}
		
	}
	public SudokuPuzzle(String fileName){
		loadFromFile(fileName);
		init();
		
	}
	public static void main(String[] args){
		SudokuPuzzle s = new SudokuPuzzle(args[0]);
		//s.init();
		s.solve();
		s.printData();
		System.out.println("Stats: ");
		System.out.println("Backtracks : " + s.backTracks);
		System.out.println("It took " + (System.currentTimeMillis() - s.startTime) + " milliseconds.");
	}
	public void printData(){
		for(Integer [] line : data){
			//String d = A
			System.out.println(Arrays.toString(line).replace(", ", " ").replace("[", "").replace("]",""));
			//System.out.println("----------------");
		}
		System.out.println("\n");		
	}
	public void loadFromFile(String fileName){
		try{
			Scanner scanner = new Scanner(new File(fileName));
			int i = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				line = line.replace("_", "0");
				String[] newLine = line.split(",");
				
				for(int k = 0; k < 9; k++){
					data[i][k] = Integer.parseInt(newLine[k]);	
				}
				i++;
				
			  // process the line using String#split function
			}
			//printData();
		}catch(FileNotFoundException e){
			System.out.println("Whoops");
		}	
		
	}
	public void solve(){
		while(!solved){
			subSolve();
			//break;
		}/*
		printData();
		System.out.println("Stats: ");
		System.out.println("Backtracks : " + backTracks);
		System.out.println("It took " + (System.currentTimeMillis() - startTime) + " milliseconds.");*/
		
	}
	private void subSolve(){
		solved = true;
		while(knownCount < 81){
			int count = knownCount;
			for(Coordinate coord: possibleValues.keySet()){

				possibleValues.get(coord).removeAll(getNeighbors(coord));
				possibleValues.get(coord).removeAll(impossibleValues.get(coord));
				possibleValues.get(coord).remove(0);
				if(possibleValues.get(coord).size() == 1){
					setValue(coord, false);
					solved = false;
				}else if(possibleValues.get(coord).size() == 0){
					solved = false;
					reloadState();
					break;
				}
			}
			if(count == knownCount){
				//printData();
				saveState();
				guessBest();
				break;
			}
		}
		//solved = true;
	}
	private void setValue(Coordinate coord, boolean guess){
		int x = coord.x;
		int y = coord.y;
		int val = (int) possibleValues.get(coord).toArray()[0];
		if(guess){
			HashSet<Integer> temp = possibleValues.get(coord);
			temp.removeAll(impossibleValues.get(coord));
			val = (int) temp.toArray()[0];
			possibleValues.put(coord, new HashSet<Integer>(Arrays.asList(val)));
		}
		if(data[y][x] != val){
			rows.get(y).add(val);
			columns.get(x).add(val);
			groups.get(getGroup(x, y)).add(val);
			data[y][x] = val;
			knownCount++;
		}
		
	}
	private HashSet<Integer> getNeighbors(Coordinate coord){
		HashSet<Integer> neighbors = new HashSet<Integer>();
		
		neighbors.addAll(rows.get(coord.y));
		
		neighbors.addAll(columns.get(coord.x));
		neighbors.addAll(groups.get(getGroup(coord.x, coord.y)));
		neighbors.remove(data[coord.y][coord.x]);
		return neighbors;
	}
	public static SudokuPuzzle clone(SudokuPuzzle object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (SudokuPuzzle) ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int getGroup(int x, int y){
		return ((x + 3)/3) + ((y/3) * 3);
		
	}
	private void saveState(){
		System.out.println("Saved the state");
		SudokuPuzzle state = (SudokuPuzzle) clone(this);
		state.savedStates = null;//new ArrayList<SudokuPuzzle>();//null;
		savedStates.add(state);
	}
	private void reloadState(){
		//printData();
		System.out.println("Reloading state, root " +  root + " = " + rootVal + " was wrong");
		SudokuPuzzle state = savedStates.get(savedStates.size() - 1);
		savedStates.remove(savedStates.size() - 1);
		state.impossibleValues.get(root).add(rootVal);
		state.backTracks = backTracks + 1;
		state.savedStates = savedStates;
		System.out.println("Reloaded state");
		copy(state);
		//state.solve();
		
		
	}

	private void guessBest(){
		HashMap<Coordinate, HashSet<Integer>> newValues = new HashMap<Coordinate, HashSet<Integer>>(possibleValues);
		//HashMap<Coordinate, HashSet<Integer>> newValues = (HashMap) possibleValues.clone();
		for(Coordinate coord: newValues.keySet()){
			newValues.get(coord).removeAll(impossibleValues.get(coord));
		}
		Coordinate bestCoord = (Coordinate) getMinKey(newValues);
		System.out.println(newValues.get(bestCoord));
		setValue(bestCoord, true);
		root = bestCoord;
		rootVal = data[bestCoord.y][bestCoord.x];
		System.out.println("Guessed "+  root + " = " + rootVal);
		System.out.println(knownCount);
		printData();
	}
	private static Coordinate getMinKey(HashMap<Coordinate ,HashSet<Integer>> dict){
		Coordinate minKey = new Coordinate(0, 0);
		for(Coordinate key : dict.keySet()){
			//System.out.println(key + " " + dict.get(key) + " " + minKey + " " + dict.get(minKey));
			if(((1 < dict.get(key).size()) && (dict.get(key).size() < dict.get(minKey).size())) || ( 1 == dict.get(minKey).size())){
				minKey = key;	
			}
		}
		return minKey;
		
	}
}