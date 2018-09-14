package project;

import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Item{

	private Timer timer = new Timer();
	private Integer limitedTime = 10;
	
	public Integer getLimitedTime() {
		return limitedTime;
	}
	public InvincibilityPotion(Board board) {
		super(board);
		this.name = "invincibility potion";
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
