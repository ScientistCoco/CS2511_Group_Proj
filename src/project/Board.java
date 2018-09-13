package project;

import java.util.ArrayList;

public class Board {
	private Entity[][] map;
	private ArrayList<Entity> entities;
	
	public Board() {
		// Default size if no given width/height
		map = new Entity[10][10];
		entities = new ArrayList<>();
	}
	
	// If width and height are specified
	public Board(int width, int height) {
		super();
		map = new Entity[width][height];
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	/**
	 * This method checks all the 'alive' entities and their coordinates, checking
	 * that their coordinates correspond to that on the board.
	 */
	public void updateBoard() {
		for (Entity entity : entities) {
			// Suppose we have two entities that occupy a square, we need to determine their
			// zOrders to determine which entity gets 'drawn' on the board.
			if (entity.getXCoordinate() != null) {
				Entity e = map[entity.getXCoordinate()][entity.getYCoordinate()];
				if (e != null) {
					// If the entity we are checking has a higher zorder then the one occupying
					// the space then we will replace the existing entity with the higher zorder one.
					if (entity.getZOrder() < e.getZOrder()) {
						map[entity.getXCoordinate()][entity.getYCoordinate()] = entity;
					}
				} else {
					map[entity.getXCoordinate()][entity.getYCoordinate()] = entity;
				}
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
			return map[x][y];
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
		// Board checks that the new coordinates are within the board. Then it checks if the passed in entity
		// is allowed to pass over the objects that might occupy the new coordinates.
		if ((x >= 0 && x < map.length) && (y >= 0 && y < map.length)) {
			Entity e = map[x][y];
			if (e == null || e.overlappingEffect(entity)) {
				entity.setCoordinates(x, y);
				updateBoard();
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * Method that removes the entity from the map & game as a result of the entity dying.
	 * @param e
	 */
	public void removeEntity(Entity e) {
		this.entities.remove(e);
		removeFromMap(e.getXCoordinate(), e.getYCoordinate());
	}
	
	/**
	 * Method that removes the object at the given coordinates on the board as a result of the entity moving.
	 * @param x
	 * @param y
	 */
	public void removeFromMap(int x, int y) {
		map[x][y] = null;
	}
	
	/**
	 * Method that prints out the board and all the entities on it
	 */
	public void printBoard() {
		updateBoard();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[j][i] == null) {
					System.out.print(" . ");
				}
				else {
					System.out.print(map[j][i].getIcon());
				}
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Method that check whether the coordinate is out of the board
	 * @post true/false
	 * @param x
	 * @param y
	 */
	
	public boolean outOfBoard(int x, int y) {
		if(x > map.length || y > map.length || x < 0 || y < 0) {
			return true;
		}
		return false;
	}
}
