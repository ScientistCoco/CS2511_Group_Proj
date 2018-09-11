package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Hunter;
import project.Player;

public class HunterTest {
	@Test
	public void test1() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 3, 3);
		
		h1.updateMove(p1);
		//System.out.println(h1.getXCoordinate());
		assertTrue(h1.getXCoordinate() == 1);
	}
	
	public void test2() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		
		b1.placeEntity(h1, 1, 1);
		b1.placeEntity(p1, 1, 3);
		
		h1.updateMove(p1);	
	
		assertTrue(h1.getYCoordinate() == 2);
	}
	
	public void test3() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		
		b1.placeEntity(h1, 1, 1);
		b1.placeEntity(p1, 3, 1);
		
		h1.updateMove(p1);
		
		assertTrue(h1.getXCoordinate() == 2);
	}

}
