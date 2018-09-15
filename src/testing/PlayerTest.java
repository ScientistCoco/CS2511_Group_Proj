package testing;

import static org.junit.Assert.*;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import enemies.Enemy;
import items.Bomb;
import other.Board;
import other.Buff;
import other.Pit;
import other.Player;

public class PlayerTest {
	
	@Test
	public void testBuffAdded() {
		Board board = new Board();
		Player p1 = new Player(board);
		p1.addBuff(Buff.Invincibility);
		assertEquals(true, p1.containBuff(Buff.Invincibility));
	}

	@Test
	public void TestPlayerDiesInRangeOfExplodingBomb() {
		Board board = new Board();
		Player player = new Player(board);
		Bomb bomb = new Bomb(board);
		
		board.placeEntity(bomb, 3, 4);
		board.placeEntity(player, 4, 4);
		
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
		Board board = new Board();
		Player player = new Player(board);
		Pit pit = new Pit(board);
		
		board.placeEntity(player, 1, 0);
		board.placeEntity(pit, 0, 0);
		board.placeEntity(player, 0, 0);
		
		assertEquals(true, player.getHealth() == 0);
	}
	
	@Test
	public void TestPlayerDiesUponCollisionWithEnemy() {
		Board board = new Board();
		Player player = new Player(board);
		Enemy enemy = new Enemy(board);
		
		board.placeEntity(player, 0, 0);
		board.placeEntity(enemy, 1, 0);
		board.placeEntity(player, 1, 0);
		
		assertEquals(true, player.getHealth() == 0);
	}
}
