package project;

public class Entity {
	protected Integer xCoordinate;	// Use Integer so that null values can be used. This will tell us whether or not the entity has been placed on the boar
	protected Integer yCoordinate;
	protected Board board;
	protected MovementBehaviour move;
	protected String icon;	// This is the icon that gets displayed on the board
	protected Integer zOrder;	// Z-order is an ordering of overlapping 2d objects. 
								// The ranks in descending order are: 0 > 1 > 2 > 3 ...
								// 0 : Exit
								// 1 : Player, Enemy, Pit, Wall
								// 2 : Boulder, Potion, Door
								// 3 : Other items. This is the default value
	
	/**
	 * @pre (x > 0 && x < board.length) && (y > 0 && y < board.length)
	 * @param x
	 * @param y
	 * @param board
	 */
	public Entity (Board board) {
		this.board = board;
		this.move = new MovementBehaviour();
		this.zOrder = 3;
		this.board.addEntity(this);
	}
	
	public String getIcon() {
		return this.icon;
	}
	
	/**
	 * 
	 * @return the objects zOrder rank
	 */
	public Integer getZOrder() {
		return this.zOrder;
	}
	
	public Integer getXCoordinate() {
		return this.xCoordinate;
	}
	
	public Integer getYCoordinate() {
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
	public void setCoordinates(int x, int y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	public boolean affectPlayer(Player player) {
		return true;
	}
	
	/**
	 * Each entity should have a defined action for what happens when another
	 * entity overlaps with them. 
	 * @param entity: This is the other entity that will 'pass over' this entity 
	 * @return true/false: depending on whether the passed in object is allowed to pass over this entity.
	 */
	public boolean overlappingEffect(Entity entity) {
		return false;
	}
}
