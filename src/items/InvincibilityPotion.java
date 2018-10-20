package items;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import other.Buff;
import player.Player;

public class InvincibilityPotion extends Item{

	private Timer timer = new Timer();
	private Integer limitedTime = 5000;	// 2 minutes in milliseconds = 120000
	
	public Integer getLimitedTime() {
		return limitedTime;
	}
	public InvincibilityPotion(Board board) {
		super(board);
		this.name = "Invincibility Potion";
		this.icon = " ♟ ";
		this.description = "A potion that grants you invincibility for a limited time";
		this.entityIcon = new ImageView(new Image("icons/invincibility_potion.png"));
	}

	@Override
	public String useItem(Player player) {
		player.addBuff(Buff.Invincibility);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// Use runLater so it runs on the same thread as the JavaFX application,
				// hence allowing the buff icon to be deleted at the same time too.
				Platform.runLater(() -> player.deleteInvincibility());
			}
			
		}, limitedTime );
		return "You drank the invincibility potion";
	}

}
