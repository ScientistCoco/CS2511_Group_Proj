package items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import other.Direction;
import other.Door;
import other.DoorStatus;
import other.Entity;
import other.Player;

public class Key extends Item{

	private int keyNum;
	
	/**
	 * 
	 * @param board
	 * @param key
	 */
	public Key(Board board, int keyNum) {
		super(board);
		this.keyNum = keyNum;
		this.name = "Key";
		this.description = "A key used to open the door number - " + keyNum;
		this.icon = " ℙ ";
		this.entityIcon = new ImageView(new Image("icons/key.png"));
	}
	
	public boolean openDoor(Door door) {
		if (door.getDoorNum() == keyNum) {
			door.changeStatus(DoorStatus.Open);
			return true;
		}
		return false;
	}
	
	public int getKey() {
		return keyNum;
	}

	@Override
	public String toString() {
		return "Key Number = " + keyNum;
	}
	
	/**
	 * Tries to open a door in the indicated direction.
	 * @param x. The x-coordinate for which the player is trying to open the door at
	 * @param y. The y-coordinate for which the player is trying to open the door at.
	 * @param direction. The direction in which the player is trying to open the door at.
	 * @return true/false depending on whether or not the player was able to successfully open the door
	 */
	public boolean tryOpenDoor(int x, int y, Direction direction) {
		Entity e = null;	
		switch (direction) {
		case Up :
			e = board.getEntity(x, y-1);
			break;
		case Down :
			e = board.getEntity(x, y+1);
			break;
		case Right :
			e = board.getEntity(x+1, y);
			break;
		case Left :
			e = board.getEntity(x-1, y);
			break;
		}
		
		if (e instanceof Door) {
			return openDoor((Door)e);
		}
		return false;
	}
	
	@Override
	public String useItem(Player player) {
		System.out.println("Which direction would you like to use this key?");
		boolean res = tryOpenDoor(player.getXCoordinate(), player.getYCoordinate(), this.getPlayerInputForDirection());
		
		// If the player did not successfully open the door, we add the key back to their inventory
		if (res == false) {
			player.getInventory().addItem(this);
		} 
		return null;
	}

}
