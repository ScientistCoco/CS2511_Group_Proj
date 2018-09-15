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
import other.Floor;

public class OpeningDoorTest {
	Board b;
	Door d;
	Inventory i;
	Key k;
	Floor f;
	
	@Before
	public void setUp() throws Exception {
		this.b = new Board();
		this.d = new Door(b, 1);
		this.i = new Inventory();
		this.k = new Key(b, 0, i);
	}
	
	@Test
	public void placeNoMoreThan3Doors() {
		Door d1 = new Door(b, 1);
		Door d2 = new Door(b, 2);
		Door d3 = new Door(b, 3);
		b.placeEntity(d1, 2, 2);
		b.placeEntity(d2, 3, 3);
		b.placeEntity(d3, 4, 4);
		//assertEquals(true, f.countEntities(d1) <= 3);
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
