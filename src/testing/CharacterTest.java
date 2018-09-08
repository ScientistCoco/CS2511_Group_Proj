package testing;

import project.Board;
import project.Exit;
import project.Objective;
import project.Player;
import project.Wall;

public class CharacterTest {
	
	// Class to test the movement of the character class by receiving playerInput.
	// Also tests the implementation of the wall class - prevent character from passing through it.
	public static void main(String[] args) {
		Board board = new Board();
		Player p1 = new Player(1, 1, board);
		Exit e1 = new Exit(9, 9, board);
		Objective objExit1 = new Objective(e1, "Pass through this exit to complete the game", 1);
		
		p1.addObjective(objExit1);
		
		board.placeEntity(new Wall(0, 0, board), 0, 0);
		board.placeEntity(new Wall(0, 0, board), 0, 1);
		board.placeEntity(e1, 9, 9);
		
		board.printBoard();
		while (!p1.getKeyboardInput()) {
			board.printBoard();
			System.out.println(p1.getObjectiveString());
		}
		
		
	}
}
