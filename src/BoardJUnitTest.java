import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;

public class BoardJUnitTest {

	@Test
	public void placeEntityOnEmptyLocation() {
		Board board = new Board();
		assertEquals(true, board.placeEntity(new Object(), 5, 2));
	}
	
	@Test
	public void placeEntityOnNotEmptyLocation() {
		Board board = new Board();
		board.placeEntity(new Object(), 5, 2);
		assertEquals(false, board.placeEntity(new Object(), 5, 2));
	}
	
	@Test
	public void placeEntityOutsideBoard() {
		Board board = new Board();
		assertEquals(false, board.placeEntity(new Object(), 11, 11));
	}

}
