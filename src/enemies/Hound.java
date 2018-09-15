package enemies;

import other.Board;
import other.Player;

public class Hound extends Enemy {
	
	
	private Hunter hunter;

	public Hound(Board board, Hunter hunter) {
		super(board);
		this.hunter = hunter;
		//hunter = new Hunter(board);
		//xHunter = hunter.getXCoordinate();
		//yHunter = hunter.getYCoordinate();
		
	}

	@Override
	public void updateMove(Player player) {
		int x = player.getXCoordinate();
		int y = player.getYCoordinate();
		int xHunter = this.hunter.getXCoordinate();
		int yHunter = this.hunter.getYCoordinate();
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

	
	/*
	 * public void runFromBuff(Player player) {
		this.runAway(player.getXCoordinate(), player.getYCoordinate());
	}
	*/
	

}
