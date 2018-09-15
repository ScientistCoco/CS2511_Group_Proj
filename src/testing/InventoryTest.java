package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import items.HoverPotion;
import items.Item;
import items.Sword;
import other.Board;
import other.Inventory;
import other.Player;

public class InventoryTest {

	@Test
	public void TestPickUpItem() {
		Board b = new Board();
		Item it = new HoverPotion(b);
		b.placeEntity(it, 3, 3);
		Player p = new Player(b);
		assertTrue(b.placeEntity(p, 3, 3));
	}
	
	@Test
	public void TestItemCanBeFoundInInventory() {
		Board b = new Board();
		HoverPotion it = new HoverPotion(b);
		Inventory i = new Inventory();
		i.addItem(it);
		assertTrue(i.findItem("hover potion").equals(it));
	}
	
	@Test
	public void TestCannotPickUpTwoSword() {
		Board b = new Board();
		Inventory i = new Inventory();
		Sword s = new Sword(b);
		i.addItem(s);
		assertTrue(i.findItem("sword").equals(s));
		
		Sword s2 = new Sword(b);
		i.addItem(s2);
	}
	
	@Test
	public void TestItemWillBeDisappearIfItUsed() {
		Board b = new Board();
		HoverPotion s = new HoverPotion(b);
		Player player = new Player(b);
		b.placeEntity(s, 0, 0);
		b.placeEntity(player, 0, 0);
		assertEquals(true, player.getInventory().findItem("hover potion").equals(s));
		player.getInventory().useItem(player, "hover potion");
		assertEquals(true, player.getInventory().findItem("hover potion") == null);
	}
}
