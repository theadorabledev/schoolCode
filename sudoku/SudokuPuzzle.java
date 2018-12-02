import java.util.*;
import java.io.*;
public class SudokuPuzzle implements Serializable{
	public static final HashMap<Integer, HashSet<Coordinate>> groupCoords =  getCoords("groups");
	public static final HashMap<Integer, HashSet<Coordinate>> rowCoords =  getCoords("rows");
	public static final HashMap<Integer, HashSet<Coordinate>> columnCoords =  getCoords("columns");
	private static final List<Integer> defaultValues = Arrays.asList(1,2,3,4,5,6,7,8,9);
	private final boolean debug = false;
	
	private Integer[][] data = new Integer[9][9];
	private boolean solved = false;
	private HashMap<Coordinate, HashSet<Integer>> possibleValues = new HashMap<Coordinate, HashSet<Integer>>();
	private HashMap<Coordinate, HashSet<Integer>> impossibleValues = new HashMap<Coordinate, HashSet<Integer>>();
	private HashMap<Integer, HashSet<Integer>> groups = new HashMap<Integer, HashSet<Integer>>();
	private HashMap<Integer, HashSet<Integer>> rows = new HashMap<Integer, HashSet<Integer>>();
	private HashMap<Integer, HashSet<Integer>> columns = new HashMap<Integer, HashSet<Integer>>();
	private ArrayList<SudokuPuzzle> savedStates = new ArrayList<SudokuPuzzle>();
	private long startTime = System.currentTimeMillis();
	private int knownCount = 0;
	private int backTracks = 0;
	private int rootVal;
	private Coordinate root = null;
	
