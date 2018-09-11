import static org.junit.Assert.*;

import org.junit.Test;

import project.Board;
import project.Buff;
import project.Player;

public class PlayerJUnitTest {
	Board b1 = new Board();
	Player p1 = new Player(b1);
	
	@Test
	public void testBuffAdded() {
		p1.addBuff(Buff.Invincibility);
		assertEquals(true, p1.containBuff(Buff.Invincibility));
	}

}
