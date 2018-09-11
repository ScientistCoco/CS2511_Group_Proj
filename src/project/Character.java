package project;

public abstract class Character extends Entity{
	protected MovementBehaviour move;
	private Integer health;
	
	public Character(Board board) {
		super(board);
		this.health = 1;
		this.move = new MovementBehaviour();
	}
	
	//public abstract void move(String direction);
	
	public void deleteHealth() {
		this.health -= 1;
	}
	
	public int getHealth() {
		return health;
	}
	
	/**
	 * This method checks if the Character is still alive or not.
	 * @return true/false
	 */
	public boolean checkIfAlive() {
		return getHealth() == 1 ? true : false;
	}
}
