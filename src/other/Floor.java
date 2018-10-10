package other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

// Make the floor a StackPane so it can hold items
public class Floor extends StackPane {
	private CopyOnWriteArrayList<Entity> entities; 	// CopyOnWriteArrayList allows for concurrent changes
	private ImageView icon;
	
	public Floor () {
		entities = new CopyOnWriteArrayList<Entity>();
		icon = new ImageView(new Image("icons/dirt_1_new.png"));
		this.getChildren().add(icon);	// Add the floor icon to its base.
	}
	
	/**
	 * Method that adds an entity to this floor space.
	 * @param e: The entity to be added
	 * @return true/false if the entity has been added successfully.
	 */
	public boolean addEntity(Entity e) {
		if (canOccupySameSpace(e)) {
			entities.add(e);
			this.getChildren().add(e.getEntityIcon());
			return true;
		}
		return false;
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
		this.getChildren().remove(e.getEntityIcon());
		//System.out.println("Removing " + e.getClass() + " Image: " + e.getEntityIcon() + " before: " + this.getChildren() + " result: " + );
	}
	
	public ArrayList<Entity> getEntities() {
		ArrayList<Entity> res = new ArrayList<Entity>();
		for (Entity e : entities) {
			res.add(e);
		}
		return res;
	}
	
	/**
	 *  This methods helps control number of entities as user want.
	 *  e.g. User want to create no more than three doors in the map.
	 */
	/*
	public int countEntities(Entity e){
		int counter = 0;
		for (Entity e1 : entities) {
			if (e.getClass().equals(e1.getClass()))
				counter++;
		}
		return counter;
	}*/
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
		return (entityA.getZOrder() <= entityB.getZOrder() ? entityA : entityB);
	}
	
	/**
	 * This method returns the entity with the highest priority to be displayed on the board.
	 * i.e. A player must appear on top of a key, but a key should not appear on top of a player.
	 * @return Entity with the highest priority to be displayed
	 */
	public Entity getFrontEntity() {
		// Cycle through the arraylist and check the 'priorities' of each entity.
		
		// Priority order (desc): Exit > Player == Enemy == Pit == Wall > Boulder == Potion == Door == Switch
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