package project;

public class Arrow extends Item{
	private Direction direction;
	private ItemStatus status;
	private Inventory items;
	
	public Arrow(Board board, Inventory items) {
		super(board);
		// TODO Auto-generated constructor stub
		this.status = ItemStatus.Exist;
		this.items = items;
	}
	
	
	public Direction getDirection() {
		return direction;
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
					this.move.moveUp(this);
				}
				break;
			case Down:
				while (!destroyed()) {
					this.move.moveDown(this);
				}
				break;
			case Right:
				while (!destroyed()) {
					this.move.moveRight(this);
				}
				break;
			case Left:
				while (!destroyed()) {
					this.move.moveLeft(this);
				}
				break;
		}
		this.status = ItemStatus.Disappear;
		items.removeItem(this);
	}
}
