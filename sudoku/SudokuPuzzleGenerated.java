import java.util.*;
import java.io.*;
public class SudokuPuzzleGenerated extends SudokuPuzzle{
	public static final String templateFile = "templates.txt";
	public static final HashMap<String, Integer> conversion = new HashMap<String, Integer>() {{
		put("A",1); put("B",2); put("C",3); put("D",4); put("E",5); put("F",6); put("G",7); put("H",8); put("I",9);
	}};
	public static final ArrayList<String> conversionValues = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I"));
	private String[][] subData = new String[9][9];
	private Random rand;
	public static void main(String[] args){
		SudokuPuzzleGenerated s = new SudokuPuzzleGenerated("Hard");
	}
	public SudokuPuzzleGenerated(String boardName){
		super();
		rand = new Random();
		loadFromFile(templateFile, "Hard");
		randomShuffle();
		printData();
	}
	public void loadFromFile(String fileName, String boardName){
		try{
			Scanner scanner = new Scanner(new File(fileName));
			int i = 0;
			boolean found = false;
			while (scanner.hasNextLine() && i < 9) {
				String line = scanner.nextLine();
				if(found){
					line = line.replace("_", "0");
					String[] newLine = line.split(",");
					for(int k = 0; k < 9; k++){
						subData[i][k] = newLine[k];	
					}
					i++;
				}
				if (line.equals(boardName)){
					found = true;
				}
			}
			
		}catch(FileNotFoundException e){
			System.out.println("Whoops! ");
		}	
	}
	private void randomShuffle(){
		ArrayList<String> values = shuffled();
		//String values = new String(shuffled());
		for(int i = 0; i < 10; i++){
			int a = rand.nextInt(9);
			if(a % 2 == 0){
				shuffleRows();
			}else{
				shuffleCols();
			}
		}
		for(int x = 0; x < 9; x++){
			for(int y = 0; y < 9; y++){
				data[y][x] = values.indexOf(subData[y][x]) + 1;
			}
		}
	}
	private void shuffleRows(){
		int a = rand.nextInt(9);
		int b = rand.nextInt(9);
		String[] temp = subData[a];
		subData[a] = subData[b];
		subData[b] = temp;
	}
	private void shuffleCols(){
		int a = rand.nextInt(9);
		int b = rand.nextInt(9);
		for(int i = 0; i < 9; i++){
			String temp = subData[i][a];
			subData[i][a] = subData[i][b];
			subData[i][b] = temp;
		}
	}
	private ArrayList<String> shuffled(){
		ArrayList<String> shuffleOrder = conversionValues;
		Collections.shuffle(shuffleOrder, rand);
		return shuffleOrder;
	}

}