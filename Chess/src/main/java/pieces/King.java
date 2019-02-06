import java.util.*;
public class King extends Piece{
	public static final String symbol = "K";
	public static final int points = 10;
	public King(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
	}
	public boolean isValidPlay(Coordinate c, boolean theoretical){
		if(!super.isValidPlaySuper(c)) return false;
		return (Math.abs(c.x - pos.x) == 1 && Math.abs(c.y - pos.y) == 1) && (theoretical ||  tryMove(c));
	}
	public boolean isInCheck(){
		for(Piece p : game.players[otherSide].pieces){
			if(p.isValidPlay(pos, true)) return true;
		}
		return false;
	}
}
