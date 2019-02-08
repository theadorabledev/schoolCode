import java.util.*;
import java.util.stream.*;

public abstract class Piece{
    public String symbol;
    public Coordinate pos;
    public int side, otherSide;
    public Game game;
    public boolean moved = false;
    public King king;
	public int points;
    public Piece(Coordinate c, int s, String sym, Game g){
		pos = c;
		side = s;
		otherSide = Math.abs(side - 1);
		symbol = sym;
		game = g;
		//System.out.println(g.players);
		//king = g.players[side].king;
    }
    public abstract boolean isValidPlay(Coordinate c, boolean theoretical );
    public boolean isValidPlay(Coordinate c){
    	return isValidPlay(c, false);
	}
    public boolean isValidPlaySuper(Coordinate c){
		return isValidPlaySuper(c.x, c.y);
	}
	public boolean isValidPlaySuper(int x, int y){
		if(x < 0 || x > 8 || y < 0 || y > 8) return false;
		if(x == pos.x && y == pos.y) return false;
		Piece pi = game.pieceAt(x, y);
		if(pi != null && pi.side == side) return false;
		return true;
    }
    public boolean tryMove(Coordinate c){
    	Coordinate old = new Coordinate(pos.x, pos.y);
    	boolean m = moved;
    	Piece p = game.pieceAt(c);
    	game.movePiece(this, c, true);
    	//King king = game.players[side].king;
    	if (king.isInCheck()){
    		game.movePiece(this, old, true, true);
    		moved = m;
    		return false;
		}
		game.movePiece(this, old, true, true);
    	moved = m;
    	if(p != null )game.movePiece(p, c, true, true);
    	return true;
	}
    public void move(Coordinate c){
		pos = c;
		moved = true;
    }
	public ArrayList<Coordinate> getPossibleMoves(){
    	return game.allCoords.stream().filter(c -> isValidPlay(c)).collect(Collectors.toCollection(() -> new ArrayList<Coordinate>()));
	}
    @Override
	public String toString(){
		return (side == 1) ? symbol : symbol.toLowerCase();
	}
	public void betray(int s){
    	side = s;
	}
	public void setKing(King k){
    	king = k;
	}
}
