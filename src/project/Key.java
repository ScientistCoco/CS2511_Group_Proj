package project;

import java.util.HashMap;

public class Key extends Item{

	private int keyNum;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param board
	 * @param key
	 * @param door
	 * @pre key
	 */
	public Key(int x, int y, Board board, int key) {
		super(x, y, board);
		keyNum = key;
	}
	
	public boolean openDoor(Door door) {
		if (door.getDoorNum() == keyNum) {
			return true;
		}
		return false;
	}
	
	public int getKey() {
		return keyNum;
	}

}
