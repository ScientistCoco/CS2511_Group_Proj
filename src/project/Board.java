package project;

public class Board {
	private Object[][] map;
	
	public Board() {
		// Default size if no given width/height
		map = new Object[10][10];
	}
	
	// If width and height are specified
	public Board(int width, int height) {
		map = new Object[width][height];
	}
	
	/**
	 * Method that returns the object at the given coordinates on the board
	 * @param x: The x-coordinate that the user wants to look at
	 * @param y: The y-coordinate that the user wants to look at
	 * @return the object that is located at the given coordinates
	 */
	public Object getEntity(int x, int y) {
		return map[x][y];
	}
	
	/**
	 * Method that places the object at the given coordinates on the board
	 * @pre (x > 0 && x < map.length) && ( y > 0 && y < map.length)
	 * @post true/false
	 * 
	 * @param entity: The entity we want to place on the board
	 * @param x: The x-coordinate that the entity will be placed at
	 * @param y: The y-coordinate that the entity will be placed at
	 * @return true/false if the entity was successfully placed at the given argument
	 */
	public Boolean placeEntity(Object entity, int x, int y) {
		if (getEntity(x, y) == null) {
			map[x][y] = entity;
			return true;
		}
		return false;
	}
	
	/**
	 * Method that removes the object at the given coordinates on the board
	 * @param x
	 * @param y
	 */
	public void removeEntity(int x, int y) {
		map[x][y] = null;
	}
	
	/**
	 * Method that prints out the board and all the entities on it
	 */
	public void printBoard() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == null) {
					System.out.print(" . ");
				} else {
					System.out.print(" o ");
				}
			}
			System.out.print("\n");
		}
	}
}
