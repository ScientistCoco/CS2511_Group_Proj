import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Exit;
import project.Objective;

public class ObjectiveJUnitTest {
	Board b1 = new Board();
	Objective o1 = new Objective(new Exit(b1), "Find exit", 1);
	
	@Test
	public void checkCorrectTypeStored() {
		assertEquals(true, o1.getType() instanceof Exit);
	}
	
	@Test
	public void checkCorrectDescriptionStored() {
		assertEquals("Find exit", o1.getDescription());
	}
	
	@Test
	public void checkCorrectAmountRequired() {
		assertEquals(1, o1.getAmountRequired());
	}

}
