package testing;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import org.junit.Test;

import items.Key;
import other.Board;
import other.Door;
import other.DoorStatus;
import other.Player;

public class OpeningDoorTest {

	@Test
	public void testCannotOpen() {
		Board b1 =new Board();
		Door d1 = new Door(b1, 1);
		Key k1 = new Key(b1, 0);
		
		assertFalse(k1.openDoor(d1));
	}
	
	@Test
	public void testCanOpen() {
		Board b2 =new Board();
		Door d2 = new Door(b2, 1);
		Key k2 = new Key(b2, 1);
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
	public void testDoorStatusAfterOpening() {
		Board b4 =new Board();
		Door d4 = new Door(b4, 1);
		Key k4 = new Key(b4, 1);
		k4.openDoor(d4);
		assertTrue(d4.getDoorStatus() == DoorStatus.Open);
	}
	
	@Test
	public void testKeyDisappearAfterOpeningDoor() {
		Board b5 =new Board();
		Door d5 = new Door(b5, 1);
		Key k5 = new Key(b5, 1);
		Player p5 = new Player(b5);
		
		b5.placeEntity(k5, 0, 0);
		b5.placeEntity(p5, 0, 0);
		b5.placeEntity(d5, 1, 0);

		ByteArrayInputStream in = new ByteArrayInputStream("Right\n".getBytes());
		System.setIn(in);

		p5.getInventory().useItem(p5, "Key");
		
		assertEquals(null, p5.getInventory().findItem("Key"));
	}

	@Test
	public void testKeyRemainsWithNonMatchingDoor() {
		Board b5 =new Board();
		Door d5 = new Door(b5, 2);
		Key k5 = new Key(b5, 1);
		Player p5 = new Player(b5);
		
		b5.placeEntity(k5, 0, 0);
		b5.placeEntity(p5, 0, 0);
		b5.placeEntity(d5, 1, 0);
		
		ByteArrayInputStream in = new ByteArrayInputStream("Right\n".getBytes());
		System.setIn(in);

		p5.getInventory().useItem(p5, "Key");
		
		assertNotEquals(null, p5.getInventory().findItem("Key"));
	}
}
