package items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import other.Player;
import points.TreasurePoint;

public class Treasure extends Item {

	public Treasure(Board board) {
		super(board);
		this.point = new TreasurePoint();
		this.name = "Treasure";
		this.description = "A very shiny piece of treasure.";
		this.icon = " ☆ ";
		this.entityIcon = new ImageView(new Image("icons/treasure.png"));
	}
	
	// The treasure has no actions when it is used
	@Override
	public void useItem(Player player) {

	}

}
