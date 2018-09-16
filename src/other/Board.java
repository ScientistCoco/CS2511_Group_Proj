package other;

import java.util.ArrayList;

import enemies.Enemy;

public class Board {
	private Floor[][] map;
	private ArrayList<Entity> entities;
	
	public Board() {
		// Default size if no given width/height
		map = new Floor[10][10];
		entities = new ArrayList<>();
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
 	 * This method returns all the enemy entities associated with this board.
 	 * @return an arraylist of the enemy entities
 	 */
 	public ArrayList<Enemy> getEnemyObjects() {
 		ArrayList<Enemy> enemies = new ArrayList<>();
 		for (Entity entity : entities) {
 			if (entity instanceof Enemy) {
 				enemies.add((Enemy) entity);
 			}
 		}
 		return enemies;
 	}
 	
	/**
	 * This method returns player entity associated with this board. It searches
	 * based on the entities instance. 
	 * @return player entity or null if nothing was found.
	 */
	public Player getPlayerObject() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Player) {
				return (Player) entities.get(i);
			}
		}
		return null;
	}
	
	/**
	 * This method returns all the objectives that has to be completed on this board 
	 * in order to complete the level
	 * @return an objectiveComponent of all the objectives
	 */
	public ObjectiveComponent getObjectivesOnThisBoard() {
		ObjectiveComponent obj = new ObjectiveComponent();
		
		for (Entity entity : entities) {
			if (entity.getAssociatedPointType() != null) {
				if (getPlayerObject() != null) {
					obj.addObjecitve(entity.getAssociatedPointType());
				}
			}
		}
		
		return obj;
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
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
		// Board checks that the new coordinates are within the board. Then it checks if the passed in entity
		// is allowed to pass over the objects that might occupy the new coordinates.
		if ((x >= 0 && x < map.length) && (y >= 0 && y < map.length)) {
			if (entities.contains(entity) == false) addEntity(entity); // If board does not know about this entity then we add it to the boards entities arraylist
			
			if (map[x][y].addEntity(entity)) {
				entity.setCoordinates(x, y);
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
		removeFromMap(e, e.getXCoordinate(), e.getYCoordinate());
	}
	
	/**
	 * Method that removes the object at the given coordinates on the board as a result of the entity moving.
	 * @param x
	 * @param y
	 */
	public void removeFromMap(Entity e, int x, int y) {
		map[x][y].removeEntity(e);
	}
	
	/**
	 * Method that prints out the board and all the entities on it
	 */
	public void printBoard() {
		//updateBoard();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[j][i].getFrontEntity() == null) {
					System.out.print(" . ");
				}
				else {
					System.out.print(map[j][i].getFrontEntity().getIcon());
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
