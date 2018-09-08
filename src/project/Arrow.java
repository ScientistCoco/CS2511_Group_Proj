package project;

public class Arrow extends Item implements ArrowBehaviour {
	private Direction direction;
	private ItemStatus status;
	private Inventory items;
	
	public Arrow(int x, int y, Board board, Inventory items) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
		status = ItemStatus.Exist;
		items = items;
	}

	
	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
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
	
	@Override
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
