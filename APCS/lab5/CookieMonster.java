/*

3, Write a program in which Cookie Monster finds the optimal path from 
the upper-left corner(0,0) to the lower-right corner (length - 1, length -1)
in a cookie-grid (a 2-D array). Each element of the grid contains either
some number of cookies (a non-negative integer) or a barrel(-1). On 
each step Cookie Monster can only go down or to the right. The monster
is not allowed to step on barrels. The optimal path contains
the largest number of cookies.

Hints: Use a stack. If there is only one way to proceed from the current
position, then go there and update the total accumulated number of cookies.
If there are two ways to proceed, save one of the possible two points
(and its total) on the stack and proceed to the other point. If you 
have reached the lower-right corner, update the maximum. If there is 
nowhere to go, examine the stack: pop a saved point, if any, and 
resume from there.
*/


// How to run : $java  CookieMonster cookies.txt 12
// cookies.txt has entries of a 12x12 board.

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.IOException;

public class CookieMonster{



	public static void main(String [] args){
		String fileIn = args[0]; // file name of the data
		final int N = Integer.parseInt(args[1]); // dimensions of the NxN board
		int [][] board = new int[N][N];
		getBoard(fileIn, board);
		print(board); 
		bestPath(board);		
		
	}

	// preconditon: rd is open for reading
	//             
	// postconditon: concatenates the files read from rd1 and rd2 into the file
	//               associated with wr.
	//               All Readers and Writers are closed.
	private static void  getBoard(String fileIn, int [][] board){
		try{
			final int N = board.length;
			int i = 0, j = 0;
			BufferedReader rd = openFileReader(fileIn);
			while(true){
				String line = rd.readLine();
				if (line == null) break;
				Scanner sys = new Scanner(line);
				while (sys.hasNext()){
					board[i][j] = sys.nextInt();
					if (j < N-1) j++;
					else {
						j = 0;
						i++;
					}
				}
			}
			rd.close();
		}catch(IOException ex){
			throw new RuntimeException(ex.toString());
		}
	}

	private static BufferedReader openFileReader(String file){
		try{
			return new BufferedReader(new FileReader(file)); 

		}catch(IOException ex){
			throw new RuntimeException(ex.toString());
		}
	}

	private static void print(int[][] board){
		final int N = board.length;
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				System.out.printf("%3d", board[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void bestPath(int[] [] board){
		System.out.println(explore(board, 0, 0, 0));
	}
	private static int explore(int[][] board, int x, int y, int total){
		int right = 0;
		int bottom = 0;
		if(x == board.length -1 && y == board.length -1) return total;
		if(x < board.length - 1 && board[y][x + 1] != -1){
			right = explore(board, x + 1, y, total + board[y][x + 1]);
		}
		if(y < board.length - 1 && board[y + 1][x] != -1){
			bottom = explore(board, x, y + 1, total + board[y + 1][x]);
		}
		return Math.max(right, bottom); 
	}
}
