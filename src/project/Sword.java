package project;

public class Sword extends Item {

	private int hitsRemain;
	private Inventory bag;
	
	public Sword(Board board, Inventory bag) {
		super(board);
		this.name = "sword";
		hitsRemain = 5;
		status = ItemStatus.Exist;
		this.bag = bag;
	}
	
	
	public boolean attack(Enemy e) {
		e.deleteHealth();
		hitsRemain--;
		if (disappear()) {
			status = ItemStatus.Disappear;
			bag.removeItem(this);
		}
		return true;
	}
	
	private boolean disappear() {
		if (hitsRemain<=0) {
			return true;
		}
		return false;
	}

	public int getHitsRemain() {
		return this.hitsRemain;
	}

	// With this method we can check if the player position is next to any nearby enemies
	// if it is then we call call the attack() method
	@Override
	public void useItem(Player player) {
		// TODO Auto-generated method stub
		
	}

	/*private boolean canHit(Enemy e) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		if (x-this.getXCoordinate() > 1 || this.getXCoordinate() - x > 1) {
			return false;
		}
		if (y-this.getYCoordinate() > 1 || this.getYCoordinate() - y > 1) {
			return false;
		}
		return true;
	}*/

}
