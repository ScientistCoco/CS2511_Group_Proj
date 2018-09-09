package project;

public class UnlitBombBehaviour extends Entity implements BombBehaviour {

	public UnlitBombBehaviour(int x, int y, Board board) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean effect() {
		return false;
	}

}
