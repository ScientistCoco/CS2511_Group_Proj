package project;

public class Coward extends Enemy {
	public Coward(Board board) {
		super(board);
	}

	@Override
	public void updateMove(Player player) {
		int x = player.getXCoordinate();
		int y = player.getYCoordinate();
		double offsetX = x - this.getXCoordinate();
		double offsetY = y - this.getYCoordinate();
		double safeDistance = Math.sqrt(2);
		double distance = Math.sqrt(Math.pow(offsetX, 2) + Math.pow(offsetY, 2));
		
		if(distance <= safeDistance) {
			runAway();
		} else {
			this.trackPlayer(player.getXCoordinate(), player.getYCoordinate());
		}
	}
	
	
	private void runAway() {
		
	}
	
}
