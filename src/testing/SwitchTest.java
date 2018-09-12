package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Boulder;
import project.Player;
import project.Switch;

public class SwitchTest {
	Board b1 = new Board();
	Switch s1 = new Switch(b1);
	Player p1 = new Player(b1);
	Boulder bou1 = new Boulder(b1);
	
	@Test
	public void placeASwitchOnBoardWithCorrectCoordinates() {
		b1.placeEntity(s1, 0, 0);
		assertEquals(true, b1.getEntity(0, 0) == s1);
	}
	
	// Entities that are not a boulder will not turn on the switch when it is stepped on
	@Test
	public void playerSteppingOnTopOfSwitch() {
		b1.placeEntity(s1, 0, 0);
		b1.placeEntity(p1, 0, 0);
		
		assertEquals(false, s1.getState());
;	}
	
	// A switch will be turned on if there is a boulder on top of it
	@Test
	public void boulderPlacedOnTopOfSwitch() {
		b1.placeEntity(s1, 0, 0);
		b1.placeEntity(bou1, 0, 0);
		
		assertEquals(true, s1.getState());
	}
	
	// A switch should not be on if there is no other entities on top of it 
	@Test
	public void nothingOnTopOfSwitch() {
		b1.placeEntity(s1, 0, 0);
		assertEquals(false, s1.getState());
	}
}