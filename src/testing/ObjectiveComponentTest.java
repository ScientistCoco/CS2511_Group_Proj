package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enemies.Enemy;
import items.Arrow;
import items.HoverPotion;
import items.Item;
import items.Treasure;
import other.Board;
import other.Boulder;
import other.Direction;
import other.Inventory;
import other.Pit;
import other.Player;
import other.Switch;

public class ObjectiveComponentTest {
	Board b1;
	Player p1;
	Treasure treasure;
	Boulder boulder;
	Switch s1;
	Enemy enemy;
	Arrow a1;
	
	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		p1 = new Player(b1);
		treasure = new Treasure(b1);
		boulder = new Boulder(b1);
		s1 = new Switch(b1);
		enemy = new Enemy(b1);
		a1 = new Arrow(b1);
	}
	
	@Test
	public void testAchievedTreasurePoint() {

		b1.placeEntity(p1, 0, 0);
		b1.placeEntity(treasure, 1, 0);
		b1.placeEntity(p1, 1, 0);
		
		assertEquals(true, p1.checkAllObjectivesCompleted());
	}
	
	@Test
	public void testAchievedSwitchPoint() {
		
		b1.placeEntity(p1, 0, 0);
		b1.placeEntity(s1, 2, 0);
		b1.placeEntity(boulder, 1, 0);
		b1.placeEntity(p1, 1, 0);
		
		assertEquals(true, p1.checkAllObjectivesCompleted());
	}
	
	@Test
	public void testAchievedEnemyPoint() {
		
		b1.placeEntity(a1, 1, 0);
		b1.placeEntity(p1, 1, 0);
		b1.placeEntity(enemy, 1, 5);
		a1.Fly(Direction.Down);
		
		assertEquals(true, p1.checkAllObjectivesCompleted());
	}
}
