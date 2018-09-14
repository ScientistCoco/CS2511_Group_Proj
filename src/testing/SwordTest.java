package testing;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import project.Board;
import project.Direction;
import project.Enemy;
import project.Player;
import project.Sword;

public class SwordTest {

	@Test
	public void TestDestroyEnemy() {
		Board b1 = new Board();
		Sword s1 = new Sword(b1);
		Enemy e1 = new Enemy(b1);
		Player p1 = new Player(b1);
		
		b1.placeEntity(s1, 0, 0);	// Grid looks like this: 	♀ ☻ . .
		b1.placeEntity(p1, 0, 0);	//							. . . . 
		b1.placeEntity(e1, 1, 0);	//							. . . . 
		
		assertEquals(1, e1.getHealth());
		s1.swing(p1, Direction.Right);
		assertEquals(0, e1.getHealth());
	}
	
	@Test
	public void TestHitsRemain() {
		Board b2 = new Board();
		Sword s2 = new Sword(b2);
		Enemy e2 = new Enemy(b2);
		Player p2 = new Player(b2);
		
		b2.placeEntity(s2, 0, 0);	// Grid looks like this: 	♀ ☻ . .
		b2.placeEntity(p2, 0, 0);	//							. . . . 
		b2.placeEntity(e2, 1, 0);	//							. . . . 
		
		assertEquals(5, s2.getDurability());
		s2.swing(p2, Direction.Right);
		assertEquals(4, s2.getDurability());
	}
	
	@Test
	public void TestSwordDisappearAfterFiveHits() {
		Board b3 = new Board();
		Player p = new Player(b3);
		Sword s3 = new Sword(b3);
		
		b3.placeEntity(s3, 0, 0);
		b3.placeEntity(p, 0, 0);
		
		Assert.assertNotEquals(null, p.getInventory().findItem("sword"));
		int i;
		for (i=0; i<5; i++) {
			s3.swing(p, Direction.Right);
		}
		Assert.assertEquals(null, p.getInventory().findItem("sword"));
	}

}
