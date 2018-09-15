package testing;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import items.Key;
import other.Board;
import other.Door;
import other.DoorStatus;
import other.Player;


public class OpeningDoorTest {
	Board b1;
	Door d1;
	Key k1;
	Player p1;
	
	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		d1 = new Door(b1, 1);
	}

	@Test
	public void testCannotOpen() {
		k1 = new Key(b1, 0);
		assertFalse(k1.openDoor(d1));
	}
	
	@Test
	public void testCanOpen() {
		k1 = new Key(b1, 1);
		assertTrue(k1.openDoor(d1));
	}
	
	@Test
	public void testNoKey() {
		Door d1 = new Door(b1, 1);
		Player p1 = new Player(b1);
		assertFalse(d1.overlappingEffect(p1));
	}
	
	@Test
	public void testDoorStatusAfterOpening() {
		k1 = new Key(b1, 1);
		k1.openDoor(d1);
		assertTrue(d1.getDoorStatus() == DoorStatus.Open);
	}
	
	@Test
	public void testKeyDisappearAfterOpeningDoor() {
		k1 = new Key(b1, 1);
		p1 = new Player(b1);
		
		b1.placeEntity(k1, 0, 0);
		b1.placeEntity(p1, 0, 0);
		b1.placeEntity(d1, 1, 0);

		ByteArrayInputStream in = new ByteArrayInputStream("Right\n".getBytes());
		System.setIn(in);

		p1.getInventory().useItem(p1, "Key");
		
		assertEquals(null, p1.getInventory().findItem("Key"));
	}

	@Test
	public void testKeyRemainsWithNonMatchingDoor() {
		d1 = new Door(b1, 2);
		k1 = new Key(b1, 1);
		p1 = new Player(b1);

		b1.placeEntity(k1, 0, 0);
		b1.placeEntity(p1, 0, 0);
		b1.placeEntity(d1, 1, 0);
		
		ByteArrayInputStream in = new ByteArrayInputStream("Right\n".getBytes());
		System.setIn(in);

		p1.getInventory().useItem(p1, "Key");
		
		assertNotEquals(null, p1.getInventory().findItem("Key"));
	}
	
	@Test
 	public void placeNoMoreThan3Doors() {
 		Door d1 = new Door(b1, 1);
 		Door d2 = new Door(b1, 2);
 		Door d3 = new Door(b1, 3);
 		b1.placeEntity(d1, 2, 2);
 		b1.placeEntity(d2, 3, 3);
 		b1.placeEntity(d3, 4, 4);
 		//assertEquals(true, f.countEntities(d1) <= 3);
 	}
 	
}