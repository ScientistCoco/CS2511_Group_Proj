package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import enemies.Hunter;
import other.Board;
import other.Player;
import other.Wall;

public class HunterTest {
	@Test
	public void TestHunterMovementX() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 3, 3);
	
		h1.updateMove(p1);
		//System.out.println(h1.getXCoordinate());
		assertTrue(h1.getXCoordinate() == 1);
		assertTrue(h1.getYCoordinate() == 0);
		
	}
	
	@Test
	public void TestHunterMovementY() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		
		b1.placeEntity(h1, 1, 1);
		b1.placeEntity(p1, 1, 3);
		
		h1.updateMove(p1);	
	
		assertTrue(h1.getXCoordinate() == 1);
		assertTrue(h1.getYCoordinate() == 2);
	}
	
	@Test
	public void TestHunterMoveXOnly() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		
		b1.placeEntity(h1, 1, 1);
		b1.placeEntity(p1, 3, 1);
		
		h1.updateMove(p1);
		
		assertTrue(h1.getXCoordinate() == 2);
		assertTrue(h1.getYCoordinate() == 1);
	}
	
	@Test
	public void TestHunterToBeStationary() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		Wall w1 = new Wall(b1);
		
		b1.placeEntity(w1, 2, 1);
		b1.placeEntity(h1, 1, 1);
		b1.placeEntity(p1, 3, 1);
		
		h1.updateMove(p1);
		
		assertTrue(h1.getXCoordinate() == 1);
		assertTrue(h1.getYCoordinate() == 1);
	}
	
	@Test
	public void TestHunterMoveYIfCannotMoveX() {
		Board b1 = new Board();
		Hunter h1 = new Hunter(b1);
		Player p1 = new Player(b1);
		Wall w1 = new Wall(b1);
		
		b1.placeEntity(w1, 1, 0);
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 3, 3);
		
		h1.updateMove(p1);
		
		assertTrue(h1.getXCoordinate() == 0);
		assertTrue(h1.getYCoordinate() == 1);
	}

}
