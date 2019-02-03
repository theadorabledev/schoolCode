import java.util.*;
public class Queen extends Piece{
	public static final String symbol = "!";
	private Bishop bishop;
	private Rook rook;
	public Queen(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
		bishop = new Bishop(c, s, g);
		rook = new Rook(c, s, g);
	}
	public boolean isValidPlay(Coordinate c){
		if(!super.isValidPlay(c)) return false;
		return (bishop.isValidPlay(c) || rook.isValidPlay(c));
	}
	public void move(Coordinate c){
		super.move(c);
		bishop.move(c);
		rook.move(c);
	}

}