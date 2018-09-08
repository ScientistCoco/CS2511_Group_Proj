package project;

public class Sword extends Item implements SwordBehaviour{

	private int hitsRemain;
	private Inventory bag;
	
	public Sword(int x, int y, Board board, Inventory bag) {
		super(x, y, board);
		this.name = "Sword";
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

	@Override
	public int getHitsRemain() {
		return this.hitsRemain;
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
