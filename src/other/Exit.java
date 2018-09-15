package other;

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
