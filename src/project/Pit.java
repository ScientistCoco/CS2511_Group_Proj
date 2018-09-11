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

}
