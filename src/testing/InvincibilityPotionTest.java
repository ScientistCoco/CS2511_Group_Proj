package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Buff;
import project.HoverPotion;
import project.Hunter;
import project.InvincibilityPotion;
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
		p1.notifyAllEnemies();
		
		//System.out.println(h1.getYCoordinate());
		assertTrue(p1.checkIfAlive() == false);
		assertTrue(p1.getHealth() == 0);
		
	}

	@Test
	public void TestItemWillBeDisappearIfPickedUp() {
		Board b = new Board();
		InvincibilityPotion s = new InvincibilityPotion(b);
		Player player = new Player(b);
		b.placeEntity(s, 0, 0);
		b.placeEntity(player, 0, 0);
		assertTrue(player.getInventory().findItem("invincibility potion").equals(s));
	}
	
	@Test
	public void TestItemWillDisappearIfUsed() {
		Board b = new Board();
		InvincibilityPotion s = new InvincibilityPotion(b);
		//Buff s = Buff.Invincibility;
		Player player = new Player(b);
		b.placeEntity(s, 0, 0);
		b.placeEntity(player, 0, 0);
		//player.addBuff(s);
		//assertTrue(player.containBuff(Buff.Invincibility));
		//s.useItem(player);
		System.out.println("1");
		//assertTrue(!player.containBuff(Buff.Invincibility));
		assertTrue(player.getInventory().findItem("invincibility potion").equals(s));
		player.getInventory().useItem(player, "invincibility potion");
		assertTrue(player.getInventory().findItem("invincibility potion") == null);
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
