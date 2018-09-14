package testing;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import project.Board;
import project.Enemy;
import project.Inventory;
import project.Player;
import project.Sword;

public class SwordTest {

	@Test
	public void TestDestroyEnemy() {
		Board b1 = new Board();
		Sword s1 = new Sword(b1);
		Enemy e1 = new Enemy(b1);
		assertEquals(1, e1.getHealth());
		s1.attack(e1);
		assertEquals(0, e1.getHealth());
	}
	
	@Test
	public void TestHitsRemain() {
		Board b2 = new Board();
		Inventory i2 = new Inventory();
		Sword s2 = new Sword(b2);
		i2.addItem(s2);
		Enemy e2 = new Enemy(b2);
		assertEquals(5, s2.getHitsRemain());
		s2.attack(e2);
		assertEquals(4, s2.getHitsRemain());
	}
	
	@Test
	public void TestSwordDisappearAfterFiveHits() {
		Board b3 = new Board();
		Player p = new Player(b3);
		Sword s3 = new Sword(b3);
		s3.overlappingEffect(p);
		Enemy e3 = new Enemy(b3);
		Assert.assertNotEquals(null, p.getInventory().findItem("sword"));
		int i;
		for (i=0; i<5; i++) {
			s3.attack(e3);
		}
		Assert.assertEquals(null, p.getInventory().findItem("sword"));
	}

}
