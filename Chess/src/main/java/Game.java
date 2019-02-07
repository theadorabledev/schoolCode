import javafx.util.Pair;

import java.util.*;
public class Game{
	public static final Coordinate[] center = {new Coordinate(3, 3), new Coordinate(3, 4), new Coordinate(4, 3), new Coordinate(4, 4)};
	public static final Coordinate[] outerCenter = {};
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
		System.out.println("   0  1  2  3  4  5  6  7\n");
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
    	//turn = (turn == 1) ? 0 : 1;
	}
	public void swapTurn(){
		turn = (turn == 1) ? 0 : 1;
	}
	public String stringifyBoard(){
		String s = "";
		for(Piece[] row : board) for(Piece p : row) s += ((s != null) ? p : " ");
		return s;
	}
	public int analyzeBoard(int side) {
		int points = 0;
		for (Piece p : players[side].pieces) {
			int lSide = p.side;
			for (Piece oP : players[p.otherSide].pieces) { // Piece attacked
				if (p.isValidPlay(oP.pos, true)) points += (2 * oP.points);
			}
			p.betray(p.otherSide);
			for (Piece oP : players[side].pieces) { // Pieces defended
				if (p.isValidPlay(oP.pos, true)) points += oP.points;
			}
			p.betray(2); // Jump out so all moves are ok. Then tests defended positions.
			for (Coordinate c : center) {
				if (p.isValidPlay(c, true)) points += 2;
			}
			for (Coordinate c : outerCenter) {
				if (p.isValidPlay(c, true)) points += 1;
			}
			p.betray(lSide);
		}
		return points;
	}
	public HashMap<String, Integer> bestMoveTree(int side, int depth){
		HashMap<String, Integer> dict = new HashMap<String, Integer>();

		System.out.println(depth);
		printBoard();
		for(Piece p : players[side].pieces){
			Coordinate lastCoord = p.pos;
			boolean moved = p.moved;

			//System.out.println(p.pos + " " + p.getPossibleMoves());
			for(Coordinate c : p.getPossibleMoves()){
				Piece lastPiece = pieceAt(c);
				movePiece(p, c, false, true);
				//System.out.println(depth + " " + p + " " + p.pos);
				//printBoard();
				String move = lastCoord.x + "" + lastCoord.y + "-" + c.x + "" + c.y;
				if(depth > 1){
					HashMap<String, Integer> m = bestMoveTree(p.otherSide, depth - 1 );
					String s = (side == 1) ? Collections.max(m.entrySet(), Map.Entry.comparingByValue()).getKey() : Collections.min(m.entrySet(), Map.Entry.comparingByValue()).getKey();
					dict.put(move, m.get(s));
				}else{
					dict.put(move, analyzeBoard(1) - analyzeBoard(0));
				}
				movePiece(p, lastCoord, false, true);
				if(lastPiece != null) movePiece(lastPiece, c, true,true);
			}
			p.moved = moved;
		}
		System.out.println(depth + "\n	" + dict);
		return dict;
	}

	public Pair<Coordinate, Coordinate> getBestMove(int side, int depth){
		HashMap<String, Integer> m = bestMoveTree(side, depth);
		String s = (side == 1) ? Collections.max(m.entrySet(), Map.Entry.comparingByValue()).getKey() : Collections.min(m.entrySet(), Map.Entry.comparingByValue()).getKey();
		String[] arr = s.split(",");
		Pair<Coordinate, Coordinate> pair = new Pair<>(new Coordinate(Integer.valueOf(s.charAt(0)), Integer.valueOf(s.charAt(1))), new Coordinate(Integer.valueOf(s.charAt(3)), Integer.valueOf(s.charAt(4))));
		return pair;
	}
	public static void main(String[] args){
		Game g = new Game();
		g.printBoard();
		Pair<Coordinate, Coordinate> p = g.getBestMove(1, 5);
		g.printBoard();
		System.out.println("White : " + g.analyzeBoard(1) + "\nBlack : " + g.analyzeBoard(0));
	}
}
