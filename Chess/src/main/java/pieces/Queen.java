public class Queen extends Piece{
	public static final String symbol = "Q";
	public static final int points = 9;
	private Bishop bishop;
	private Rook rook;
	public Queen(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
		bishop = new Bishop(c, s, g);
		rook = new Rook(c, s, g);
	}
	public boolean isValidPlay(Coordinate c, boolean theoretical){
		if(!super.isValidPlaySuper(c)) return false;
		return (bishop.isValidPlay(c) || rook.isValidPlay(c));
	}
	public void move(Coordinate c){
		super.move(c);
		bishop.move(c);
		rook.move(c);
	}
	public void betray(int s){
		side = s;
		bishop.side = s;
		rook.side = s;
	}
	public void setKing(King k){
		king = bishop.king =rook.king = k;
	}

}