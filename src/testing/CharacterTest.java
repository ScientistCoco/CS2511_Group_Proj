package testing;

import project.Board;
import project.Player;
import project.Wall;

public class CharacterTest {
	
	// Class to test the movement of the character class by receiving playerInput.
	// Also tests the implementation of the wall class - prevent character from passing through it.
	public static void main(String[] args) {
		Board board = new Board();
		Player p1 = new Player(1, 1, board);
		
		board.placeEntity(new Wall(board), 0, 0);
		board.placeEntity(new Wall(board), 0, 1);
		
		board.printBoard();
		while (!p1.getKeyboardInput()) {
			board.printBoard();
		}
		
		
	}
}
