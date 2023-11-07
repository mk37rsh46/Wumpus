package Pieces;
import Board.Position;
import interfaces.Color;
import interfaces.Type;

public class Mage extends Piece {

	public Mage(Color c, Position p, Type t) {
		super(c, p, t);
		// TODO Auto-generated constructor stub
	}
	public Mage(Piece p) {
		super(p);
	}
		public int attackres(Piece a) {
		
		Type oppType = a.type;
		if(oppType == Type.MAGE) {
			return 0;
		}
		if(oppType != Type.HERO) {
			return -1;
		}
		return 1;
		
	}

}
