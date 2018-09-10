package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Door;
import project.Key;

public class OpeningDoorTest {

	@Test
	public void testCannotOpen() {
		Board b1 =new Board();
		Door d1 = new Door(1, 1, b1, 1);
		Key k1 = new Key(0, 0, b1, 0);
		assertFalse(k1.openDoor(d1));
	}
	
	@Test
	public void testCanOpen() {
		Board b2 =new Board();
		Door d2 = new Door(1, 1, b2, 1);
		Key k2 = new Key(0, 0, b2, 1);
		System.out.println(d2.getDoorNum() + " " + k2.getKey());
		assertTrue(k2.openDoor(d2));
	}

}
