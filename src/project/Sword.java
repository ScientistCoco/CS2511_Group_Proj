package project;

public class Sword extends Entity implements SwordBehaviour{

	private int hitsRemain;
	
	public Sword(int x, int y, Board board) {
		super(x, y, board);
		hitsRemain = 5;
	}

	@Override
	public boolean attack(Enemy e) {
		if (canHit(e)) {
			e.deleteHealth();
			hitsRemain--;
			return true;
		}
		return false;
	}

	private boolean canHit(Enemy e) {
		
		return false;
	}

}
