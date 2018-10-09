package items;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import other.Buff;
import other.Player;

public class InvincibilityPotion extends Item{

	private Timer timer = new Timer();
	private Integer limitedTime = 200;
	
	public Integer getLimitedTime() {
		return limitedTime;
	}
	public InvincibilityPotion(Board board) {
		super(board);
		this.name = "invincibility potion";
		this.icon = " ♟ ";
		this.description = "A potion that grants you invincibility for a limited time";
		this.entityIcon = new ImageView(new Image("icons/invincibility_potion.png"));
	}

	@Override
	public String useItem(final Player player) {
		player.addBuff(Buff.Invincibility);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				player.deleteInvincibility();
			}
			
		}, limitedTime );
		return "You drank the invincibility potion";
	}

}
