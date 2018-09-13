package testing;

import static org.junit.Assert.*;


import org.junit.Test;

import project.Arrow;
import project.Board;
import project.Direction;
import project.Enemy;
import project.Inventory;
import project.Player;
import project.Wall;

public class ArrowTest {

	@Test
	public void testFly() {
		Board b1 = new Board();
		Player p1 = new Player(b1);
		Direction d1 = Direction.Up;
		Arrow a1 = new Arrow(b1);
		b1.placeEntity(a1, 0, 0);
		a1.overlappingEffect(p1);
		assertNotEquals(null, p1.getInventory().findItem("arrow"));
		a1.Fly(d1);
		assertEquals(null, p1.getInventory().findItem("arrow"));
	}
	
	@Test
	public void testNotDestroyed() {
		Board b2 = new Board();
		Inventory i2 = new Inventory();
		Arrow a2 = new Arrow(b2);
		i2.addItem(a2);
		assertFalse(a2.destroyed());
	}
	
	@Test
	public void testDestroyedByWall() {
		Board b2 = new Board();
		Inventory i2 = new Inventory();
		Wall w = new Wall(b2);
		b2.placeEntity(w, 3, 3);
		Arrow a2 = new Arrow(b2);
		a2.setCoordinates(3, 3);
		i2.addItem(a2);
		assertTrue(a2.destroyed());
	}
	
	@Test
	public void testDestroyedByEnemy() {
		Board b2 = new Board();
		Inventory i2 = new Inventory();
		Enemy e = new Enemy(b2);
		assertTrue(e.getHealth() == 1);
		b2.placeEntity(e, 3, 3);
		Arrow a2 = new Arrow(b2);
		a2.setCoordinates(3, 3);
		i2.addItem(a2);
		assertTrue(a2.destroyed());
		assertTrue(e.getHealth() == 0);
	}
	
	


}
