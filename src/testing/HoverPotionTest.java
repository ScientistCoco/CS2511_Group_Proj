package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.HoverPotion;
import project.Pit;
import project.Player;

public class HoverPotionTest {
	
	@Test
	public void TestCannotPassPits() {
		Board b = new Board();
		Player p = new Player(b);
		//HoverPotion hover = new HoverPotion(b);
		Pit pit = new Pit(b);
		b.placeEntity(pit, 3, 3);
		//hover.useItem(p);
		assertFalse(b.placeEntity(p, 3, 3));
	}
	

	@Test
	public void TestPassPits() {
		Board b = new Board();
		Player p = new Player(b);
		HoverPotion hover = new HoverPotion(b);
		Pit pit = new Pit(b);
		b.placeEntity(pit, 3, 3);
		hover.useItem(p);
		assertTrue(b.placeEntity(p, 3, 3));
	}
	
	@Test
	public void TestItemWillBeDisappearIfItUsed() {
		Board b = new Board();
		HoverPotion s = new HoverPotion(b);
		Player player = new Player(b);
		s.overlappingEffect(player);
		assertTrue(player.getInventory().findItem("hover potion").equals(s));
		s.useItem(player);
		assertTrue(player.getInventory().findItem("hover potion") == null);
	}

}
