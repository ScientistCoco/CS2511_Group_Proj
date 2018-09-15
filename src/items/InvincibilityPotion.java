package items;

import java.util.Timer;
import java.util.TimerTask;

import other.Board;
import other.Buff;
import other.Player;

public class InvincibilityPotion extends Item{

	private Timer timer = new Timer();
	private Integer limitedTime = 10;
	
	public Integer getLimitedTime() {
		return limitedTime;
	}
	public InvincibilityPotion(Board board) {
		super(board);
		this.name = "invincibility potion";
		this.icon = " ♟ ";
	}

	@Override
	public void useItem(Player player) {
		player.addBuff(Buff.Invincibility);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				player.deleteInvincibility();
			}
			
		}, limitedTime );
	}

}
