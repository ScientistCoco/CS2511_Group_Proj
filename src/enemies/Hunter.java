package enemies;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import other.Buff;
import player.Player;

public class Hunter extends Enemy {

	Hound hound = null;
	
	public Hunter(Board board) {
		super(board);
		this.setEnemyName("Hunter");
		this.icon = " ☻ ";
		this.entityIcon = new ImageView(new Image("icons/hunter.png"));
	}

	@Override
	public void updateMove(Player player) {
		if(player.containBuff(Buff.Invincibility)) {
			this.runAway(player.getXCoordinate(), player.getYCoordinate());
			return;
		}
		this.trackPlayer(player.getXCoordinate(), player.getYCoordinate());
		if (hound != null) {
			notifyHound(player);
		}
	}
	
	public void notifyHound(Player player) {
		hound.updateMove(player);
	}
	
	public void addHound(Hound h) {
		this.hound = h;;
	}
}