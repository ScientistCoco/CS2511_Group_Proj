package testing;

import project.Board;

public class BoardTest {
	
	// Class to test the production of the board class
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board(5, 5);
		board.printBoard();
		System.out.println("\n");
		
		board.placeEntity(new Object(), 0, 1);
		board.printBoard();		
		
	}

}
