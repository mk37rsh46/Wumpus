package Pieces;
import Board.Position;
import interfaces.Color;
import interfaces.Type;

public class Wumpus extends Piece {

	public Wumpus(Color c, Position p, Type t) {
		super(c, p, t);
		// TODO Auto-generated constructor stub
	}
	public Wumpus(Piece t) {
		super(t);
	}
	public int attackres(Piece a) {
		
		Type oppType = a.type;
		if(oppType == Type.WUMPUS) {
			return 0;
		}
		if(oppType != Type.MAGE) {
			return -1;
		}
		return 1;
		
	}

}
