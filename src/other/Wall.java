package other;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The wall class which prevents other entities from passing through it
 * @author court
 *
 */
public class Wall extends Entity{
	
	public Wall(Board board) {
		super(board);
		this.icon = " # ";
		this.zOrder = 1;
		this.setName("Wall");
		this.entityIcon = new ImageView(new Image("icons/brick_brown_2.png"));
	}
	
	/**
	 * @return false. Nothing is allowed to pass through the walls.
	 */
	@Override
	public boolean overlappingEffect(Entity entity) {
		return false;
	}
}
