import java.util.*;
public class Game{
	private Piece[][] board = new Piece[8][8];
    public Player[] players = new Player[] {new Player("b", this), new Player("w",this)};
    public int turn = 1;
    public static final ArrayList<Coordinate> allCoords = new ArrayList<Coordinate>(){{
    	for(int i = 0; i < 8;i ++) for (int k = 0; k < 8; k++) add(new Coordinate(i, k));
	}};

    public Game(){
		for(Player p : players){
			for(Piece piece : p.pieces){
				board[piece.pos.y][piece.pos.x] = piece;
				piece.king = p.king;
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
		int i = 0;
		for(Piece[] row : board){
			System.out.print(i + " ");
			for(Piece p : row){
				System.out.print("[" + String.valueOf(p).replace("null", " ") + "]");
			}
			System.out.println();
			i++;
		}
		System.out.println("   0  1  2  3  4  5  6  7");
		//System.out.println("   A  B  C  D  E  F  G  H");
	}
	public void movePiece(Piece p, Coordinate c){movePiece(p, c, false);}
	public void movePiece(Piece p, Coordinate c, boolean t){movePiece(p, c, t, false);}
	public void movePiece(Piece p, Coordinate c, boolean theoretical, boolean fixit){
    	if(fixit || p.isValidPlay(c, theoretical)){
    		int x = p.pos.x;
    		int y = p.pos.y;
    		p.move(c);
    		board[y][x] = null;
    		board[c.y][c.x] = p;


		}
	}
	public static void main(String[] args){
		Game g = new Game();
		g.printBoard();
		for(Piece p : g.players[1].pieces){
			System.out.println(p + " " + p.pos + " "  + p.getPossibleMoves());
			//g.printBoard();
		}
		System.out.println("\n");
		for(Piece p : g.players[0].pieces){
			System.out.println(p + " " + p.pos + " "  + p.getPossibleMoves());
			///g.printBoard();
		}
	}
}
