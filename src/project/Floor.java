package project;

import java.util.ArrayList;
import java.util.Iterator;

public class Floor {
	private ArrayList<Entity> entities;
	
	public Floor () {
		entities = new ArrayList<Entity>();
	}
	
	/**
	 * Method that adds an entity to this floor space.
	 * @param e: The entity to be added
	 * @return true/false if the entity has been added successfully.
	 */
	public boolean addEntity(Entity e) {
		if (canOccupySameSpace(e)) {
			entities.add(e);
			return true;
		}
		return false;
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
		Iterator<Entity> itEntity = this.entities.iterator();
		while (itEntity.hasNext()) {
			if (itEntity.next().overlappingEffect(entityToBeAdded) == false) {
				return false;
			}
		}
		return true;
	}
	
	// This method is used along with getFrontEntity() to compare the priority of two passed in entities
	private Entity cmpTwoEntities(Entity entityA, Entity entityB) {
		if (entityA.getClass() == Exit.class) {
			return entityA;
		} else if (entityB.getClass() == Exit.class) {
			return entityB;
		}
		else if (entityA.getClass() == Player.class || entityA.getClass() == Enemy.class || entityA.getClass() == Wall.class || entityA.getClass() == Boulder.class) {
			return entityA;
		}
		else if (entityB.getClass() == Player.class || entityB.getClass() == Enemy.class || entityB.getClass() == Wall.class || entityB.getClass() == Boulder.class) {
			return entityB;
		}
		return entityA;
	}
	
	/**
	 * This method returns the entity with the highest priority to be displayed on the board.
	 * i.e. A player must appear on top of a key, but a key should not appear on top of a player.
	 * @return Entity with the highest priority to be displayed
	 */
	public Entity getFrontEntity() {
		// Cycle through the arraylist and check the 'priorities' of each entity.
		
		// Priority order (desc): Exit > Player == Enemy == Boulder == Wall > Pit == Potion == Door == Switch
		Entity frontEntity = null;
		for (Entity entity : this.entities) {
			if (frontEntity == null) frontEntity = entity;
			else {
				frontEntity = cmpTwoEntities(frontEntity, entity);
			}
		}
		return frontEntity;
	}
	

}