	public SudokuPuzzle(String fileName, String boardName){
		loadFromFile(fileName, boardName);
		init();
	}
	public static void main(String[] args){
		if (args.length == 0){
			System.out.println("Get serious.");
		}else{
			String fileName = args[0];
			String name = null;
			if(args.length > 1){
				name = args[1];
			}
			SudokuPuzzle s = new SudokuPuzzle(fileName, name);
			s.solve();
			if(args.length > 1){
				System.out.println("\n" + args[1].replace("solved", "unsolved") + "\n");
			}
			if(args.length > 2){
				s.printDataFancy();
			}else{
				s.printData();
			}
			System.out.println("Stats: ");
			System.out.println("	Backtracks : " + s.backTracks);
			System.out.println("	It took " + (System.currentTimeMillis() - s.startTime) + " milliseconds.");
		}
	}
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
		return (((x + 3)/3) + ((y/3) * 3)) - 1;
	}
	private static Coordinate getMinKey(HashMap<Coordinate ,HashSet<Integer>> dict){
		Coordinate minKey = new Coordinate(0, 0);
		for(Coordinate key : dict.keySet()){
			if(((1 < dict.get(key).size()) && (dict.get(key).size() < dict.get(minKey).size())) || ( 1 == dict.get(minKey).size())){
				minKey = key;	
			}
		}
		return minKey;	
	}
	private int getKnownCount(){
		int kk = 0;
		for(int x = 0; x < 9; x ++){
			for(int y = 0; y < 9; y ++){
				if(data[y][x] != 0){
					kk ++;
				}
			}
		}
		return kk;
	}
	private HashSet<Integer> getNeighbors(Coordinate coord){
		HashSet<Integer> neighbors = new HashSet<Integer>();
		neighbors.addAll(rows.get(coord.y));
		neighbors.addAll(columns.get(coord.x));
		neighbors.addAll(groups.get(getGroup(coord.x, coord.y)));
		neighbors.remove(data[coord.y][coord.x]);
		return neighbors;
	}
	
	private boolean hiddenSub(HashMap<Integer, HashSet<Coordinate>> coords){
		boolean change = false;
		ArrayList<ArrayList<HashSet<Integer>>> nakedGroups = new ArrayList<ArrayList<HashSet<Integer>>>();
		for(Integer key : coords.keySet()){
			nakedGroups.add(new ArrayList<HashSet<Integer>>());
			for(Coordinate coord: coords.get(key)){
				nakedGroups.get(nakedGroups.size() - 1).add(possibleValues.get(coord));
			}
		}
		for(int i = 0; i < 9; i ++){
			for(int x = 0; x < 9; x++){
				HashSet<Integer> curSet = nakedGroups.get(i).get(x);
				HashSet<HashSet<Integer>> possibleGroups = new HashSet<HashSet<Integer>>();
				possibleGroups.add(curSet);
				if(1 < curSet.size() && curSet.size() <= 3){
					for(int p = 1; p < 10; p++){
						HashSet<Integer> temp = new HashSet<Integer>();
						temp.addAll(curSet);
						temp.add(p);
						possibleGroups.add(temp);
						for(int q = 1; q < 10; q++){
							HashSet<Integer> temp1 = new HashSet<Integer>();
							temp1.addAll(curSet);
							temp1.add(p);
							temp1.add(q);
							possibleGroups.add(temp1);
						}
					}
				}
				for(HashSet<Integer> pGroup: possibleGroups){
					int groupCount = 0;
					for(HashSet<Integer> group: nakedGroups.get(i)){
						if( possibleGroups.size() > 1 && pGroup.containsAll(group)){
							groupCount ++;
						}
					}
					if(groupCount == pGroup.size()){
						for(Coordinate coord : coords.get(i)){
							if(!(pGroup.containsAll(possibleValues.get(coord))) && !(impossibleValues.get(coord).containsAll(pGroup))){
								impossibleValues.get(coord).addAll(pGroup);
								change = true;
							}
						}
					}
				}
			}
		}
		return change;
	}
	private boolean forbiddenFruit(){
		boolean row = hiddenSub(rowCoords);
		boolean col = hiddenSub(columnCoords);
		boolean box = hiddenSub(groupCoords);
		return row || col || box;
	}
	
	private void copy(SudokuPuzzle other){
		this.data = other.data;
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
		this.solved = other.solved;
	}

	private void init(){
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9; x++){
				possibleValues.put(new Coordinate(x, y), new HashSet<Integer>(defaultValues));//defaultValues));
				impossibleValues.put(new Coordinate(x, y), new HashSet<Integer>());
			}
			groups.put(y, new HashSet<Integer>(defaultValues));
			rows.put(y, new HashSet<Integer>(defaultValues));
			columns.put(y, new HashSet<Integer>(defaultValues));
			for(int x = 0; x < 9; x++){
				if(data[y][x] != 0){
					knownCount ++;
				}
			}
		}
		for(int i = 0; i < 9; i++){
			HashSet<Integer> rowValues = new HashSet<Integer>();
			HashSet<Integer> colValues = new HashSet<Integer>();
			HashSet<Integer> boxValues = new HashSet<Integer>();
			for(Coordinate coord : groupCoords.get(i)){
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
			groups.put(i, boxValues);
			rows.put(i, rowValues);
			columns.put(i, colValues);
			
		}		
	}
	
	public void solve(){
		while(!solved){
			subSolve();
		}

		
	}
	private void subSolve(){
		solved = true;
		while(getKnownCount() < 81){
			int count = knownCount;
			boolean nakedNums = forbiddenFruit();
			for(Coordinate coord: possibleValues.keySet()){

				possibleValues.get(coord).removeAll(getNeighbors(coord));
				possibleValues.get(coord).removeAll(impossibleValues.get(coord));
				possibleValues.get(coord).remove(0);
				if(data[coord.y][coord.x] == 0 && possibleValues.get(coord).size() == 1){
					print(coord + " must be " + possibleValues.get(coord));
					print("	Neighbors are: " + getNeighbors(coord));
					print("	Impossible Values are: " + impossibleValues.get(coord));
					
					setValue(coord, false);
					
					solved = false;
				}else if(possibleValues.get(coord).size() == 0){
					solved = false;
					reloadState();
					break;
				}
			}
			if((count == knownCount) && !nakedNums){
				//solved = false;
				saveState();
				guessBest();
				solved = false;
				break;
			}
		}
	}
	
	public void printData(){
		for(Integer [] line : data){
				System.out.println(Arrays.toString(line).replace(", ", " ").replace("[", "").replace("]",""));
			}
			System.out.println("\n");		
		}
	public void printData(int x){
		if(!debug){
			return;
		}
		int i = 0;
		print("  0 1 2 3 4 5 6 7 8 \n ------------------");
		
		for(Integer [] line : data){
			System.out.println(i + "|" + Arrays.toString(line).replace(", ", " ").replace("[", "").replace("]","").replace("0", "_"));
			i++;
			
		}
		System.out.println("\n");		
	}
	public void printDataFancy(){
		int i = 0;
		for(Integer [] line : data){
			if(i % 3 == 0 && i != 0){
				System.out.println(" ------+-------+------");
			}
			String l = Arrays.toString(line).replace(", ", " ").replace("[", "").replace("]","");
			//"0 1 2 3 4 5 6 7 8"
			System.out.println(" " + l.substring(0,6) + "|" + l.substring(5,12) + "|" + l.substring(11,17));
			i++;
		}
		System.out.println("\n");		
	}
	private void print(String x){
		if(debug){
			System.out.println(x);
		}
	}
	
	public void loadFromFile(String fileName, String boardName){
		try{
			Scanner scanner = new Scanner(new File(fileName));
			int i = 0;
			if(boardName == null){
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
			}else{
				boolean found = false;
				while (scanner.hasNextLine() && i < 9) {
					String line = scanner.nextLine();
					if(found){
						line = line.replace("_", "0");
						String[] newLine = line.split(",");
						for(int k = 0; k < 9; k++){
							data[i][k] = Integer.parseInt(newLine[k]);	
						}
						i++;
					}
					if (line.equals(boardName)){
						found = true;
					}
				}
			}
		}catch(FileNotFoundException e){
			System.out.println("Whoops! ");
		}	
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
	
	private void saveState(){
		print("Saving state.");
		SudokuPuzzle state = (SudokuPuzzle) clone(this);
		state.savedStates = null;//new ArrayList<SudokuPuzzle>();//null;
		savedStates.add(state);
	}
	private void reloadState(){
		print("RELOADING STATE.");
		print("	Bad root : "+root+ " as " + rootVal + "\n\n");
		SudokuPuzzle state = savedStates.get(savedStates.size() - 1);
		
		savedStates.remove(savedStates.size() - 1);
		state.impossibleValues.get(root).add(rootVal);
		impossibleValues.get(root).add(rootVal);
		state.backTracks = backTracks + 1;
		state.savedStates = savedStates;
		copy(state);
		printData(1);
		
	}
	private void guessBest(){
		HashMap<Coordinate, HashSet<Integer>> newValues = new HashMap<Coordinate, HashSet<Integer>>(possibleValues);
		//HashMap<Coordinate, HashSet<Integer>> newValues = (HashMap) possibleValues.clone();
		for(Coordinate coord: newValues.keySet()){
			newValues.get(coord).removeAll(impossibleValues.get(coord));
		}
		Coordinate bestCoord = (Coordinate) getMinKey(newValues);
		print("Guessing "+ bestCoord + " is ");
		print("	Possible: " + possibleValues.get(bestCoord));
		print("	Impossible: " + impossibleValues.get(bestCoord));
		print("	New: "+ newValues.get(bestCoord));
		setValue(bestCoord, true);
		printData(1);
		root = bestCoord;
		rootVal = data[bestCoord.y][bestCoord.x];

	}
	
}