package project;

public class Hound extends Enemy {
	
	public Hound(Board board) {
		super(board);
	}

	
	@Override
	public void updateMove(Player player) {
		int x0 = player.getXCoordinate();
		int y0 = player.getYCoordinate();

	
		//this.trackPlayer();
	}

}
