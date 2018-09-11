package testing;

import project.Board;
import project.Entity;
import project.Floor;
import project.Wall;

public class BoardTest {
	
	// Class to test the production of the board class
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board(5, 5);
		board.printBoard();
		System.out.println("\n");
		
		//board.placeEntity(new Entity(0, 1, board), 0, 1);
		//board.printBoard();
		
		Wall w1 = new Wall(board);
		board.placeEntity(w1, 1, 0);
		System.out.println(board.getEntity(1, 0));
		//System.out.println(w1.getXCoordinate());
		//board.printBoard();
		
		/*Floor f1 = new Floor();
		System.out.println(f1.getEntities());
		f1.addEntity(w1);
		System.out.println(f1.getFrontEntity());*/
	}

}
