package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import enemies.Enemy;
import items.Arrow;
import items.Treasure;
import other.Board;
import other.Boulder;
import other.Direction;
import other.Player;
import other.Switch;

public class ObjectiveComponentTest {

	@Test
	public void testAchievedTreasurePoint() {
		Board board = new Board();
		Player player = new Player(board);
		Treasure treasure = new Treasure(board);
		
		board.placeEntity(player, 0, 0);
		board.placeEntity(treasure, 1, 0);
		board.placeEntity(player, 1, 0);
		
		assertEquals(true, player.checkAllObjectivesCompleted());
	}
	
	@Test
	public void testAchievedSwitchPoint() {
		Board board = new Board();
		Player player = new Player(board);
		Boulder boulder = new Boulder(board);
		Switch s1 = new Switch(board);
		
		board.placeEntity(player, 0, 0);
		board.placeEntity(s1, 2, 0);
		board.placeEntity(boulder, 1, 0);
		board.placeEntity(player, 1, 0);
		
		assertEquals(true, player.checkAllObjectivesCompleted());
	}
	
	@Test
	public void testAchievedEnemyPoint() {
		Board board = new Board();
		Player player = new Player(board);
		Enemy enemy = new Enemy(board);
		Arrow a1 = new Arrow(board);
		
		board.placeEntity(a1, 1, 0);
		board.placeEntity(player, 1, 0);
		board.placeEntity(enemy, 1, 5);
		a1.Fly(Direction.Down);
		
		assertEquals(true, player.checkAllObjectivesCompleted());
	}
}
