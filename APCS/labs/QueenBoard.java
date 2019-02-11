import java.util.*;
public class QueenBoard{
	private int[][] board;
	public ArrayList<String> boards = new ArrayList<String>();
	
	private int size;
	public QueenBoard(int size){
		board = new int[size][size];
		this.size = size;
		
	}
	private boolean addQueen(int x, int y){
		for(int i = 0; i < size; i++){
			if(board[y][i] == 1 || board[i][x] == 1) return false;
			if(x - i >= 0 && y - i >= 0 && board[y - i][x - i] == 1) return false;
			if(x - i >= 0 && y + i < size && board[y + i][x - i] == 1) return false;
			if(x + i < size && y - i >= 0 && board[y - i][x + i] == 1) return false;
			if(x + i < size && y + i < size && board[y + i][x + i] == 1) return false;
		}
		board[y][x] = 1;
		return true;
	}
	private boolean removeQueen(int x, int y){
		if(board[y][x] == 1){
			board[y][x] = 0;
			return true;
		}
		return false;
	}
	public void printBoard(){
		for(int[] row : board){
			for(int i : row) System.out.print(" " + i);
			System.out.println();
		}
		System.out.println();
	}

	public String toString(){
		String s = "";
		for(int[] row : board){
			for(int i : row) s += " " + i;
			s += "\n";
		}
		return s;
	}


	public boolean solve(){
		//printBoard();
		boolean s = false;
		boolean t = false;
		for(int i = 0; i < size; i++){
			for(int k = 0; k < size; k++){
				if(addQueen(i, k)){
					t = true;
					s = solve();
					removeQueen(i, k);
					return s;
				}
				
			}
		}
		//printBoard();
		
		if(!t){ 
			boards.add(toString());
			return true;
		};
		//if(s) return true;
		return false;
	}
	public int countSolutions(){
		return 2;
	}
	public static void main(String[] args){
		QueenBoard q = new QueenBoard(5);
		//q.printBoard();
		q.solve();
		for(String s : q.boards) System.out.println(s);
		System.out.println(q.boards.size());
		
	}
}
