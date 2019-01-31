import java.util.*;
public abstract class Piece{
    public String symbol;
    public Coordinate pos;
    public int side, otherSide;
    public Game game;
    public boolean moved = false;
    public Piece(Coordinate c, int s, String sym, Game g){
	pos = c;
	side = s;
	otherSide = Math.abs(side - 1);
	symbol = sym;
	game = g;
    }
    public boolean isValidPlay(Coordinate c){
	Piece pi = game.pieceAt(c);
	if(pi != null && pi.side == side) return false;
        king = game.players[side].king;
	for(Piece p : game.players){
	    if(p.isValidPlay(king.pos)) return false;
	}
       
	return true;
    }
    public void move(Coordinate c){
	pos = c;
	moved = true;
    }
}
