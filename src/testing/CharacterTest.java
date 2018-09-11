package testing;

import project.Board;
import project.Door;
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
		Exit e1 = new Exit(2, 0, board);
		Door d1 = new Door(4, 4, board, 0);
		//d1.changeStatus(DoorStatus.Open);
		Objective objExit1 = new Objective(e1, "Pass through this exit to complete the game", 1);
		
		p1.addObjective(objExit1);
		
		board.placeEntity(p1, 1, 1);
		board.placeEntity(new Wall(0, 0, board), 0, 0);
		board.placeEntity(new Wall(0, 1, board), 0, 1);
		board.placeEntity(e1, 2, 4);
		board.placeEntity(d1, 4, 4);
		
		board.printBoard();
		while (!p1.getKeyboardInput()) {
			board.printBoard();
			System.out.println(p1.getObjectiveString());
			// If the player has completed all objectives then show congratulations message
			if (p1.checkObjectives() == true) {
				System.out.println("Congratulations you completed this level!");
				break;
			}
		}
		
		
	}
}
