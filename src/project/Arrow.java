package project;

public class Arrow extends Item{
	private ItemStatus status;
	

	public Arrow(Board board) {
		super(board);
		this.name = "arrow";
		// TODO Auto-generated constructor stub
		this.status = ItemStatus.Exist;
	}
	

	public boolean destroyed() {
		// No other entities in this direction
		if (this.board.placeEntity(this, this.getXCoordinate(), this.getYCoordinate())) {
			return false;
		}
		// If arrow hits an enemy
		if (this.board.getEntity(getXCoordinate(), getYCoordinate()) instanceof Enemy) {
			// destroy enemy
			Enemy e = (Enemy)this.board.getEntity(getXCoordinate(), getYCoordinate());
			e.deleteHealth();
			return true;
		}
		return true;
	}
	
	
	public void Fly(Direction d) {
		switch (d) {
			case Up:
				while(!destroyed()) {
					if (!this.move.moveUp(this, this.board)) {
						break;
					}
				}
				break;
			case Down:
				while (!destroyed()) {
					if (!this.move.moveDown(this, this.board)) {
						break;
					}
				}
				break;
			case Right:
				while (!destroyed()) {
					if (!this.move.moveRight(this, this.board)) {
						break;
					}
				}
				break;
			case Left:
				while (!destroyed()) {
					if (!this.move.moveLeft(this, this.board)) {
						break;
					}
				}
				break;
		}
		this.status = ItemStatus.Disappear;
		items.removeItem(this);
	}


	@Override
	public void useItem(Player player) {
		// TODO Auto-generated method stub
		
	}
}
