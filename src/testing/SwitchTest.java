package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import other.Board;
import other.Boulder;
import player.Player;
import switchObj.Switch;

public class SwitchTest {
	Board b1 = new Board();
	Switch s1 = new Switch(b1);
	Player p1 = new Player(b1);
	Boulder bou1 = new Boulder(b1);
	
	@Test
	public void placeASwitchOnBoardWithCorrectCoordinates() {
		b1.placeEntity(s1, 2, 2);
		assertEquals(true, b1.getEntity(2, 2) == s1);
	}
	
	// Entities that are not a boulder will not turn on the switch when it is stepped on
	@Test
	public void playerSteppingOnTopOfSwitch() {
		b1.placeEntity(s1, 2, 2);
		b1.placeEntity(p1, 2, 2);
		
		assertEquals(false, s1.getState());
;	}
	
	// A switch will be turned on if there is a boulder on top of it
	@Test
	public void boulderPlacedOnTopOfSwitch() {
		b1.placeEntity(s1, 3, 0);
		b1.placeEntity(bou1, 3, 0);
		
		assertEquals(true, s1.getState());
	}
	
	// A switch should not be on if there is no other entities on top of it 
	@Test
	public void nothingOnTopOfSwitch() {
		b1.placeEntity(s1, 9, 3);
		assertEquals(false, s1.getState());
	}
}
