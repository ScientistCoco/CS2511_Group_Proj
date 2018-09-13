package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Buff;
import project.Hunter;
import project.Player;

public class InvincibilityPotionTest {

	@Test
	// player die when he meet the hunter
	public void testWithoutPotion() {
		Board b1 = new Board();
		Player p1 = new Player(b1);
		Hunter h1 = new Hunter(b1);
		
		b1.placeEntity(p1, 2, 2);
		b1.placeEntity(h1, 2, 1);
		p1.addEnemy(h1);
		System.out.println(p1.getEnemies());
		p1.notifyAllEnemies();
		
		
		assertTrue(p1.checkIfAlive() == false);
		assertTrue(p1.getHealth() == 0);
		
	}

	
	/*
	@Test
	 this time enemy will run away from player
	public void testWithPotion() {
		Board b1 = new Board();
		Player p1 = new Player(b1);
		Hunter h1 = new Hunter(b1);
		
		b1.placeEntity(p1, 2, 2);
		b1.placeEntity(h1, 2, 1);
		Buff b = Buff.Invincibility;
		p1.addBuff(b);
		p1.notifyAllEnemies();
		
		assertTrue(h1.getXCoordinate() == 3);
		assertTrue(h1.getYCoordinate() == 1);
	}
	
	@Test
	public void testKillEnemy() {
		Board b1 = new Board();
		Player p1 = new Player(b1);
		Hunter h1 = new Hunter(b1);
		
		b1.placeEntity(p1, 1, 0);
		b1.placeEntity(h1, 0, 0);
		
		
	}
	*/
}
