package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enemies.Coward;
import other.Board;
import other.Player;

public class CowardTest {
	Board b1;
	Player p1;
	Coward c1;
	
	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		p1 = new Player(b1);
		c1 = new Coward(b1);
	}
	
	@Test
	// test when distance between coward and player is beyond safe_distance
	// it move towards player
	public void TestCloseToPlayer() {

		b1.placeEntity(p1, 4, 4);
		b1.placeEntity(c1, 1, 1);
		
		c1.updateMove(p1);
		
		//System.out.println(c1.getXCoordinate());
		assertTrue(c1.getXCoordinate() == 2);
		assertTrue(c1.getYCoordinate() == 1);
	}
	
	@Test
	// test when distance == safeDistance
	// coward runaway from player
	public void TestRunAwayNormalCase() {

		b1.placeEntity(p1, 4, 4);
		b1.placeEntity(c1, 3, 3);

		c1.updateMove(p1);

		//System.out.println(c1.getYCoordinate());
		assertTrue(c1.getXCoordinate() == 0);
		assertTrue(c1.getYCoordinate() == 3);
	}
	
	@Test
	// test when coward has nowhere to go and stay in the same point(left up)
	public void TestRunAwayIfNowhereToGo() {
		
		b1.placeEntity(p1, 2, 2);
		b1.placeEntity(c1, 1, 1);
		
		c1.updateMove(p1);
		
		assertTrue(c1.getXCoordinate() == 0);
		assertTrue(c1.getYCoordinate() == 0);
		
	}
	
	@Test
	// coward turn direction when reach the end of row
	public void TestRunAwayTurnDirection() {

		b1.placeEntity(p1, 3, 3);
		b1.placeEntity(c1, 2, 2);
		
		c1.updateMove(p1);
		
		assertTrue(c1.getXCoordinate() == 0);
		assertTrue(c1.getYCoordinate() == 1);
		
	}

}
