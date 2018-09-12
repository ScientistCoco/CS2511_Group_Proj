package project;

public class Hound extends Enemy {
	
	private int xHunter;
	private int yHunter; 
	
	public Hound(Board board, Hunter hunter) {
		super(board);
		//hunter = new Hunter(board);
		//xHunter = hunter.getXCoordinate();
		//yHunter = hunter.getYCoordinate();
	}

	@Override
	public void updateHound(Player player, Hunter hunter) {
		int x = player.getXCoordinate();
		int y = player.getYCoordinate();
		xHunter = hunter.getXCoordinate();
		yHunter = hunter.getYCoordinate();
		//int offsetX = x - xHunter;
		//int offsetY = y - yHunter;
		
		/*
		 * int trackX = 0;
		 
		int trackY = 0;
		// hunter is left up of player
		if(offsetX > 0 && offsetY > 0) {
			trackX = x + 1;
			trackY = y + 1;
		}
		// left down
		if(offsetX > 0 && offsetY < 0) {
			trackX = x + 1;
			trackY = y - 1;
		}
		// right up
		if(offsetX < 0 && offsetY > 0) {
			trackX = x - 1;
			trackY = y + 1;
		}
		// right down
		if(offsetX < 0 && offsetY < 0) {
			trackX = x - 1;
			trackY = y - 1;
		}
		*/ 
		int trackX = x + x - xHunter;
		int trackY = y + y - yHunter;
		
		this.trackPlayer(trackX, trackY);
	}

}
