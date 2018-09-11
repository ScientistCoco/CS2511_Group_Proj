package testing;

import project.Board;
import project.Entity;
import project.Floor;

public class BoardTest {
	
	// Class to test the production of the board class
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board(5, 5);
		board.printBoard();
		System.out.println("\n");
		
		//board.placeEntity(new Entity(0, 1, board), 0, 1);
		//board.printBoard();
		
		Floor[][] map = new Floor[10][10];
		
	}

}
