package items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import player.Player;
import points.PointType;
import points.Points1;

public class Treasure extends Item {

	public Treasure(Board board) {
		super(board);
		this.point = new Points1(PointType.TreasurePoint);
		this.name = "Treasure";
		this.description = "A very shiny piece of treasure.";
		this.icon = "T";
		this.entityIcon = new ImageView(new Image("icons/treasure.png"));
	}
	
	// The treasure has no actions when it is used
	@Override
	public String useItem(Player player) {
		return null;
	}
	
	/**
	 * When player picks up a piece of treasure it will add to the list of
	 * completed objectives
	 */
	@Override
	public boolean affectPlayer(Player player) {
		player.getInventory().addItem(this);
		this.items = player.getInventory();				
		this.point.pointAchieved();
		// We want to remove the entity after checking if this entity has a point associated
		// with it. This is to prevent the board from deleting the objective with the
		// removeEntity() method.
		board.removeEntity(this);	
		return true;
	}
}
