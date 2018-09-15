package testing;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import enemies.Enemy;
import items.Bomb;
import items.Sword;
import other.Board;
import other.Direction;
import other.Pit;
import other.Player;

public class SwordTest {
	Board b1;
	Player p1;
	Enemy e1;
	Sword s1;
	
	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		s1 = new Sword(b1);
		e1 = new Enemy(b1);
		p1 = new Player(b1);
	}
	
	@Test
	public void TestDestroyEnemy() {
		
		b1.placeEntity(s1, 0, 0);	// Grid looks like this: 	♀ ☻ . .
		b1.placeEntity(p1, 0, 0);	//							. . . . 
		b1.placeEntity(e1, 1, 0);	//							. . . . 
		
		assertEquals(1, e1.getHealth());
		s1.swing(p1, Direction.Right);
		assertEquals(0, e1.getHealth());
	}
	
	@Test
	public void TestHitsRemain() {
		
		b1.placeEntity(s1, 0, 0);	// Grid looks like this: 	♀ ☻ . .
		b1.placeEntity(p1, 0, 0);	//							. . . . 
		b1.placeEntity(e1, 1, 0);	//							. . . . 
		
		assertEquals(5, s1.getDurability());
		s1.swing(p1, Direction.Right);
		assertEquals(4, s1.getDurability());
	}
	
	@Test
	public void TestSwordDisappearAfterFiveHits() {

		b1.placeEntity(s1, 0, 0);
		b1.placeEntity(p1, 0, 0);
		
		Assert.assertNotEquals(null, p1.getInventory().findItem("sword"));
		int i;
		for (i=0; i<5; i++) {
			s1.swing(p1, Direction.Right);
		}
		Assert.assertEquals(null, p1.getInventory().findItem("sword"));
	}

}
