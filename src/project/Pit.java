package project;

public class Pit extends Entity{

	public Pit(int x, int y, Board board) {
		super(x, y, board);
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
