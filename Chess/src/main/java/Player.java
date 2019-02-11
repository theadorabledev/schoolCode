import java.util.ArrayList;
import java.util.Arrays;

public class Player{
    public King king;
    private int side;
    public int otherSide;
    public Piece[] piecesA;
    public ArrayList<Piece> pieces;
    //public static final Player BLACK = new Player("b");
    //public static final Player WHITE = new Player("w");
    public Player(String s, Game g){
    	side = (s.equals("w")) ? 1 : 0;
    	otherSide = (side == 1) ? 0 : 1;
		if(side == 1) {
			piecesA = new Piece[] {new Pawn(new Coordinate(0, 1), side, g), new Pawn(new Coordinate(1, 1), side, g), new Pawn(new Coordinate(2, 1), side, g), new Pawn(new Coordinate(3, 1), side, g), new Pawn(new Coordinate(4, 1), side, g), new Pawn(new Coordinate(5, 1), side, g), new Pawn(new Coordinate(6, 1), side, g), new Pawn(new Coordinate(7, 1), side, g), new Rook(new Coordinate(0, 0), side, g), new Knight(new Coordinate(1, 0), side, g), new Bishop(new Coordinate(2, 0), side, g), new Queen(new Coordinate(3, 0), side, g), new King(new Coordinate(4, 0), side, g), new Bishop(new Coordinate(5, 0), side, g), new Knight(new Coordinate(6, 0), side, g), new Rook(new Coordinate(7, 0), side, g)};
		}else{
			piecesA = new Piece[] {new Pawn(new Coordinate(0, 6), side, g), new Pawn(new Coordinate(1, 6), side, g), new Pawn(new Coordinate(2, 6), side, g), new Pawn(new Coordinate(3, 6), side, g), new Pawn(new Coordinate(4, 6), side, g), new Pawn(new Coordinate(5, 6), side, g), new Pawn(new Coordinate(6, 6), side, g), new Pawn(new Coordinate(7, 6), side, g), new Rook(new Coordinate(0, 7), side, g), new Knight(new Coordinate(1, 7), side, g), new Bishop(new Coordinate(2, 7), side, g), new Queen(new Coordinate(3, 7), side, g), new King(new Coordinate(4, 7), side, g), new Bishop(new Coordinate(5, 7), side, g), new Knight(new Coordinate(6, 7), side, g), new Rook(new Coordinate(7, 7), side, g)};
		}
		pieces = new ArrayList<>(Arrays.asList(piecesA));
		king = (King) piecesA[12];
		for(Piece p : pieces) p.setKing(king);
    }

}
