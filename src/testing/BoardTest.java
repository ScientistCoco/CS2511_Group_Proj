package testing;

import project.Board;

public class BoardTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board(5, 5);
		board.printBoard();
		System.out.println("\n");
		
		board.placeEntity(new Object(), 0, 1);
		board.printBoard();		

	}

}
