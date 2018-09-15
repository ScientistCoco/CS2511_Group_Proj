package design;

import other.Board;
import other.Entity;

public class DesignMode {
	private Board board;
	private Designer designer;
	
	public DesignMode() {
		this.board = new Board();
		this.designer = new Designer();
	}
	
	/**
	 * This method calls on the designer to input the entity type they want to place on the board
	 * @return a string of the designers input
	 */
	public String askForEntity() {
		System.out.println("What entity would you like to put on the board?");
		return designer.getCmd();
	}
	
	/**
	 * This method calls on the designer to input an x-coordinate for where they want to put the entity
	 * @return an int of the designers requested x-coordinate.
	 */
	public String askForXLocation() {
		return null;
	}
	
	/**
	 * This method calls on the designer to input an Y-coordinate for where they want to put the entity
	 * @return an int of the designers requested y-coordinate.
	 */
	public String askForYLocation() {
		return null;
	}
	
	/**
	 * This method tells the board to put the Entity on the given x & y coordinates. 
	 */
	public void putOnBoard(Entity entity, int x, int y) {
		board.placeEntity(entity, x, y);
	}
}
