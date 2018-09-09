package project;

public class LitBombBehaviour extends Entity implements BombBehaviour{

	public LitBombBehaviour(int x, int y, Board board) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean effect() {
		if (isCharacter(this.getXCoordinate()+1, this.getYCoordinate())) {
			deleteCharacter(this.getXCoordinate()+1, this.getYCoordinate());
		} else if (isCharacter(this.getXCoordinate()-1, this.getYCoordinate())) {
			deleteCharacter(this.getXCoordinate()-1, this.getYCoordinate());
		} else if (isCharacter(this.getXCoordinate(), this.getYCoordinate()-1)) {
			deleteCharacter(this.getXCoordinate(), this.getYCoordinate()-1);
		} else if (isCharacter(this.getXCoordinate(), this.getYCoordinate()+1)) {
			deleteCharacter(this.getXCoordinate(), this.getYCoordinate()+1);
		}
		return true;
	}
	
	private boolean isCharacter(int x, int y) {
		return (this.board.getEntity(x, y) instanceof Character);
	}
	
	private void deleteCharacter(int x, int y) {
		Character c = (Character)this.board.getEntity(x, y);
		c.deleteHealth();
	}

}
