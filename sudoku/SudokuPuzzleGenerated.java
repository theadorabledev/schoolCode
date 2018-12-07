import java.util.*;
import java.io.*;
public class SudokuPuzzleGenerated extends SudokuPuzzle{
	public static final String templateFile = "templates.txt";
	public static final HashMap<String, Integer> conversion = new HashMap<String, Integer>() {{
		put("A",1); put("B",2); put("C",3); put("D",4); put("E",5); put("F",6); put("G",7); put("H",8); put("I",9);
	}};
	private ArrayList<String> conversionValues = new ArrayList<String>(Arrays.asList(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I"}));
	private String[][] subData = new String[9][9];
	private Random rand;
	public static void main(String[] args){
		SudokuPuzzleGenerated s = new SudokuPuzzleGenerated("Hard", "123");
		s.printData();
		//System.out.println(rand.seed);
		SudokuPuzzle p = new SudokuPuzzle(s.getData());
		p.solve();
		p.printData();
	}
	public SudokuPuzzleGenerated(String boardName, String seed){
		super();
		rand = new Random(Long.parseLong(seed, 36));
		loadFromFile(templateFile, boardName);
		randomShuffle();
		    
		//printData();
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
			int a = rand.nextInt(4);
			if(a == 0){
				shuffleRows();
			}else if (a == 1){
				shuffleCols();
			}else if (a == 2){
				shuffleBoxRows();
			}else{
				shuffleBoxCols();
			}
		}
		for(int x = 0; x < 9; x++){
			for(int y = 0; y < 9; y++){
				data[y][x] = values.indexOf(subData[y][x]) + 1;
			}
		}
	}
	private void shuffleRows(){
		int box = rand.nextInt(3);
		int a = rand.nextInt(3);
		int b = rand.nextInt(3);
		if(box == 0){
			a += 3;
			b += 3;
		}else if (box == 1){
			a += 6;
			b += 6;
		}
		String[] temp = subData[a];
		subData[a] = subData[b];
		subData[b] = temp;
	}
	private void shuffleCols(){
		int box = rand.nextInt(3);
		int a = rand.nextInt(3);
		int b = rand.nextInt(3);
		if(box == 0){
			a += 3;
			b += 3;
		}else if (box == 1){
			a += 6;
			b += 6;
		}
		for(int i = 0; i < 9; i++){
			String temp = subData[i][a];
			subData[i][a] = subData[i][b];
			subData[i][b] = temp;
		}
	}
	private void shuffleBoxRows(){
		int a = rand.nextInt(3) * 3;
		int b = rand.nextInt(3) * 3;
		for(int i = 0; i < 3; i++){
			String[] temp = subData[a];
			subData[a] = subData[b];
			subData[b] = temp;
			a++;
			b++;
		}
	}
	private void shuffleBoxCols(){
		int a = rand.nextInt(3) * 3;
		int b = rand.nextInt(3) * 3;
		for(int i = 0; i < 3; i++){
			String temp = subData[i][a];
			subData[i][a] = subData[i][b];
			subData[i][b] = temp;
			a++;
			b++;
		}
	}
	private ArrayList<String> shuffled(){
		
		ArrayList<String> shuffleOrder = conversionValues;
		System.out.println(shuffleOrder);
		Collections.shuffle(shuffleOrder, rand);
		System.out.println(shuffleOrder);
		return shuffleOrder;
	}
	public Integer[][] getData(){
		return data;
	}
}
