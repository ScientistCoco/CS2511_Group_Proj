package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enemies.Hunter;
import items.HoverPotion;
import items.Item;
import items.Sword;
import other.Board;
import other.Inventory;
import other.Pit;
import other.Player;
import other.Wall;

public class InventoryTest {
	Board b1;
	Player p1;
	Pit pit;
	Item it;
	HoverPotion hover;
	Inventory in;
	
	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		p1 = new Player(b1);
		hover = new HoverPotion(b1);
		it = hover; //????
		in = new Inventory();
	}
	
	@Test
	public void TestPickUpItem() {
		b1.placeEntity(it, 3, 3);
		assertTrue(b1.placeEntity(p1, 3, 3));
	}
	
	@Test
	public void TestItemCanBeFoundInInventory() {
		in.addItem(it);
		assertTrue(in.findItem("hover potion").equals(it));
	}
	
	@Test
	public void TestCannotPickUpTwoSword() {

		Sword s = new Sword(b1);
		Sword s2 = new Sword(b1);

		in.addItem(s);
		assertTrue(in.findItem("sword").equals(s));
		in.addItem(s2);
	}
	
	@Test
	public void TestItemWillBeDisappearIfItUsed() {

		b1.placeEntity(hover, 0, 0);
		b1.placeEntity(p1, 0, 0);
		assertEquals(true, p1.getInventory().findItem("hover potion").equals(hover));
		p1.getInventory().useItem(p1, "hover potion");
		assertEquals(true, p1.getInventory().findItem("hover potion") == null);
	}
}
