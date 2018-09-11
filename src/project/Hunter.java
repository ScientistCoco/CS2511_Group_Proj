package project;

public class Hunter extends Enemy {

	public Hunter(Board board) {
		super(board);
	}

	@Override
	public void updateMove(Player player) {
		int x = player.getXCoordinate();
		int y = player.getYCoordinate();
		
		if(x!= this.xCoordinate) {
			if(!moveX(x)) {
				moveY(y);
			}
		} else {
			moveY(y);
		}
		
	}
	
	
	private boolean moveX(int x) {
		if((this.xCoordinate - x) > 0) {
			return this.move.moveLeft(this, this.board);
		} else {
			return this.move.moveRight(this, this.board);
		}
	}
	
	private void moveY(int y) {
		if((this.yCoordinate - y) > 0) {
			this.move.moveUp(this, this.board);
		} else {
			this.move.moveDown(this, this.board);
		}
	}

	
}
