package testing;

import project.Board;
import project.Player;

public class CharacterTest {
	
	// Class to test the movement of the character class by receiving playerInput
	public static void main(String[] args) {
		Board board = new Board();
		Player p1 = new Player(1, 1, board);
		
		board.printBoard();
		while (!p1.getKeyboardInput()) {
			board.printBoard();
		}
		
		
	}
}
