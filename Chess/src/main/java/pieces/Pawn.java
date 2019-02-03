public class Pawn extends Piece{
	private int direction;
	public static final String symbol = "P";
	public Pawn(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
		direction = (s == 0) ? 1 : -1;
	}
	public boolean isValidPlay(Coordinate c){	
		if(Math.abs(c.x - pos.x) > 1) return false;
		if(!super.isValidPlay(c)) return false;
		if(game.pieceAt(c.x, c.y - direction) != null){//piece in front 
			if(c.x != pos.x && game.pieceAt(c) != null) return true; //if capturing
			return false;
		}
		if(c.y - pos.y == direction * 2 && game.pieceAt(c) == null && !moved) return true; //first move
		return false;
	}
}