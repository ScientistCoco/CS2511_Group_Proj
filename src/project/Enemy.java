package project;

public class Enemy extends Character{
	
	public Enemy(Board board) {
		super(board);
		this.zOrder = 1;
		this.point = new EnemyPoint(); // An enemy is an objective that the player needs to kill
	}

	
	public void updateMove(Player player) {
		if(player.containBuff(Buff.Invincibility)) {
			this.runAway(player.getXCoordinate(), player.getYCoordinate());
		}
	}

	protected void trackPlayer(int x, int y) {
		if(this.xCoordinate != x) {
			if (!moveX(x)) {
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
			if(p.containBuff(Buff.Invincibility)) {
				this.deleteHealth();
			} else {
				p.deleteHealth();
			}
			return true;
		} else if (entity instanceof Arrow) {
			this.deleteHealth();
			board.removeEntity(entity);
			return true;
		}
		return false;
	}
	
	
	protected boolean moveX(int x) {
		if((this.xCoordinate - x) > 0) {
			return this.move.moveLeft(this, this.board);
		} else if ((this.xCoordinate - x) < 0){
			return this.move.moveRight(this, this.board);
		} else {
			return false;
		}
	}
	
	protected boolean moveY(int y) {
		if((this.yCoordinate - y) > 0) {
			return this.move.moveUp(this, this.board);
		} else if ((this.yCoordinate - y) < 0){
			return this.move.moveDown(this, this.board);
		} else {
			return false;
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
