import java.util.*;
public class Game{
	private Piece[][] board = new Piece[8][8];
    public Player[] players = {new Player("w"), new Player("b")};
    public Game(){
		for(Player p : players){
			for(Piece piece : p.pieces){
				board[piece.pos.y][piece.pos.x] = piece;
			}
		}
	}
	public Piece pieceAt(Coordinate c){
		return board[c.y][c.x];
    }
	public Piece pieceAt(int x, int y){
		return board[y][x];
	}
	public void printBoard(){
		int i = 8;
		for(Piece[] row : board){
			System.out.print(i + " ");
			for(Piece p : row){
				System.out.print("[" + String.valueOf(p).replace("null", " ") + "]");
			}
			System.out.println();
			i--;
		}
		System.out.println("   A  B  C  D  E  F  G  H");
	}
	public static void main(String[] args){
		Game g = new Game();
		g.printBoard();
	}
}
