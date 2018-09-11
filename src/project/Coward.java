package project;

public class Coward extends Enemy {
	public Coward(Board board) {
		super(board);
	}

	@Override
	public void updateMove(Player player) {
		int x = player.getXCoordinate();
		int y = player.getYCoordinate();
		int offsetX = x - this.getXCoordinate();
		int offsetY = y - this.getYCoordinate();
		int distance = 1;
		
		
		/*if(offsetX) {
			runAway();
		}*/
		this.trackPlayer(offsetX, offsetY);
	}
	
	
	private void runAway() {
		
	}
	
}
