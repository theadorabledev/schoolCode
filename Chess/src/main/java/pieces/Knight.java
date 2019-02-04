import java.util.*;
public class Knight extends Piece{
	public static final String symbol = "N";
	public Knight(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
	}
	public boolean isValidPlay(Coordinate c, boolean theoretical){
		if(!super.isValidPlaySuper(c)) return false;
		if((Math.abs(c.x - pos.x) == 1 && Math.abs(c.y - pos.y) == 2) || (Math.abs(c.x - pos.x) == 2 && Math.abs(c.y - pos.y) == 1)) return (theoretical ||  tryMove(c));
		return false;
	}

}