package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enemies.Coward;
import items.HoverPotion;
import other.Board;
import other.Buff;
import other.Pit;
import other.Player;

public class HoverPotionTest {
	Board b1;
	Player p1;
	Pit pit;
	HoverPotion hover;
	
	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		p1 = new Player(b1);
		pit = new Pit(b1);
		hover = new HoverPotion(b1);
	}
	
	@Test
	public void TestPlayerFallsThroughPitWithoutHoverPotion() {
		b1.placeEntity(p1, 3, 2);
		b1.placeEntity(pit, 3, 3);
		b1.placeEntity(p1, 3, 3);
		assertEquals(true, p1.getHealth() == 0);
		assertEquals(true, b1.getEntity(3, 3) == pit);
	}
	

	@Test
	public void TestPassPits() {
		b1.placeEntity(pit, 3, 3);
		b1.placeEntity(p1, 3, 2);
		hover.useItem(p1);
		assertTrue(b1.placeEntity(p1, 3, 3));
	}
	
	@Test
	public void TestItemWillBeDisappearIfItUsed() {

		b1.placeEntity(hover, 0, 0);
		b1.placeEntity(p1, 0, 0);
		assertTrue(p1.getInventory().findItem("hover potion").equals(hover));
		p1.getInventory().useItem(p1, "hover potion");
		assertTrue(p1.getInventory().findItem("hover potion") == null);
	}

}
