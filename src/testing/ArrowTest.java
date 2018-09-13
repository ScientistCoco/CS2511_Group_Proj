package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Arrow;
import project.Board;
import project.Direction;
import project.Enemy;
import project.Wall;

public class ArrowTest {

	// Test arrow is able to fly downwards with no obstacles in its course. The arrow should also disappear off the map
	// if it exceeds the boundaries on the map.
	@Test
	public void testFlyDown() {
		Board b1 = new Board();
		Arrow a1 = new Arrow(b1);
		b1.placeEntity(a1, 0, 0);
		a1.Fly(Direction.Down);
		assertEquals(true, a1.getXCoordinate() == 0 && a1.getYCoordinate() == 9);
		assertEquals(true, b1.getEntity(0, 9) == null);
	}
	
	@Test
	public void testFlyUp() {
		Board b1 = new Board();
		Arrow a1 = new Arrow(b1);
		b1.placeEntity(a1, 0, 9);
		a1.Fly(Direction.Up);
		assertEquals(true, a1.getXCoordinate() == 0 && a1.getYCoordinate() == 0);
		assertEquals(true, b1.getEntity(0, 0) == null);
	}
	
	@Test
	public void testFlyRight() {
		Board b1 = new Board();
		Arrow a1 = new Arrow(b1);
		b1.placeEntity(a1, 0, 0);
		a1.Fly(Direction.Right);
		assertEquals(true, a1.getXCoordinate() == 9 && a1.getYCoordinate() == 0);
		assertEquals(true, b1.getEntity(9, 0) == null);
	}
	
	@Test
	public void testFlyLeft() {
		Board b1 = new Board();
		Arrow a1 = new Arrow(b1);
		b1.placeEntity(a1, 9, 0);
		a1.Fly(Direction.Left);
		assertEquals(true, a1.getXCoordinate() == 0 && a1.getYCoordinate() == 0);
		assertEquals(true, b1.getEntity(0, 0) == null);
	}
	
	@Test
	public void testDestroyedByWall() {
		Board b2 = new Board();
		Wall w = new Wall(b2);
		b2.placeEntity(w, 3, 3);
		Arrow a2 = new Arrow(b2);
		a2.setCoordinates(3, 1);
		a2.Fly(Direction.Down);
		assertEquals(true, b2.getEntity(3, 2) == null);
		assertEquals(true, b2.getEntity(3, 3) == w);
	}
	
	@Test
	public void testDestroyedByEnemy() {
		Board b2 = new Board();
		Enemy e = new Enemy(b2);
		b2.placeEntity(e, 3, 3);
		Arrow a2 = new Arrow(b2);
		a2.setCoordinates(3, 0);
		a2.Fly(Direction.Down);
		assertEquals(true, e.getHealth() == 0);
		assertEquals(true, b2.getEntity(3, 4) == null);	// Once arrow hits enemy it should disappear
	}
	
	


}
