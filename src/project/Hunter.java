package project;

public class Hunter extends Enemy {

	public Hunter(Board board) {
		super(board);
	}

	@Override
	public void updateMove(Player player) {
		if(player.containBuff(Buff.Invincibility)) {
			this.runAway(player.getXCoordinate(), player.getYCoordinate());
		} else {
			this.trackPlayer(player.getXCoordinate(), player.getYCoordinate());
	
		}
	}
}