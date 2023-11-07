package minimax;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import Board.Board;
import Board.Position;
import Pieces.Piece;
import interfaces.Color;
import interfaces.Type;

public class Minimax {
public Board b;
public int depth;
public int alpha;
public int beta;
public boolean maximizingPlayer;
boolean nomove;
public Minimax(Board b, int depth, int alpha, int beta, boolean maximizingPlayer) {
	super();
	this.b = b;
	this.depth = depth;
	this.alpha = alpha;
	this.beta = beta;
	this.maximizingPlayer = maximizingPlayer;
	nomove = false;
}
public Minimax(Board b, int depth, boolean maximizingPlayer) {
	this.alpha = 0;
	this.beta = 0;
	this.b = b;
	this.depth = depth;
	this.maximizingPlayer = maximizingPlayer;
	nomove = false;
}

public Board regminimax(Board board, int depth, float a, float b, boolean maximizingPlayer) {
		if(depth == 0 || board.gameOver()) {
	
			if(maximizingPlayer) {
			board.currentVal=	eval(board,Color.WHITE);
			}
			else {
				board.currentVal = 	eval(board,Color.BLACK);
			}
		
			return board;
		}
		Board boardReturned = new Board(board);
		
		if(maximizingPlayer) {
			boardReturned.currentVal = Integer.MIN_VALUE;
			Piece[] pieces = allPieces(board.board, Color.BLACK);
			
			for(Piece piece : pieces) {
				Position[] p = piece.moves(board.size, board);
				for(Position pos : p) {
					Board newBoard = new Board(board);
					newBoard.boardMove(piece.position, pos);
					Board oneMove = new Board(newBoard);
					newBoard= regminimax(newBoard, depth -1, a, b, false);
					// System.out.println("newboard evaluation: " + newBoard.currentVal);
					float d = boardReturned.currentVal;
					// System.out.println("oldboard evaluation: " + boardReturned.currentVal);
	         		boardReturned = (newBoard.currentVal >= boardReturned.currentVal) ? new Board(oneMove) : boardReturned;
	         		boardReturned.currentVal = (newBoard.currentVal >= d) ? newBoard.currentVal  : d;
	         		a = Math.max(a,boardReturned.currentVal);
	         		// System.out.println("current board returned + eval: " + boardReturned.currentVal);

	         		// System.out.println();
	         		//// System.out.println(piece + "moving from" + piece.position + " to " + pos);
	         		if(b<=a) {
	         			break;
	         		}
				}
			}
			return boardReturned;
		}
		else{
			boardReturned.currentVal = Integer.MAX_VALUE;
			Piece[] pieces = allPieces(board.board, Color.WHITE);
			for(Piece piece : pieces) {
				Position[] p = piece.moves(board.size, board);
				for(Position pos : p) {
					Board newBoard = new Board(board);
				
					// System.out.println(piece.toString() + " positoin: " + piece.position + " to: " + pos );
					newBoard.boardMove(piece.position, pos);
					Board oneMove = new Board(newBoard);
					newBoard= regminimax(newBoard, depth -1, a, b, true);
					// System.out.println("newboard evaluation: " + newBoard.currentVal);
					// System.out.println("oldboard evaluation: " + boardReturned.currentVal);
					float d = boardReturned.currentVal;
	         		boardReturned = (newBoard.currentVal <= boardReturned.currentVal) ? new Board(oneMove) : boardReturned;
	         		boardReturned.currentVal = (newBoard.currentVal <= d) ? newBoard.currentVal : d;
	         		b = Math.min(b,boardReturned.currentVal);
	         		if(b<=a) {
	         			break;
	         		}
				}
			}
			return boardReturned;
		}

}


public Board pminimax(Board board, int depth, float a, float b, boolean maximizingPlayer) {
	if(depth == 0 || board.gameOver()) {

		if(maximizingPlayer) {
		board.currentVal=	eval(board,Color.WHITE);
		}
		else {
			board.currentVal = 	eval(board,Color.BLACK);
		}
	
		return board;
	}
	Board boardReturned = new Board(board);
	
	if(maximizingPlayer) {
		boardReturned.currentVal = Integer.MIN_VALUE;
		Piece[] pieces = allPieces(board.board, Color.BLACK);
		PriorityQueue<Board> child = new PriorityQueue<Board>(new Comparator<Board>() {
			public int compare(Board o1, Board o2) {
				return Float.compare(o1.currentVal,o2.currentVal);
			}
		});
		
		for(Piece piece : pieces) {
			Position[] p = piece.moves(board.size, board);
			for(Position pos : p) {
				Board newBoard = new Board(board);
				newBoard.boardMove(piece.position, pos);
				Board oneMove = new Board(newBoard);
				float evaluate = eval(oneMove, Color.BLACK);
				oneMove.currentVal = evaluate;
				child.add(oneMove);
				// System.out.println("newboard evaluation: " + newBoard.currentVal);
				
				// System.out.println("oldboard evaluation: " + boardReturned.currentVal);
         		
         		// System.out.println("current board returned + eval: " + boardReturned.currentVal);

         		// System.out.println();
         		
			}
		}
		while(child.peek() != null) {
			Board currEvalBoard = child.poll();
			Board cboard = new Board(currEvalBoard);
			// System.out.println("oldboard evaluation: " + boardReturned.currentVal);
			Board finboard = regminimax(currEvalBoard, depth -1, a, b, false);
			float d = finboard.currentVal;
     		boardReturned = (finboard.currentVal >= boardReturned.currentVal) ? new Board(currEvalBoard) : boardReturned;
     		boardReturned.currentVal = (d >= boardReturned.currentVal) ? d : boardReturned.currentVal;
     		a = Math.max(a,boardReturned.currentVal);
     		if(b<=a) {
     			break;
     		}
			
		}
		
		return boardReturned;
	}
	else{
		boardReturned.currentVal = Integer.MAX_VALUE;
		Piece[] pieces = allPieces(board.board, Color.WHITE);
		PriorityQueue<Board> child = new PriorityQueue<Board>(new Comparator<Board>() {
			public int compare(Board o1, Board o2) {
				return Float.compare(o2.currentVal,o1.currentVal);
			}
		});
		
		for(Piece piece : pieces) {
			Position[] p = piece.moves(board.size, board);
			for(Position pos : p) {
				Board newBoard = new Board(board);
				newBoard.boardMove(piece.position, pos);
				Board oneMove = new Board(newBoard);
				float evaluate = eval(oneMove, Color.WHITE);
				oneMove.currentVal = evaluate;
				child.add(oneMove);
				// System.out.println("newboard evaluation: " + newBoard.currentVal);
				
				// System.out.println("oldboard evaluation: " + boardReturned.currentVal);
         		
         		// System.out.println("current board returned + eval: " + boardReturned.currentVal);

         		// System.out.println();
         		//// System.out.println(piece + "moving from" + piece.position + " to " + pos);
         	
			}
		}
		while(child.peek() != null) {
			Board currEvalBoard = child.poll();
			Board cboard = new Board(currEvalBoard);
			// System.out.println("oldboard evaluation: " + boardReturned.currentVal);
			Board finboard = regminimax(currEvalBoard, depth -1, a, b, true);
			float d = finboard.currentVal;
     		boardReturned = (finboard.currentVal <= boardReturned.currentVal) ? new Board(currEvalBoard) : boardReturned;
     		boardReturned.currentVal = (currEvalBoard.currentVal <= d) ? currEvalBoard.currentVal  : d;
     		b = Math.min(b,boardReturned.currentVal);
     		if(b<=a) {
     			break;
     		}
			
		}
		
		return boardReturned;
	}

}

public float eval(Board b, Color c) {
	float currval =b.currentPiecesBlack - b.currentPiecesWhite;
	// System.out.println("currval after pieces" + currval);
	Piece[] pieces = allPieces(b.board, c);
	int maxSpace = (c == Color.BLACK) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
	for(Piece p : pieces) {
		if(c == Color.BLACK) {
			maxSpace = Math.max(maxSpace, p.position.row);
		}
		else if(c == Color.WHITE) {
			maxSpace = Math.min(maxSpace, p.position.row);
			
		}
		Position[] pos = p.moves(b.size, b);
		for(Position posi : pos) {
			boolean succAtt = false;
			boolean unsuccAtt = false;
			if(b.board[posi.row][posi.col] != null) {
				Piece otherPiece = b.board[posi.row][posi.col];
				if(otherPiece.color != p.color && otherPiece.type != Type.PIT) {
		
					if(p.attackres(otherPiece) == 1) {
						if(c == Color.BLACK) {
							if(!succAtt) {
							currval +=.5;
							// System.out.println("currval successful attack black: " + currval);
							// System.out.println("change");
							
							succAtt = true;
							}
						}
						else {
							if(c == Color.WHITE) {
								if(!succAtt) {
								currval-=.5;
								succAtt = true;
								// System.out.println("currval successful attack white: " + currval);

								}
							}
						}
						//increase whtie or black
					}
					else if(p.attackres(otherPiece) == -1) {
						if(c == Color.BLACK) {
							if(!unsuccAtt) {
								currval-=.6;
								unsuccAtt = true;
								// System.out.println("currval unsuccessful attack black: " + currval);

								}
							
						}
						else {
							if(!unsuccAtt) {
								currval+=.6;
								unsuccAtt = true;
								// System.out.println("currval successful attack white: " + currval);

								}
							
						}
						//decrease black or white
					}
				}
			}
		}
	}
currval += maxSpace * .003;
Random rand = new Random();
currval += (rand.nextFloat() * .0002);
// System.out.println("currval space: " + currval);

return currval;
}

Piece[] allPieces(Piece[][] board, Color c) {
	ArrayList<Piece> p = new ArrayList<Piece>();
	for(int x = 0; x < board.length; x ++) {
		for(int y = 0; y < board[0].length; y++) {
			if(board[x][y] != null) {
				if(board[x][y].type != Type.PIT && board[x][y].color == c) {
					p.add(board[x][y]);
				}
			}
		}
	}
	Piece[] arr = new Piece[p.size()];
	for(int x = 0; x < arr.length; x++) {
		arr[x] = p.get(x);
	}
	return arr;
}


}