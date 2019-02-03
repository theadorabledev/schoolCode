import java.util.*;
public class Rook extends Piece{
	public static final String symbol = "R";
	public Rook(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
	}
	public boolean isValidPlay(Coordinate c){
		if(!super.isValidPlay(c)) return false;
		int xDir = (c.x - pos.x == 0) ? 0 : (c.x - pos.x) / Math.abs(c.x - pos.x);
		int yDir = (c.y - pos.y == 0) ? 0 : (c.y - pos.y) / Math.abs(c.y - pos.y);
		for(int i : new PythonRange(1, Math.max(xDir, yDir))){
			if(game.pieceAt(pos.x + (i * xDir), pos.y + (i * yDir)) != null) return false;
		}
		return true;
	}

}