package other;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import points.ExitPoint;

/**
 * If the player goes through this exit class then the puzzle is complete.
 * @author court
 *
 */
public class Exit extends Entity{
	
	public Exit(Board board) {
		super(board);
		this.icon = "[ ]";
		this.point = new ExitPoint();
		this.entityIcon = new ImageView(new Image("icons/exit.png"));
	}
	
	@Override
	public boolean overlappingEffect(Entity entity) {
		// If the overlapping entity is a player
		if (entity instanceof Player) {
			point.pointAchieved();
			return true;
		} 
		return false;
	}
}
