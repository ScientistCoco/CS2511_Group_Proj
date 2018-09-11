package project;

import java.util.HashMap;

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
	public Key(int x, int y, Board board, int key, Inventory in) {
		super(x, y, board);
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
	
	

}
