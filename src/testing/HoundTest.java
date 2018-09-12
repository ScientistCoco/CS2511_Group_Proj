package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Hound;
import project.Hunter;
import project.Player;

public class HoundTest {

	@Test
	// test when x coordinate is different
	public void test1() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		Hound hound1 = new Hound(b1, h1);
		
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 2, 2);
		b1.placeEntity(hound1, 3, 0);
		
		hound1.updateHunter(p1,h1);
		
		assertTrue(hound1.getXCoordinate() == 4);
	}
	
	@Test
	// test when x coordinate is same
	// only y move
	public void test2() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		Hound hound1 = new Hound(b1, h1);
		
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 1, 1);
		b1.placeEntity(hound1, 2, 4);
		
		hound1.updateHunter(p1,h1);
		
		assertTrue(hound1.getXCoordinate() == 2);
		assertTrue(hound1.getYCoordinate() == 3);
	}
	
	@Test
	// test when hunter is moving at the same time
	// 
	public void test3() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		Hound hound1 = new Hound(b1, h1);
		
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 1, 1);
		b1.placeEntity(hound1, 3, 2);
		
		hound1.updateHunter(p1,h1);
		assertTrue(hound1.getXCoordinate() == 2);
		assertTrue(hound1.getYCoordinate() == 2);
		
		h1.updateMove(p1);
		assertTrue(h1.getXCoordinate() == 1);
		assertTrue(h1.getYCoordinate() == 0);
		
		hound1.updateHunter(p1, h1);
		assertTrue(hound1.getXCoordinate() == 1);
		assertTrue(hound1.getYCoordinate() == 2);
	}

}
