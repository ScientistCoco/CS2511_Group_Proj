package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enemies.Hunter;
import enemies.Strategist;
import items.Bomb;
import other.Board;
import other.Direction;
import other.Pit;
import other.Player;

public class StrategistTest {
	
	Board b1;
	Player p1;
	Bomb bomb;
	Pit pit;
	Strategist s;
	Hunter h;
	
	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		p1 = new Player(b1);
		bomb = new Bomb(b1);
		pit = new Pit(b1);
		s = new Strategist(b1);
		h = new Hunter(b1);
	}
	
	@Test
	public void TestPredictDirection() {

		Direction d = Direction.Up;
		p1.addDirection(d);
		assertTrue(s.predictDirection(p1.getDirections()).equals(Direction.Up));
	}
	
	@Test
	public void TestMoveNormalCase() {

		Direction d = Direction.Up;
		p1.addDirection(d);
		b1.placeEntity(p1, 3, 3);
		b1.placeEntity(s, 1, 1);
		s.updateMove(p1);

		assertTrue(s.getXCoordinate() == 2);
		assertTrue(s.getYCoordinate() == 1);
	}
	
	// Hunter and Strategist have the same coordinates, but different action
	@Test
	public void TestMoveRandomCase() {

		Direction d = Direction.Left;
		p1.addDirection(d);
		b1.placeEntity(p1, 3, 3);
		b1.placeEntity(s, 2, 6);
		
		s.updateMove(p1);
		
		assertTrue(s.getXCoordinate() == 2);
		assertTrue(s.getYCoordinate() == 5);
		
		b1.removeEntity(s);
		b1.placeEntity(h, 2, 6);
		
		h.updateMove(p1);
		assertTrue(h.getXCoordinate() == 3);
		assertTrue(h.getYCoordinate() == 6);
	}

}
