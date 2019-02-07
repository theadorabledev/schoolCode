public class Queen extends Piece{
	public static final String symbol = "Q";
	public static final int points = 9;
	private Bishop bishop;
	private Rook rook;
	public Queen(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
		//bishop = new Bishop(c, s, g);
		//rook = new Rook(c, s, g);
	}
	public boolean isValidPlay(Coordinate c, boolean theoretical){
		if (!super.isValidPlaySuper(c)) return false;
		if (!(((c.x == pos.x) ^ (c.y == pos.y)) || (Math.abs(c.x - pos.x) == Math.abs(c.y - pos.y)))) return false;
		int xDir;
		int yDir;
		if (Math.abs(c.x - pos.x) == Math.abs(c.y - pos.y)) {//Bishop
			xDir = (c.x - pos.x) / Math.abs(c.x - pos.x);
			yDir = (c.y - pos.y) / Math.abs(c.y - pos.y);
		} else {//Rook
			xDir = (c.x - pos.x == 0) ? 0 : (c.x - pos.x) / Math.abs(c.x - pos.x);
			yDir = (c.y - pos.y == 0) ? 0 : (c.y - pos.y) / Math.abs(c.y - pos.y);
		}
		for (int i : new PythonRange(1, Math.max(Math.abs(c.x - pos.x), Math.abs(c.y - pos.y)))) {
			if (game.pieceAt(pos.x + (i * xDir), pos.y + (i * yDir)) != null) return false;
		}
		return (theoretical || tryMove(c));
		//return (bishop.isValidPlay(c) || rook.isValidPlay(c));
	}
	public void move(Coordinate c){
		super.move(c);
		//bishop.move(c);
		//rook.move(c);
	}
	public void betray(int s){
		side = s;
		//bishop.side = s;
		//rook.side = s;
	}
	public void setKing(King k){
	    //king = bishop.king =rook.king = k;
	    king = k;
	}

}
