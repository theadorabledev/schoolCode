import java.util.*;
public class Bishop extends Piece{
	public static final String symbol = "B";
	public Bishop(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
	}
	public boolean isValidPlay(Coordinate c){
		if(!super.isValidPlay(c) || Math.abs(c.x - pos.x) != Math.abs(c.y - pos.y)) return false;
		int xDir = (c.x - pos.x) / Math.abs(c.x - pos.x);
		int yDir = (c.y - pos.y) / Math.abs(c.y - pos.y);
		for(int i : new PythonRange(1, Math.abs(c.x - pos.x))){
			if(game.pieceAt(pos.x + (i * xDir), pos.y + (i * yDir)) != null) return false;
		}
		return true;
	}

}