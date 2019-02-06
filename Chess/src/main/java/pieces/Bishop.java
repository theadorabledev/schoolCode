import java.util.*;
public class Bishop extends Piece{
	public static final String symbol = "B";
	public static final int points = 3;
	public Bishop(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
	}
	public boolean isValidPlay(Coordinate c, boolean theoretical){
		if(!super.isValidPlaySuper(c) || Math.abs(c.x - pos.x) != Math.abs(c.y - pos.y)) return false;
		int xDir = (c.x - pos.x) / Math.abs(c.x - pos.x);
		int yDir = (c.y - pos.y) / Math.abs(c.y - pos.y);
		for(int i : new PythonRange(1, Math.abs(c.x - pos.x))){
			if(game.pieceAt(pos.x + (i * xDir), pos.y + (i * yDir)) != null) return false;
		}
		return (theoretical ||  tryMove(c));
	}

}