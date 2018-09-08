package project;

public class Entity {
	protected int xCoordinate;
	protected int yCoordinate;
	protected Board board;
	protected MovementBehaviour move;
	
	/**
	 * @pre (x > 0 && x < board.length) && (y > 0 && y < board.length)
	 * @param x
	 * @param y
	 * @param board
	 */
	public Entity (int x, int y, Board board) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.board = board;
		board.placeEntity(this, xCoordinate, yCoordinate);	// We could throw an exception here or an error if the x or y coordinate is out of bounds
		this.move = new MovementBehaviour();
	}
	
	public int getXCoordinate() {
		return this.xCoordinate;
	}
	
	public int getYCoordinate() {
		return this.yCoordinate;
	}
	
	/** Receives a set of coordinates that we want to move this entity too.
	  * It will check with the Board class if it is okay to place this entity
	  * in the new coordinates. If it is then the board class will do the movement
	  * of the entity into the new coordinates, then we just update the entities coordinates.
	  * 
	  * @param x: x-coordinate that we want the entity to be placed at
	  * @param y: y-coordinate that we want the entity to be placed at
	  * @return true/false if the entity can be placed in the given coordinates
	  */
	public boolean setCoordinates(int x, int y) {
		if (board.placeEntity(this, x, y) == true) {
			board.removeEntity(this.xCoordinate, this.yCoordinate);
			this.xCoordinate = x;
			this.yCoordinate = y;
			return true;
		}
		return false;
	}

	public boolean affectPlayer(Player player) {
		return true;
	}
}
