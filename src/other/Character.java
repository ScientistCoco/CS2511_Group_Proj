package other;

public abstract class Character extends MoveableEntity{
	private Integer health;
	
	public Character(Board board) {
		super(board);
		this.health = 1;
	}
	
	//public abstract void move(String direction);
	
	public void deleteHealth() {
		this.health = this.health - 1;
		if (!checkIfAlive()) {
			this.board.removeEntity(this);
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	/**
	 * This method checks if the Character is still alive or not.
	 * @return true/false
	 */
	public boolean checkIfAlive() {
		// Some characters will have a point associated with them that the player needs to complete
		if (this.getAssociatedPointType() != null && getHealth() != 1) {
			this.point.pointAchieved();
		}
		return getHealth() == 1;
	}
}
