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
		return map[y][x];
	}
	
	/**
	 * Method that places the object at the given coordinates on the board
	 * @pre (x => 0 && x < map.length) && ( y > 0 && y <= map.length)
	 * @post true/false
	 * 
	 * @param entity: The entity we want to place on the board
	 * @param x: The x-coordinate that the entity will be placed at
	 * @param y: The y-coordinate that the entity will be placed at
	 * @return true/false if the entity was successfully placed at the given argument
	 */
	public boolean placeEntity(Object entity, int x, int y) {
		if ((x >= 0 && x < map.length) && (y >= 0 && y < map.length) && getEntity(x, y) == null) {
			map[y][x] = entity; 
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
		map[y][x] = null;
	}
	
	/**
	 * Method that prints out the board and all the entities on it
	 */
	public void printBoard() {
		// Could have a better way of printing out the board. Make it more polymorphic?
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == null) {
					System.out.print(" . ");
				} else if (map[i][j].getClass().equals(Player.class)){
					System.out.print(" • ");
				} else if (map[i][j].getClass().equals(Wall.class)) {
					System.out.print(" # ");
				}
			}
			System.out.print("\n");
		}
	}
}
