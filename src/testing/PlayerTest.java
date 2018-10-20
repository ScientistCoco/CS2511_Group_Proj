package testing;

import static org.junit.Assert.*;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Before;
import org.junit.Test;

import enemies.Enemy;
import items.Bomb;
import items.HoverPotion;
import items.Item;
import other.Board;
import other.Buff;
import other.Inventory;
import other.Pit;
import player.Player;

public class PlayerTest {
	Board b1;
	Player p1;
	Bomb bomb;
	Pit pit;
	
	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		p1 = new Player(b1);
		bomb = new Bomb(b1);
		pit = new Pit(b1);
	}
	
	@Test
	public void testBuffAdded() {
		p1.addBuff(Buff.Invincibility);
		assertEquals(true, p1.containBuff(Buff.Invincibility));
	}

	@Test
	public void TestPlayerDiesInRangeOfExplodingBomb() {
		final Player player = new Player(b1);
		
		b1.placeEntity(bomb, 3, 4);
		b1.placeEntity(player, 4, 4);
		
		bomb.setBombToLight();
		
		// Have to wait for bomb to explode before we test if the player has died
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				assertEquals(true, player.getHealth() == 0);
			}
		}, 3600);
	}
	
	@Test
	public void TestPlayerDiesByFallingIntoAPit() {

		b1.placeEntity(p1, 1, 0);
		b1.placeEntity(pit, 0, 0);
		b1.placeEntity(p1, 0, 0);
		
		assertEquals(true, p1.getHealth() == 0);
	}
	
	@Test
	public void TestPlayerDiesUponCollisionWithEnemy() {

		Enemy enemy = new Enemy(b1);
		
		b1.placeEntity(p1, 0, 0);
		b1.placeEntity(enemy, 1, 0);
		b1.placeEntity(p1, 1, 0);
		
		assertEquals(true, p1.getHealth() == 0);
	}
}
