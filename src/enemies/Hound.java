package enemies;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import other.Buff;
import player.Player;

public class Hound extends Enemy {
	
	
	private Hunter hunter;

	public Hound(Board board, Hunter hunter) {
		super(board);
		this.setEnemyName("Hound");
		this.hunter = hunter;
		this.entityIcon = new ImageView(new Image("icons/hound.png"));
	}

	@Override
	public void updateMove(Player player) {
		if(player.containBuff(Buff.Invincibility)) {
			this.runAway(player.getXCoordinate(), player.getYCoordinate());
			return;
		}
		int x = player.getXCoordinate();
		int y = player.getYCoordinate();
		int xHunter = this.hunter.getXCoordinate();
		int yHunter = this.hunter.getYCoordinate(); 
		int trackX = x + x - xHunter;
		int trackY = y + y - yHunter;
		
		this.trackPlayer(trackX, trackY);
	}

}
