package items;

import other.Board;
import other.Door;
import other.DoorStatus;
import other.Inventory;
import other.Player;

public class Key extends Item{

	private int keyNum;
	private Inventory items;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param board
	 * @param key
	 * @param door
	 * @pre key
	 */
	public Key(Board board, int key, Inventory in) {
		super(board);
		keyNum = key;
		items = in;
		this.name = "key";
	}
	
	public boolean openDoor(Door door) {
		if (door.getDoorNum() == keyNum) {
			door.changeStatus(DoorStatus.Open);
			items.removeItem(this);
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
	
	// With use item we can check if the player position is next to a door
	// if it is then we can call the openDoor method
	@Override
	public void useItem(Player player) {
		// TODO Auto-generated method stub
	}

}
