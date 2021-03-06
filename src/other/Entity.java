package other;

import javafx.scene.image.ImageView;
import player.Player;
import points.PointSubject;
import points.Points1;

public class Entity {	
	protected Integer xCoordinate;	// Use Integer so that null values can be used. This will tell us whether or not the entity has been placed on the boar
	protected Integer yCoordinate;
	protected Board board;
	protected String icon;	// This is the icon that gets displayed on the board
	protected String name;
	protected Integer zOrder;	// Z-order is an ordering of overlapping 2d objects. 
								// The ranks in descending order are: 0 > 1 > 2 > 3 ...
								// 0 : Exit
								// 1 : Player, Enemy, Pit, Wall
								// 2 : Boulder, Potion, Door
								// 3 : Other items. This is the default value
	protected Points1 point;		// Some entities will have objectives that the player needs to complete. By default this will be null
	protected ImageView entityIcon;
	
	/**
	 * @pre (x > 0 && x < board.length) && (y > 0 && y < board.length)
	 * @param x
	 * @param y
	 * @param board
	 */
	public Entity (Board board) {
		this.board = board;
		this.zOrder = 3;
		//this.board.addEntity(this);
	}
	
	/**
	 * This method is used to check whether or not this entity has an objective that needs to be achieved
	 * @return true/false
	 */
	public PointSubject getAssociatedPointType() {
		return this.point;
	}
	
	// TODO: Remove this method once the visual part is finished. It will be replaced with the method getEntityIcon();
	public String getIcon() {
		return this.icon;
	}
	
	public ImageView getEntityIcon() {
		return this.entityIcon;
	}
	
	/**
	 * 
	 * @return the objects zOrder rank
	 */
	public Integer getZOrder() {
		return this.zOrder;
	}
	
	public Integer getXCoordinate() {
		return this.xCoordinate;
	}
	
	public Integer getYCoordinate() {
		return this.yCoordinate;
	}
	
	/** Receives a set of coordinates that we want to move this entity too.
	  * It will check with the Board class if it is okay to place this entity
	  * in the new coordinates. If it is then the board class will do the movement
	  * of the entity into the new coordinates, then we just update the entities coordinates.
	  * 
	  * @param x: x-coordinate that we want the entity to be placed at
	  * @param y: y-coordinate that we want the entity to be placed at
	  * @return true/false if the entity can be placed in the given coordinates
	  */
	public void setCoordinates(int x, int y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	public boolean affectPlayer(Player player) {
		return true;
	}
	
	/**
	 * Each entity should have a defined action for what happens when another
	 * entity overlaps with them. 
	 * @param entity: This is the other entity that will 'pass over' this entity 
	 * @return true/false: depending on whether the passed in object is allowed to pass over this entity.
	 */
	public boolean overlappingEffect(Entity entity) {
		return false;
	}
	
	public String getEntityName() {
		return this.name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
}
