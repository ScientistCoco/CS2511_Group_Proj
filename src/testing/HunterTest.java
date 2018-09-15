package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enemies.Hunter;
import items.HoverPotion;
import other.Board;
import other.Pit;
import other.Player;
import other.Wall;

public class HunterTest {
	Board b1;
	Player p1;
	Pit pit;
	Hunter h1;
	Wall w1;
	
	@Before
	public void setUp() throws Exception {
		b1 = new Board();
		p1 = new Player(b1);
		h1 = new Hunter(b1);
		w1= new Wall(b1);
	}
	
	@Test
	public void TestHunterMovementX() {
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 3, 3);
	
		h1.updateMove(p1);
		//System.out.println(h1.getXCoordinate());
		assertTrue(h1.getXCoordinate() == 1);
		assertTrue(h1.getYCoordinate() == 0);
		
	}
	
	@Test
	public void TestHunterMovementY() {
		b1.placeEntity(h1, 1, 1);
		b1.placeEntity(p1, 1, 3);
		
		h1.updateMove(p1);	
	
		assertTrue(h1.getXCoordinate() == 1);
		assertTrue(h1.getYCoordinate() == 2);
	}
	
	@Test
	public void TestHunterMoveXOnly() {
		b1.placeEntity(h1, 1, 1);
		b1.placeEntity(p1, 3, 1);
		
		h1.updateMove(p1);
		
		assertTrue(h1.getXCoordinate() == 2);
		assertTrue(h1.getYCoordinate() == 1);
	}
	
	@Test
	public void TestHunterToBeStationary() {
		b1.placeEntity(w1, 2, 1);
		b1.placeEntity(h1, 1, 1);
		b1.placeEntity(p1, 3, 1);
		
		h1.updateMove(p1);
		
		assertTrue(h1.getXCoordinate() == 1);
		assertTrue(h1.getYCoordinate() == 1);
	}
	
	@Test
	public void TestHunterMoveYIfCannotMoveX() {	
		b1.placeEntity(w1, 1, 0);
		b1.placeEntity(h1, 0, 0);
		b1.placeEntity(p1, 3, 3);
		
		h1.updateMove(p1);
		
		assertTrue(h1.getXCoordinate() == 0);
		assertTrue(h1.getYCoordinate() == 1);
	}

}
