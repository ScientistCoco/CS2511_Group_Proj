package project;

public interface BombBehaviour {
	/**
	 * Allows the Bomb to change its state from an unlit bomb to a lit bomb.
	 * @param Bomb: The bomb that will be changing its state
	 * @param board : The board for which this bomb is acting on
	 * @return true/false depending on whether the bomb has been used or not.
	 */
	public boolean useItem(Bomb bomb, Board board);
}
