package Board;
import java.util.Random;

import Pieces.Hero;
import Pieces.Mage;
import Pieces.Piece;
import Pieces.Pit;
import Pieces.Wumpus;
import interfaces.Color;
import interfaces.Type;

public class Board {
	public int size;
	public Piece[][] board;
	public int currentPiecesWhite;
	public int currentPiecesBlack;
	public float currentVal;
	
	public Board(int size) {
		board = new Piece[size][size];
		generateboard();
		this.size = size;
		this.currentPiecesWhite=size;
		this.currentPiecesBlack=size;
		this.currentVal = 0;
	}
	
	public Board(Board boared) {
		this.size = boared.size;
		this.board = new Piece[size][size];
		for(int x = 0; x < this.size; x++) {
			for(int y = 0; y < this.size; y++) {
				if(boared.board[x][y] != null) {
					if(boared.board[x][y].type == Type.HERO ) {
						this.board[x][y] = new Hero(boared.board[x][y]);
					}
					else if(boared.board[x][y].type == Type.MAGE) {
						this.board[x][y] = new Mage(boared.board[x][y]);
					}
					else if(boared.board[x][y].type == Type.WUMPUS) {
						this.board[x][y] = new Wumpus(boared.board[x][y]);
					}
					else {
						this.board[x][y] = new Pit(boared.board[x][y]);
					}
				}
			}
		}
		
		this.currentPiecesWhite=boared.currentPiecesWhite;
		this.currentPiecesBlack=boared.currentPiecesBlack;
		this.currentVal = boared.currentVal;
	}
	public void generateboard() {
		for(int y = 0; y < size/3; y++) {
			board[0][3*y] = new Wumpus(Color.BLACK, new Position(0,3*y),Type.WUMPUS );
			board[0][3*y+1] = new Hero(Color.BLACK, new Position(0,3*y+1),Type.HERO);
			board[0][3*y+2] = new Mage(Color.BLACK, new Position(0,3*y+2),Type.MAGE );
			board[size-1][3*y] = new Wumpus(Color.WHITE, new Position(size-1,3*y),Type.WUMPUS );
			board[size-1][3*y+1] = new Hero(Color.WHITE, new Position(size-1,3*y+1),Type.HERO);
			board[size-1][3*y+2] = new Mage(Color.WHITE, new Position(size-1,3*y+2),Type.MAGE );
		}
		
		
		int pits = size/3;
		for(int i = 1; i < size-1; i++) {
			Random rand = new Random();
			for(int p = 0; p < pits; p++) {
				int ran = rand.nextInt(size);
				board[i][ran] = new Pit(Color.NONE, new Position(i,ran ), Type.PIT);
			}
		}
		
	}
	
	public void printBoard() {
		System.out.print("   ");
		for(int z = 0; z < board.length; z++) {
			System.out.print(z + "   ");
		}
		System.out.println();
		int countcol = 0;
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[0].length; y++) {
	
		
					if(y==0) {
					System.out.print(countcol + " ");
					countcol++;
					}
					if(board[x][y] == null) {
						System.out.print(" __ ");
					}
					else {
						System.out.print(" " + board[x][y].toString() + " ");
					}
				
			}
			System.out.println();
		}
		
		
	}
	
	public int boardMove(Position cpos, Position pos) {
		Piece currpiece = board[cpos.row][cpos.col];
		if(board[pos.row][pos.col] != null) {
			if(board[pos.row][pos.col].color == currpiece.color) {
				return -1;
			}
			else {
				int res = currpiece.attackres(board[pos.row][pos.col]);
				if(res == -1) {
					if(currpiece.color == Color.WHITE) {
						board[cpos.row][cpos.col] = null;
						currentPiecesWhite--;
						return 1;
					}
					currentPiecesBlack--;
					board[currpiece.position.row][currpiece.position.col] = null;
					return 1;
				}
				if(res == 0) {
					board[cpos.row][cpos.col] = null;
					board[pos.row][pos.col] = null;
					currentPiecesWhite--;
					currentPiecesBlack--;
					return 1;
				}
				if(res == 1) {
					if(currpiece.color == Color.WHITE) {
					board[pos.row][pos.col] = board[cpos.row][cpos.col];
					board[cpos.row][cpos.col] = null;
					board[pos.row][pos.col].position = pos;
					currentPiecesBlack--;
					return 1;
					}
					board[pos.row][pos.col] = null;
					board[pos.row][pos.col] = board[cpos.row][cpos.col];
					board[cpos.row][cpos.col] = null;
					board[pos.row][pos.col].position = pos;
					currentPiecesWhite--;
					return 1;
				}
			}
		}
		
	
		board[pos.row][pos.col] = board[cpos.row][cpos.col];
		board[pos.row][pos.col].position = pos;
		board[cpos.row][cpos.col] = null;
		
	
		return 1;
		
	}
	
	public Position parse(String x) {
		String[] splited = x.split("\\s+");
		return new Position(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
	}
	
	public boolean gameOver() {
		if(currentPiecesWhite == 0 || currentPiecesBlack == 0) {
			return true;
		}
		return false;
	}
	
	
	
	
}
