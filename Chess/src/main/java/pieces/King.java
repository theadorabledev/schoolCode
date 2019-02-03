import java.util.*;
public class King extends Piece{
	public static final String symbol = "K";
	public King(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
	}
	public boolean isValidPlay(Coordinate c){
		if(!super.isValidPlay(c)) return false;
		return (Math.abs(c.x - pos.x) == 1 && Math.abs(c.y - pos.y) == 1);
	}
}