package project;

public class UnlitBombBehaviour implements BombBehaviour {

	/**
	 * Unlit bomb has no actions that it can do
	 */
	@Override
	public boolean useItem(Player player, Board board) {
		return false;
	}

}
