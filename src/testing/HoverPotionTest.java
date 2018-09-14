package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Buff;
import project.HoverPotion;
import project.Pit;
import project.Player;

public class HoverPotionTest {
	
	@Test
	public void TestPlayerFallsThroughPitWithoutHoverPotion() {
		Board b = new Board();
		Player p = new Player(b);
		Pit pit = new Pit(b);
		b.placeEntity(p, 3, 2);
		b.placeEntity(pit, 3, 3);
		b.placeEntity(p, 3, 3);
		assertEquals(true, p.getHealth() == 0);
		assertEquals(true, b.getEntity(3, 3) == pit);
	}
	

	@Test
	public void TestPassPits() {
		Board b = new Board();
		Player p = new Player(b);
		HoverPotion hover = new HoverPotion(b);
		Pit pit = new Pit(b);
		b.placeEntity(pit, 3, 3);
		b.placeEntity(p, 3, 2);
		hover.useItem(p);
		assertTrue(b.placeEntity(p, 3, 3));
	}
	
	@Test
	public void TestItemWillBeDisappearIfItUsed() {
		Board b = new Board();
		HoverPotion s = new HoverPotion(b);
		Player player = new Player(b);
		b.placeEntity(s, 0, 0);
		b.placeEntity(player, 0, 0);
		assertTrue(player.getInventory().findItem("hover potion").equals(s));
		player.getInventory().useItem(player, "hover potion");
		assertTrue(player.getInventory().findItem("hover potion") == null);
	}

}
