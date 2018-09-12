package project;

public class Enemy extends Character{

	public Enemy(Board board) {
		super(board);
	}

	
	public void updateMove(Player player) {
		this.move.moveRight(this, this.board);
	}

	public void updateHunter(Player player, Hunter hunter) {
		this.move.moveRight(this, this.board);
	}

	protected void trackPlayer(int x, int y) {
		if(x!= this.xCoordinate) {
			if(!moveX(x)) {
				moveY(y);
			}
		} else {
			moveY(y);
		}
		
	}
	
	protected void runAway(int x, int y) {
		if(x!= this.xCoordinate) {
			if(!runX(x)) {
				runY(y);
			}
		} else {
			runY(y);
		}
	}
	
	
	@Override
	public boolean overlappingEffect(Entity entity) {
		if (entity instanceof Player) {
			Player p = (Player)entity;
			p.deleteHealth();
			return true;
		}
		return false;
	}
	
	
	protected boolean moveX(int x) {
		if((this.xCoordinate - x) > 0) {
			return this.move.moveLeft(this, this.board);
		} else {
			return this.move.moveRight(this, this.board);
		}
	}
	
	protected void moveY(int y) {
		if((this.yCoordinate - y) > 0) {
			this.move.moveUp(this, this.board);
		} else {
			this.move.moveDown(this, this.board);
		}
	}

	protected boolean runX(int x) {
		if((this.xCoordinate - x) > 0) {
			return this.move.moveRight(this, this.board);
		} else {
			return this.move.moveLeft(this, this.board);
		}
	}

	protected void runY(int y) {
		if((this.yCoordinate - y) > 0) {
			this.move.moveDown(this, this.board);
		} else {
			this.move.moveUp(this, this.board);
		}
	}
	
	

}
