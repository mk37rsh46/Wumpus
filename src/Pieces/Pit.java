package Pieces;
import java.util.ArrayList;

import Board.Board;
import Board.Position;
import interfaces.Color;
import interfaces.Type;

public class Pit extends Piece {

	public Pit(Color c, Position p, Type t) {
		super(c, p, t);
		// TODO Auto-generated constructor stub
	}
	public Pit(Piece t) {
		super(t);
	}
	public Position[] moves(int size, Board b) {
		return null;
	}
}


