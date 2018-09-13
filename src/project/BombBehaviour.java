package project;

public interface BombBehaviour {
	/**
	 * Allows the Bomb to change its state from an unlit bomb to a lit bomb.
	 * @param player : The player who is using the bomb 
	 * @param board : The board for which this bomb is acting on
	 * @return true/false depending on whether the bomb has been used or not.
	 */
	public boolean useItem(Player player, Board board);
}
