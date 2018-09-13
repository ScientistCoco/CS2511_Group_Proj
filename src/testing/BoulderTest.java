package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Boulder;
import project.Door;
import project.Pit;
import project.Player;
import project.Wall;

public class BoulderTest {
	Board b1 = new Board();
	Boulder bou1 = new Boulder(b1);
	
	@Test
	public void instantiateABoulderOnBoardWithCorrectCoordinates() {
		b1.placeEntity(bou1, 2, 1);
		assertEquals(true, b1.getEntity(2, 1) == bou1);
	}
	
	@Test
	public void otherEntitiesCannotExistOnTopOfTheBoulder() {
		b1.placeEntity(bou1, 2, 1);
		
		Door d1 = new Door(b1, 0);
		b1.placeEntity(d1, 2, 1);
		
		assertEquals(true, b1.getEntity(2, 1) == bou1);
		
		// Default value when int is not initialized is 0. Which would mean that d1 has not been
		// assigned to the coordinates requested.
		assertEquals(true, d1.getXCoordinate() == 0 && d1.getYCoordinate() == 0);
	}
	
	@Test
	public void pushingBoulderIntoAdjacentSquare() {
		b1.placeEntity(bou1, 2, 1);
		
		Player p1 = new Player(b1);
		b1.placeEntity(p1, 1, 1);
		
		// Player will move to the right to push the boulder to the right
		b1.placeEntity(p1, 2, 1);
		
		assertEquals(true, bou1.getXCoordinate() == 3 && bou1.getYCoordinate() == 1);
		assertEquals(true, p1.getXCoordinate() == 2 && p1.getYCoordinate() == 1);
	}
	
	@Test
	public void tryingToPushBoulderPastEdgeOfBoard() {
		// The boulder should not move because it is not allowed to move out of the board.
		// As a result the player should also not move
		b1.placeEntity(bou1, 0, 0);
		
		Player p1 = new Player(b1);
		b1.placeEntity(p1, 0, 1);
		
		// Player will move up to try move the boulder up into its next adjacent square
		b1.placeEntity(p1, 0, 0);
		
		assertEquals(true, bou1.getXCoordinate() == 0 && bou1.getYCoordinate() == 0);
		assertEquals(true, p1.getXCoordinate() == 0 && p1.getYCoordinate() == 1);
	}
	
	@Test
	public void tryingToPushBoulderPastAWall() {
		// The boulder should not move because it is not allowed to pass through a wall
		// As a result the player should also not move
		b1.placeEntity(bou1, 1, 0);
		
		Player p1 = new Player(b1);
		Wall w1 = new Wall(b1);
		b1.placeEntity(p1, 2, 0);
		b1.placeEntity(w1, 0, 0);
		
		// Player will move left to try move the boulder left into its next adjacent square
		b1.placeEntity(p1, 1, 0);
		
		assertEquals(true, bou1.getXCoordinate() == 1 && bou1.getYCoordinate() == 0);
		assertEquals(true, p1.getXCoordinate() == 2 && p1.getYCoordinate() == 0);
	}
	
	@Test
	public void pushingBoulderIntoAPit() {
		// Pushing a boulder into a pit will cause the boulder to disappear from the map.
		b1.placeEntity(bou1, 1, 0);
		
		Player p1 = new Player(b1);
		Pit pit1 = new Pit(b1);
		b1.placeEntity(p1, 2, 0);
		b1.placeEntity(pit1, 0, 0);
		
		// Player will move left causing the boulder to move into a pit and disappear
		b1.placeEntity(p1, 1, 0);
		
		assertEquals(true, b1.getEntity(1, 0) == null);
		assertEquals(true, b1.getEntity(0, 0) == pit1);
		assertEquals(true, p1.getXCoordinate() == 1 && p1.getYCoordinate() == 0);
	}
}
