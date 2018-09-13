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
			// the safeDistance can be modified by user
			// when distance between player and coward <= safeDistance, coward runaway.
			double safeDistance = Math.sqrt(2);
			int runDistance = 3;
			//
			double distance = Math.sqrt(Math.pow(offsetX, 2) + Math.pow(offsetY, 2));
		
			if(distance <= safeDistance) {
				for(int i = 0; i< runDistance; i++) {
					this.runAway(x, y);
				}
			} else {
				this.trackPlayer(x, y);
			}
	}
	
	
	/*
	 * 
	 * private void runAway(int x, int y) {
		int i = 0;
		while(i < 3) {
			if(x!= this.xCoordinate) {
				if(!runX(x)) {
					runY(y);
				}
			} else {
				runY(y);
			}
			i++;
		}
	}
	/*
	public boolean runX(int x) {
		if((this.xCoordinate - x) > 0) {
			return this.move.moveRight(this, this.board);
		} else {
			return this.move.moveLeft(this, this.board);
		}
	}

	public void runY(int y) {
		if((this.yCoordinate - y) > 0) {
			this.move.moveDown(this, this.board);
		} else {
			this.move.moveUp(this, this.board);
		}
	}
	*/
}
