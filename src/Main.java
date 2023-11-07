import Board.Board;
import Board.Position;
import Pieces.Piece;
import minimax.Minimax;

import java.util.Random;
import java.util.Scanner; 

public class Main {

	public static void main(String[] args) {
		Random r = new Random();
		int xd = r.nextInt(2);
		System.out.println("You are the W_ Pieces");
		if(xd == 0) {
		System.out.println("Going First");
		Board board = new Board(6);
		board.generateboard();
		board.printBoard();
		  Scanner in = new Scanner(System.in); 
		while(!board.gameOver()) {
		  System.out.println("Enter Character you want to move position" );
			String s = in.nextLine(); 
			System.out.println("Enter Where it should Move");
	        String c = in.nextLine();
	        Position currentChar = board.parse(s);
	        Position nextPosition = board.parse(c);
	        int x = board.boardMove(currentChar, nextPosition);
	        if(x == -1) {
	        	System.out.println("Sorry that is illegal");
	 		}
	        System.out.println("Opponent Move: ");
	        Minimax a = new Minimax(board, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        	board = a.regminimax(a.b, a.depth, a.alpha, a.beta, a.maximizingPlayer);
       	board.printBoard();
   
        	
	        if(board.gameOver()) {
	        	System.out.println("gg");
	        	System.exit(0);

		}
		  
	}

		in.close();
}
	
	else {
		System.out.println("Going Second");
		Board board = new Board(6);
		board.generateboard();
		 Minimax b = new Minimax(board, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
		 board = b.pminimax(b.b, b.depth, b.alpha, b.beta, b.maximizingPlayer);
		board.printBoard();
		  Scanner in = new Scanner(System.in); 
		while(!board.gameOver()) {
		  System.out.println("Enter Character you want to move position" );
			String s = in.nextLine(); 
			System.out.println("Enter Where it should Move");
	        String c = in.nextLine();
	        Position currentChar = board.parse(s);
	        Position nextPosition = board.parse(c);
	        int x = board.boardMove(currentChar, nextPosition);
	        if(x == -1) {
	        	System.out.println("Sorry that is illegal");
	 		}
	        System.out.println("Opponent Move: ");
	        Minimax a = new Minimax(board, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        	board = a.pminimax(a.b, a.depth, a.alpha, a.beta, a.maximizingPlayer);
       	board.printBoard();
   
        	
	        if(board.gameOver()) {
	        	System.out.println("gg");
	        	System.exit(0);

		}
		  
	}
		in.close();
	}
		

		
}
}