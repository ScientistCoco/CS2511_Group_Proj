package project;

public class Sword extends Item {

	private int hitsRemain;
	
	public Sword(Board board) {
		super(board);
		this.name = "sword";
		hitsRemain = 5;
		status = ItemStatus.Exist;
	}
	
	
	public boolean attack(Enemy e) {
		e.deleteHealth();
		hitsRemain--;
		if (disappear()) {
			status = ItemStatus.Disappear;
			items.removeItem(this);
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


	@Override
	public void useItem(Player player) {
		
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
