package Pieces;
import java.util.ArrayList;

import Board.Board;
import Board.Position;
import interfaces.Color;
import interfaces.Type;

public class Piece {
	public Color color;
	public Position position;
	public Type type;
	
	public Piece(Color c, Position p, Type t) {
		this.type=t;
		this.position =p;
		this.color = c;
	}
	
	public Piece(Piece p) {
		this.type = p.type;
		this.position = p.position;
		this.color = p.color;
	}
	
	
	public Position[] moves(int size, Board b) {
		int[][] directions = new int[][] {{-1,-1}, {-1,0}, {-1,1},  {0,1}, {1,1},  {1,0},  {1,-1},  {0, -1}};
		ArrayList<Position> pos = new ArrayList<Position>();
		for (int[] direction : directions) {
			 int col = position.col + direction[0];
		     int row = position.row + direction[1];
		     if((row >= 0 && row < size)) {
		    	 if(col >= 0 && col < size) {
		    		 if(b.board[row][col] == null) {
		    			 pos.add(new Position(row,col));
		    		 }
		    		 else if ( !(b.board[row][col].color == color) ) {
		    			 pos.add(new Position(row,col));
		    		 }
		    	 }
		    }
		}
		Position[] moves = new Position[pos.size()];
		for(int x = 0; x < moves.length; x++) {
			moves[x] = pos.get(x);
		}
		return moves;
	}

	
	public int attackres(Piece a) {
		return 0;		
	}
	
	public Type gettype() {
		return type;
	}
	public void move(Position p) {
		this.position = p;
	}

	public String typeToString() {
		if(type == type.WUMPUS) {
			return "W";
		}
		if(type == type.HERO) {
			return "H";
		}
		if(type == type.MAGE) {
			return "M";
		}

			return "P";
		
	}
	
	public String ColorToString() {
		if(color == Color.WHITE) {
			return "W";
		}
		if(color == Color.BLACK) {
			return "B";
		}
		return "P";
	}

	@Override
	public String toString() {
			return ColorToString() + typeToString() ;
	
		
	}
	
	
}
