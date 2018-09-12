package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Coward;
import project.Player;

public class CowardTest {

	@Test
	// test when distance between coward and player is beyond safe_distance
	// it move towards player
	public void test1() {
		Board b1 = new Board();
		Player p1 = new Player(b1);
		Coward c1 = new Coward(b1);
		
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
	public void test2() {
		Board b1 = new Board();
		Player p1 = new Player(b1);
		Coward c1 = new Coward(b1);
		
		b1.placeEntity(p1, 4, 4);
		b1.placeEntity(c1, 3, 3);

		c1.updateMove(p1);

		//System.out.println(c1.getYCoordinate());
		assertTrue(c1.getXCoordinate() == 0);
		assertTrue(c1.getYCoordinate() == 3);
	}
	
	@Test
	// test when coward has nowhere to go and stay in the same point(left up)
	public void test3() {
		Board b1 = new Board();
		Player p1 = new Player(b1);
		Coward c1 = new Coward(b1);
		
		b1.placeEntity(p1, 2, 2);
		b1.placeEntity(c1, 1, 1);
		
		c1.updateMove(p1);
		
		assertTrue(c1.getXCoordinate() == 0);
		assertTrue(c1.getYCoordinate() == 0);
		
	}
	
	@Test
	// coward turn direction when reach the end of row
	public void test4() {
		Board b1 = new Board();
		Player p1 = new Player(b1);
		Coward c1 = new Coward(b1);
		
		b1.placeEntity(p1, 3, 3);
		b1.placeEntity(c1, 2, 2);
		
		c1.updateMove(p1);
		
		assertTrue(c1.getXCoordinate() == 0);
		assertTrue(c1.getYCoordinate() == 1);
		
	}

}
