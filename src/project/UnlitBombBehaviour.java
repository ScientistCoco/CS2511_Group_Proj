package project;

public class UnlitBombBehaviour extends Item implements BombBehaviour {

	public UnlitBombBehaviour(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean effect() {
		return false;
	}

	/**
	 * Unlit bomb has no actions that it can do
	 */
	@Override
	public void useItem(Player player) {
		
	}

}
