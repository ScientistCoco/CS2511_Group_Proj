package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enemies.Coward;
import enemies.Hound;
import enemies.Hunter;
import other.Board;
import player.Player;

public class HoundTest {
	Board b1;
	Player p1;
	Hunter h1;
	Hound hound1;
	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		p1 = new Player(b1);
		h1 = new Hunter(b1);
		hound1 = new Hound(b1, h1);
	}
	@Test
	// test when x coordinate is different
	public void TestIfXisDifferent() {
		
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 2, 2);
		b1.placeEntity(hound1, 3, 0);
		
		hound1.updateMove(p1);
		
		assertTrue(hound1.getXCoordinate() == 4);
	}
	
	@Test
	// test when x coordinate is same
	// only y move
	public void TestIfXIsTheSame() {
		
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 1, 1);
		b1.placeEntity(hound1, 2, 4);
		
		hound1.updateMove(p1);
		
		assertTrue(hound1.getXCoordinate() == 2);
		assertTrue(hound1.getYCoordinate() == 3);
	}
	
	@Test
	// test when hunter is moving at the same time
	// 
	public void TestIfHunterMoves() {
		
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 1, 1);
		b1.placeEntity(hound1, 3, 2);
		h1.addHound(hound1);
		hound1.updateMove(p1);
		assertTrue(hound1.getXCoordinate() == 2);
		assertTrue(hound1.getYCoordinate() == 2);
		
		h1.updateMove(p1);
		assertTrue(h1.getXCoordinate() == 1);
		assertTrue(h1.getYCoordinate() == 0);
		
		assertTrue(hound1.getXCoordinate() == 1);
		assertTrue(hound1.getYCoordinate() == 2);
	}

}
