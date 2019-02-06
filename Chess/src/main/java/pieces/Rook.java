public class Rook extends Piece{
	public static final String symbol = "R";
	public static final int points = 5;
	public Rook(Coordinate c, int s, Game g){
		super(c, s, symbol, g);
	}
	public boolean isValidPlay(Coordinate c, boolean theoretical){
		if(!super.isValidPlaySuper(c)) return false;
		if(c.x != pos.x && c.y != pos.y) return false;
		int xDir = (c.x - pos.x == 0) ? 0 : (c.x - pos.x) / Math.abs(c.x - pos.x);
		int yDir = (c.y - pos.y == 0) ? 0 : (c.y - pos.y) / Math.abs(c.y - pos.y);
		for(int i : new PythonRange(1, Math.max(Math.abs(c.x - pos.x), Math.abs(c.y - pos.y)))){
			if(game.pieceAt(pos.x + (i * xDir), pos.y + (i * yDir)) != null) return false;
		}
		//if(pos.x == 3 && c.x == 7) System.out.println(xDir + " " + pos);
		//System.out.println(game.pieceAt(6, 0));
		//game.printBoard();
		return (theoretical ||  tryMove(c));
	}

}