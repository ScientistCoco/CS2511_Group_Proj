package project;

public class Board {
	private Floor[][] map;
	
	public Board() {
		// Default size if no given width/height
		map = new Floor[10][10];
		initFloor(10, 10);
	}
	
	// If width and height are specified
	public Board(int width, int height) {
		super();
		map = new Floor[width][height];
		initFloor(width, height);
	}
	
	// For each grid on the map we need to instantiate a floor object
	public void initFloor(int width, int height) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				map[i][j] = new Floor();
			}
		}
	}
	
	/**
	 * Method that returns the object contained on the floor grid
	 * @param x: The x-coordinate that the user wants to look at
	 * @param y: The y-coordinate that the user wants to look at
	 * @return the object that is located at the given coordinates
	 */
	public Entity getEntity(int x, int y) {
		// Can throw an error if the given x or y coordinate is out of bounds.
		if ((x >= 0 && x < map.length) && (y >= 0 && y < map.length)) {
			return map[x][y].getFrontEntity();
		}
		return null;
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
	public boolean placeEntity(Entity entity, int x, int y) {
		//if ((x >= 0 && x < map.length) && (y >= 0 && y < map.length) && !(getEntity(x, y) instanceof Wall)) {
		// Board checks that the new coordinates are within the board. Then it checks if the passed in entity
		// is allowed to pass over the objects that might occupy the new coordinates.
		if ((x >= 0 && x < map.length) && (y >= 0 && y < map.length)) {
			if (map[x][y].addEntity(entity)) {
				entity.setCoordinates(x, y);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that removes the object at the given coordinates on the board
	 * @param x
	 * @param y
	 */
	public void removeEntity(Entity e, int x, int y) {
		map[y][x].removeEntity(e);
	}
	
	/**
	 * Method that prints out the board and all the entities on it
	 */
	public void printBoard() {
		// Could have a better way of printing out the board. Make it more polymorphic?
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[j][i].getFrontEntity() == null) {
					System.out.print(" . ");
				} else if (map[j][i].getFrontEntity().getClass().equals(Player.class)){
					System.out.print(" • ");
				} else if (map[j][i].getFrontEntity().getClass().equals(Wall.class)) {
					System.out.print(" # ");
				} else if (map[j][i].getFrontEntity().getClass().equals(Exit.class)) {
					System.out.print("[ ]");
				} else if (map[j][i].getFrontEntity().getClass().equals(Door.class)) {
					System.out.print("[#]");
				} else if (map[j][i].getFrontEntity().getClass().equals(Pit.class)) {
					System.out.print(" O ");
				}
			}
			System.out.print("\n");
		}
	}
}
