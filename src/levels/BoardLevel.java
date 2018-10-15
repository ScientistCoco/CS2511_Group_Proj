package levels;

import other.Board;

public interface BoardLevel {
	/**
	 * 
	 * @return board object associated with this level
	 */
	public Board getBoard();
	
	/**
	 * 
	 * @return the level number
	 */
	public int getLevel();
}
