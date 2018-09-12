package project;

public class Pit extends Entity{

	public Pit(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean affectPlayer(Player player) {
		if (player.containBuff(Buff.Hover)) {
			return false;
		}
		player.deleteHealth();
		return true;
	}
	
	@Override
	public boolean overlappingEffect(Entity entity) {
		if (entity instanceof Player) {
			return affectPlayer((Player) entity);
			//return true;
		} else if (entity instanceof Boulder) {
			// The boulder will fall through the pit and disappear
			board.removeEntity(entity, entity.getXCoordinate(), entity.getYCoordinate());
			return false;
		}
		return false;
	}
}
