package testing;

import static org.junit.Assert.*;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import project.Board;
import project.Bomb;
import project.Enemy;
import project.Pit;
import project.Player;

public class PlayerTest {

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
