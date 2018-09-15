package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import items.Key;
import other.Board;
import other.Door;
import other.DoorStatus;
import other.Inventory;
import other.Player;

public class OpeningDoorTest {
	Board b;
	Door d;
	Inventory i;
	Key k;
	
	@Before
	public void setUp() throws Exception {
		this.b = new Board();
		this.d = new Door(b, 1);
		this.i = new Inventory();
		this.k = new Key(b, 0, i);
	}
	
	@Test
	public void testCannotOpen() {
		i.addItem(k);
		assertFalse(k.openDoor(d));
	}
	
	@Test
	public void testCanOpen() {
		k = new Key(b, 1, i);
		i.addItem(k);
		assertTrue(k.openDoor(d));
	}
	
	@Test
	public void testNoKey() {
		Player p = new Player(b);
		assertFalse(d.overlappingEffect(p));
	}
	
	@Test
	public void testCanDoorStatusAfterOpening() {
		k = new Key(b, 1, i);
		i.addItem(k);
		k.openDoor(d);
		assertTrue(d.getDoorStatus() == DoorStatus.Open);
	}
	
	@Test
	public void testKeyDisappearAfterOpeningDoor() {
		k = new Key(b, 1, i);
		i.addItem(k);
		k.openDoor(d);
		assertEquals(null, i.findItem("key"));
	}

}
