package Pieces;
import Board.Position;
import interfaces.Color;
import interfaces.Type;

public class Hero extends Piece{

	public Hero(Color c, Position p, Type t) {
		super(c, p, t);
		// TODO Auto-generated constructor stub
	}
	
	public Hero(Piece piece) {
		super(piece);
	}

	public int attackres(Piece a) {
		
		Type oppType = a.type;
		if(oppType == Type.HERO) {
			return 0;
		}
		if(oppType != Type.WUMPUS) {
			return -1;
		}
		return 1;
		
	}

}
