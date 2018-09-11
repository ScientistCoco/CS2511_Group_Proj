import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Entity;
import project.Player;
import project.Wall;

public class BoardJUnitTest {
	Board board = new Board();
	Player p1 = new Player(board);
	Wall w1 = new Wall(board);
	
	@Test
	public void placeEntityOnEmptyLocation() {
		assertEquals(true, board.placeEntity(p1, 5, 2));
	}
	
	@Test
	public void placeEntityOnNotEmptyLocation() {
		board.placeEntity(w1, 5, 2);
		assertEquals(false, board.placeEntity(new Entity(board), 5, 2));
	}
	
	@Test
	public void placeEntityOutsideBoard() {
		assertEquals(false, board.placeEntity(new Entity(board), 11, 11));
	}
	
	@Test
	public void checkWallInCorrectSpotOnMap() {
		assertEquals(true, board.getEntity(5, 2) instanceof Wall);
	}

}
