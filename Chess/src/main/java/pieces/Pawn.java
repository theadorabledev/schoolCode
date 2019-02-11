
public class Pawn extends Piece{
	private int direction;
	public static final String symbol = "P";
	public Pawn(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
		direction = (s == 1) ? 1 : -1;
	}
	//(theoretical ||  tryMove(c))
	public boolean isValidPlay(Coordinate c, boolean theoretical){	
		if(Math.abs(c.x - pos.x) > 1 || Math.abs(c.y - pos.y) > 2 || (Math.abs(c.y - pos.y) == 2 && c.x - pos.x != 0)) return false;
		if(!super.isValidPlaySuper(c)) return false;
		if(c.y - pos.y == direction){
			if(pos.x - c.x == 0) return game.pieceAt(pos.x, pos.y + direction) == null && (theoretical ||  tryMove(c));
			return (Math.abs(pos.x - c.y) == 1 && game.pieceAt(pos.x, pos.y + direction) != null) && (theoretical ||  tryMove(c));
		}
		if(!moved && c.x - pos.x == 0 && c.y - pos.y == 2 * direction && game.pieceAt(c) == null && game.pieceAt(pos.x, pos.y + direction) == null) return (theoretical ||  tryMove(c)); //first move
		return false;
	}
}
