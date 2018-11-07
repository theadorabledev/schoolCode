import java.util.*; 
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
public class wordSearch{
	public int height;
	public int width;
	public String[][] data;
	public String[][] answer;
	public ArrayList<String> words = new ArrayList<String>();
	Random rand;
	public final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public wordSearch(String wordFile, int width1, int height1){
		height = height1;
		width = width1;
		data = new String[height][width];
		answer = new String[height][width];
		for(String[] row : data){
			Arrays.fill(row, "0");
		}
		for(String[] row : answer){
			Arrays.fill(row, ".");
		}
		rand = new Random((width * height) + height + width);
		//System.out.println(Math.abs(rand.nextInt()));
        try {
            BufferedReader in = new BufferedReader(new FileReader(wordFile));
			String str;
			while((str = in.readLine()) != null){
				words.add(str);
			}   
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();                
        }
        catch(IOException ex) {
            ex.printStackTrace();                
        }
		
		setupWords();
		populateRandomLetters();
	}
	public static void main(String [] args){
		// java wordSearch words.txt output.txt width height
		String x = args[0];
		int y = Integer.valueOf(args[2]);
		int z = Integer.valueOf(args[3]);
		wordSearch w = new wordSearch(args[0], Integer.valueOf(args[2]), Integer.valueOf(args[3]));
		//w.printSearch();
		//w.printAnswers();
		try{
			w.writeSearch(args[1]);
			w.writeAnswer(args[1].substring(0, args[1].length() - 4) + "-answer.txt");
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("Word-Search printed to : "+ args[1]);
		System.out.println("Answer printed to : " + args[1].substring(0, args[1].length() - 4) + "-answer.txt");
	}
	public boolean okayOp(boolean right, String word, int x, int y){
		if(right){
			for(int i = 0; i < word.length(); i++){
				if(!( data[y][x + i] != String.valueOf(word.charAt(i)) || data[y][x + i] != "0")){
					return false;
				}
			} 
		}else{
			for(int i = 0; i < word.length(); i++){
				if(!( data[y + i][x] != String.valueOf(word.charAt(i)) || data[y + i][x] != "0")){
					return false;
				}
			}			
		}
		return true;
	}
	public void setupWords(){
		for(String word: words){
			boolean right = Math.abs(rand.nextInt()) % 2 == 0;
			boolean worked = false;
			while(!worked){	
				if(right){
					int x = Math.abs(rand.nextInt()) % (width - 1 - word.length());
					int y = Math.abs(rand.nextInt()) % (height - 1);
					if (okayOp(right, word, x, y)){
						for(int i = 0; i < word.length(); i++){
							data[y][x + i] = String.valueOf(Character.toUpperCase(word.charAt(i)));
							answer[y][x + i] = String.valueOf(Character.toUpperCase(word.charAt(i)));
						}
						worked = true;
					}
				}else{
					int x = Math.abs(rand.nextInt()) % (width - 1);
					int y = Math.abs(rand.nextInt()) % (height - 1 - word.length());
					if (okayOp(right, word, x, y)){
						for(int i = 0; i < word.length(); i++){
							data[y + i][x] = String.valueOf(Character.toUpperCase(word.charAt(i)));
							answer[y + i][x] = String.valueOf(Character.toUpperCase(word.charAt(i)));
						}		
						worked = true;
					}					
					
				}
			}
		}	
	}
	public void populateRandomLetters(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(data[y][x] == "0"){
					data[y][x] = String.valueOf(ALPHABET.charAt(Math.abs(rand.nextInt()) % 26));
				}
			}
		}
	}
	public void printAnswers(){
		System.out.println("\nAnswer\n");
		for(String[] row : answer){
			System.out.println(String.join(" ", row));
		}		
	}
	public void printSearch(){
		System.out.println("\nWord Search\n");
		for(String[] row : data){
			System.out.println(String.join(" ", row));
		}
		System.out.println("\nPlease find the following words:\n");
		for(String word: words){
			System.out.println(word.toUpperCase( ) + "\n");
		}
	}
	public void writeAnswer(String file) throws IOException {
		FileWriter fw = new FileWriter(file);
		for (String[] row: answer) {
			fw.write(String.join(" ", row) + "\n");
		}
		fw.close();
	}
	public void writeSearch(String file)throws IOException {
		FileWriter fw = new FileWriter(file);
		for (String[] row: data) {
			fw.write(String.join(" ", row) + "\n");
		}
		fw.write("\nPlease find the following words:\n");
		for(String word: words){
			fw.write(word.toUpperCase( ) + "\n");
		}
		fw.close();
	}
	
}