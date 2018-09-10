package project;

import java.util.ArrayList;

public class Board {
	private Object[][] map;
	private ArrayList<Object> entities;
	
	public Board() {
		// Default size if no given width/height
		map = new Object[10][10];
		this.entities = new ArrayList<>();
	}
	
	// If width and height are specified
	public Board(int width, int height) {
		super();
		map = new Object[width][height];
	}
	
	/**
	 * Method that fills the board with 'floors', we can't have the board
	 * have null objects in its matrix. It wouldn't make sense that a player
	 * is able to move around a null space.
	 */
	public void fillBoardWithFloors() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Entity(i, j, this); 	// Where new entity = 'floor'
			}
		}
	}
	/**
	 * Method that adds entities to the board map.
	 * @param entity: The entity that we want to add to the board map
	 */
	public void addEntity(Object entity) {
		this.entities.add(entity);
	}
	
	/**
	 * Method that removes entities from the board map. This might occur because the 
	 * entity might have died or been picked up.
	 * @param entity: The entity that we want to remove
	 */
	public void removeEntity(Object entity) {
		this.entities.remove(entity);
	}
	
	/**
	 * Method scans through all the entities in the arraylist to update their 
	 * position on the board.
	 * TODO: Consider case where player has same coordinates as another entity, i.e. switch.
	 * 		How will this method know which obj to priortize? 
	 */
	public void updateBoard() {
		for (Object obj : this.entities) {
			Entity entity = (Entity) obj; // Cast to entity type so we can call respective methods
			map[entity.getYCoordinate()][entity.getXCoordinate()] = obj;
		}
	}
	
	/**
	 * Method that returns the object at the given coordinates on the board
	 * @param x: The x-coordinate that the user wants to look at
	 * @param y: The y-coordinate that the user wants to look at
	 * @return the object that is located at the given coordinates
	 */
	public Object getEntity(int x, int y) {
		// Can throw an error if the given x or y coordinate is out of bounds.
		if ((x >= 0 && x < map.length) && (y >= 0 && y < map.length)) {
			return map[y][x];
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
		if ((x >= 0 && x < map.length) && (y >= 0 && y < map.length) && entity.overlappingEffect((Entity)getEntity(x, y))) {
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
		updateBoard();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == null) {
					System.out.print(" . ");
				} else if (map[i][j].getClass().equals(Player.class)){
					System.out.print(" • ");
				} else if (map[i][j].getClass().equals(Wall.class)) {
					System.out.print(" # ");
				} else if (map[i][j].getClass().equals(Exit.class)) {
					System.out.print("[ ]");
				} else if (map[i][j].getClass().equals(Door.class)) {
					System.out.print("[#]");
				}
			}
			System.out.print("\n");
		}
	}
}
