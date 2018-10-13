package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enemies.Hunter;
import items.HoverPotion;
import items.InvincibilityPotion;
import items.Item;
import other.Board;
import other.Buff;
import other.Inventory;
import other.Pit;
import other.Player;

public class InvincibilityPotionTest {
	Board b1;
	Player p1;
	Hunter h1;
	InvincibilityPotion invinPotion;

	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		p1 = new Player(b1);
		h1 = new Hunter(b1);
		invinPotion = new InvincibilityPotion(b1);
	}
	
	@Test
	// player die when he meet the hunter
	public void testWithoutPotion() {
		b1.placeEntity(p1, 2, 2);
		b1.placeEntity(h1, 2, 1);
		p1.notifyObservers();
		
		//System.out.println(h1.getYCoordinate());
		assertTrue(p1.checkIfAlive() == false);
		assertTrue(p1.getHealth() == 0);
		
	}

	@Test
	public void TestItemWillBeDisappearIfPickedUp() {
		b1.placeEntity(invinPotion, 0, 0);
		b1.placeEntity(p1, 0, 0);
		assertTrue(p1.getInventory().findItem("invincibility potion").equals(invinPotion));
	}
	
	@Test
	public void TestItemWillDisappearIfUsed() {
		//Buff s = Buff.Invincibility;
		b1.placeEntity(invinPotion, 0, 0);
		b1.placeEntity(p1, 0, 0);
		//player.addBuff(s);
		//assertTrue(player.containBuff(Buff.Invincibility));
		//s.useItem(player);
		System.out.println("1");
		//assertTrue(!player.containBuff(Buff.Invincibility));
		assertTrue(p1.getInventory().findItem("invincibility potion").equals(invinPotion));
		p1.getInventory().useItem(p1, "invincibility potion");
		assertTrue(p1.getInventory().findItem("invincibility potion") == null);
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
