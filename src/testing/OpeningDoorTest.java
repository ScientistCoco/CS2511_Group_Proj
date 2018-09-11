package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Door;
import project.DoorStatus;
import project.Inventory;
import project.Key;
import project.Player;

public class OpeningDoorTest {

	@Test
	public void testCannotOpen() {
		Board b1 =new Board();
		Door d1 = new Door(b1, 1);
		Inventory i1 = new Inventory();
		Key k1 = new Key(b1, 0, i1);
		i1.addItem(k1);
		assertFalse(k1.openDoor(d1));
	}
	
	@Test
	public void testCanOpen() {
		Board b2 =new Board();
		Door d2 = new Door(b2, 1);
		Inventory i2 = new Inventory();
		Key k2 = new Key(b2, 1, i2);
		i2.addItem(k2);
		assertTrue(k2.openDoor(d2));
	}
	
	@Test
	public void testNoKey() {
		Board b3 =new Board();
		Door d3 = new Door(b3, 1);
		Player p3 = new Player(b3);
		assertFalse(d3.overlappingEffect(p3));
	}
	
	@Test
	public void testCanDoorStatusAfterOpening() {
		Board b4 =new Board();
		Door d4 = new Door(b4, 1);
		Inventory i4 = new Inventory();
		Key k4 = new Key(b4, 1, i4);
		i4.addItem(k4);
		k4.openDoor(d4);
		assertTrue(d4.getDoorStatus() == DoorStatus.Open);
	}
	
	@Test
	public void testKeyDisappearAfterOpeningDoor() {
		Board b5 =new Board();
		Door d5 = new Door(b5, 1);
		Inventory i5 = new Inventory();
		Key k5 = new Key(b5, 1, i5);
		i5.addItem(k5);
		k5.openDoor(d5);
		assertEquals(null, i5.findItem("key"));
	}

}
