package project;

import java.util.ArrayList;

public class Floor {
	ArrayList<Entity> entities;
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	/**
	 * This method checks whether the entity that wants to pass over this 'floor' is allowed
	 * to coexist with any existing entities in the same floor.
	 * @param entityToBeAdded: The entity that wants to step on this floor space
	 * @return true/false if the entity is allowed to step on this floor space
	 */
	public boolean canOccupySameSpace(Entity entityToBeAdded) {
		// Make a call to overlappingEffect for each entity in the arraylist to check
		// whether this new entity is allowed to coexist
	}
	
	/**
	 * This method returns the entity with the highest priority to be displayed on the board.
	 * i.e. A player must appear on top of a key, but a key should not appear on top of a player.
	 * @return Entity with the highest priority to be displayed
	 */
	public Entity getFrontEntity() {
		// Cycle through the arraylist and check the 'priorities' of each entity.
	}
	

}
