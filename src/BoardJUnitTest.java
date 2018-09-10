import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Entity;
import project.Player;
import project.Wall;

public class BoardJUnitTest {

	@Test
	public void placeEntityOnEmptyLocation() {
		Board board = new Board();
		assertEquals(true, board.placeEntity(new Player(5, 2, board), 5, 2));
	}
	
	@Test
	public void placeEntityOnNotEmptyLocation() {
		Board board = new Board();
		board.placeEntity(new Wall(5, 2, board), 5, 2);
		assertEquals(false, board.placeEntity(new Entity(5, 2, board), 5, 2));
	}
	
	@Test
	public void placeEntityOutsideBoard() {
		Board board = new Board();
		assertEquals(false, board.placeEntity(new Entity(5, 2, board), 11, 11));
	}

}
