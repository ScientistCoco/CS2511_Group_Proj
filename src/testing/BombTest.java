package testing;

import static org.junit.Assert.*;
import org.junit.Test;

import items.Bomb;
import other.Board;
import other.Boulder;
import other.Player;

public class BombTest {
	
	@Test
	public void destroyPlayersThatAreOneGridAwayFromLitBomb() {
		Board b1 = new Board();
		Bomb bomb = new Bomb(b1);
		Player p1 = new Player(b1);
		b1.placeEntity(p1, 0, 1);
		b1.placeEntity(bomb, 0, 0);
		bomb.setBombToLight();
	
		// We have to wait for the bomb to explode before we check if the player
		// has died or not
		try {
			Thread.sleep(bomb.getExplosionTime() + 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, p1.getHealth() == 0);
	}
	
	@Test
	public void noEffectOnPlayersThatAreMoreThanOneGridAway() {
		Board b1 = new Board();
		Bomb bomb = new Bomb(b1);
		Player p1 = new Player(b1);
		Player p2 = new Player(b1);
		
		b1.placeEntity(p1, 0, 0);
		b1.placeEntity(bomb, 0, 1);
		bomb.setBombToLight();
		b1.placeEntity(p2, 0, 5);
		
		// We have to wait for the bomb to explode before we check if the player
		// has died or not
		try {
			Thread.sleep(bomb.getExplosionTime() + 10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, p2.getHealth() == 1);
	}
	
	@Test
	public void destroyBouldersThatAreOneGridAwayFromLitBomb() {
		Board b1 = new Board();
		Bomb bomb = new Bomb(b1);
		Player p1 = new Player(b1);
		Boulder boulder = new Boulder(b1);
		
		b1.placeEntity(p1, 0, 1);
		b1.placeEntity(bomb, 0, 0);
		b1.placeEntity(boulder, 1, 0);	
		
		bomb.setBombToLight();
		try {
			Thread.sleep(bomb.getExplosionTime() + 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(true, boulder.getState() == false);
	}

}
