package testing;
import static org.junit.Assert.*;

import org.junit.Test;

import items.HoverPotion;
import items.Key;
import other.Board;
import other.Door;
import other.Inventory;
import other.Player;

public class KeyTest {
	Board b1 = new Board();
	Player p1 = new Player(b1);
	Door d1 = new Door(b1, 0);
	Inventory i1 = new Inventory();
	Key k1 = new Key(b1, 0, i1);
	Key k2 = new Key(b1, 0, i1);

	@Test
	public void placeAKeyOnBoardWithCorrectCoordinates() {
		b1.placeEntity(k1, 2, 2);
		assertEquals(true, b1.getEntity(2, 2) == k1);
	}
	
	@Test
	public void placeADoorOnBoardWithCorrectCoordinates() {
		b1.placeEntity(d1, 2, 2);
		assertEquals(true, b1.getEntity(2, 2) == d1);
	}
	
	@Test
	public void playerPickingUpKey() {
		b1.placeEntity(k1, 2, 2);
		b1.placeEntity(p1, 2, 2);
		i1.addItem(k1);
		assertTrue(i1.findItem("key").equals(k1));
//		assertFalse(k1.openDoor(d1));
	}
	
	@Test
	public void assureOnlyOneKey() {
		b1.placeEntity(k1, 2, 2);
		i1.addItem(k1);
		assertTrue(i1.findItemAmount("key")==1);	
		i1.addItem(k2);
		assertFalse(i1.findItem("key").equals(k2));
		assertTrue(i1.findItemAmount("key")==1);	
	}
}
