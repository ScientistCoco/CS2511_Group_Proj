package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import enemies.Hunter;
import enemies.Strategist;
import other.Board;
import other.Direction;
import other.Player;

public class StrategistTest {

	@Test
	public void TestPredictDirection() {
		Board b = new Board();
		Player p = new Player(b);
		Strategist s = new Strategist(b);
		Direction d = Direction.Up;
		p.addDirection(d);
		assertTrue(s.predictDirection(p.getDirections()).equals(Direction.Up));
	}
	
	@Test
	public void TestMoveNormalCase() {
		Board b = new Board();
		Player p = new Player(b);
		Strategist s = new Strategist(b);
		Direction d = Direction.Up;
		p.addDirection(d);
		b.placeEntity(p, 3, 3);
		b.placeEntity(s, 1, 1);
		s.updateMove(p);

		assertTrue(s.getXCoordinate() == 2);
		assertTrue(s.getYCoordinate() == 1);
	}
	
	// Hunter and Strategist have the same coordinates, but different action
	@Test
	public void TestMoveRandomCase() {
		Board b = new Board();
		Player p = new Player(b);
		Hunter h = new Hunter(b);
		Strategist s = new Strategist(b);
		Direction d = Direction.Left;
		p.addDirection(d);
		b.placeEntity(p, 3, 3);
		b.placeEntity(s, 2, 6);
		
		s.updateMove(p);
		
		assertTrue(s.getXCoordinate() == 2);
		assertTrue(s.getYCoordinate() == 5);
		
		b.removeEntity(s);
		b.placeEntity(h, 2, 6);
		
		h.updateMove(p);
		assertTrue(h.getXCoordinate() == 3);
		assertTrue(h.getYCoordinate() == 6);
	}

}
