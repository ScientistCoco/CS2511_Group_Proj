package project;

/**
 * If the player goes through this exit class then the puzzle is complete.
 * @author court
 *
 */
public class Exit extends Entity{

	public Exit(Board board) {
		super(board);
		this.icon = "[ ]";
	}

	public void exitFound(Player player) {
		player.tickObjective(this);
	}
	
	@Override
	public boolean overlappingEffect(Entity entity) {
		// If the overlapping entity is a player
		if (entity instanceof Player) {
			exitFound((Player)entity);
			return true;
		}
		return false;
	}
}
