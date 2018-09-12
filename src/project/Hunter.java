package project;

public class Hunter extends Enemy {

	public Hunter(Board board) {
		super(board);
	}

	@Override
	public void updateMove(Player player) {
		this.trackPlayer(player.getXCoordinate(), player.getYCoordinate());
	}

}
