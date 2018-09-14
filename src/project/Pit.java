package project;

public class Pit extends Entity{

	public Pit(Board board) {
		super(board);
		this.icon = " O ";
		this.zOrder = 1;
	}

	@Override
	public boolean affectPlayer(Player player) {
		if (player.containBuff(Buff.Hover)) {
			return true;
		}
		player.deleteHealth();
		return false;
	}
	
	@Override
	public boolean overlappingEffect(Entity entity) {
		if (entity instanceof Player) {
			return affectPlayer((Player) entity);
			//return true;
		} else if (entity instanceof Boulder) {
			// The boulder will fall through the pit and disappear
			((Boulder) entity).remove();
			return true;
		}
		return false;
	}
}
