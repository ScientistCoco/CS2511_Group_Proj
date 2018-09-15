package items;

import other.Board;

public class UnlitBombBehaviour implements BombBehaviour {

	/**
	 * Unlit bomb has no actions that it can do
	 */
	@Override
	public boolean useItem(Bomb bomb, Board board) {
		return false;
	}

}
