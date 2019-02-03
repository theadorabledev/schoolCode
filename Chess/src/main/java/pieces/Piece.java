import java.util.*;
public abstract class Piece{
    public String symbol;
    public Coordinate pos;
    public int side, otherSide;
    public Game game;
    public boolean moved = false;
	public int points;
    public Piece(Coordinate c, int s, String sym, Game g){
		pos = c;
		side = s;
		otherSide = Math.abs(side - 1);
		symbol = sym;
		game = g;
    }
    public boolean isValidPlay(Coordinate c){
		return isValidPlay(c.x, c.y);
	}
	public boolean isValidPlay(int x, int y){
		if(x < 0 || x > 8 || y < 0 || y > 8) return false;
		Piece pi = game.pieceAt(x, y);
		if(pi != null && pi.side == side) return false;
		Piece king = game.players[side].king;
		for(Piece p : game.players[otherSide].pieces){
			if(p.isValidPlay(king.pos)) return false;
		}			
		return true;
    }
    public void move(Coordinate c){
		pos = c;
		moved = true;
    }
	@Override
	public String toString(){
		return symbol;
		
	}
}
