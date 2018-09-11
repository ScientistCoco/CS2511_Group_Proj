package project;

public class Enemy extends Character{

	public Enemy(Board board) {
		super(board);
	}

	
	public void updateMove(Player player) {
		this.move.moveRight(this, this.board);
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
	
	

	
	

}
