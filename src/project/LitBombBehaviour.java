package project;

public class LitBombBehaviour extends Entity implements BombBehaviour{

	public LitBombBehaviour(Board board) {
		super(board);
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
		if(c instanceof Player) {
			Player p = (Player) c;
			if(p.containBuff(Buff.Invincibility)) {
				return;
			}
		}
		c.deleteHealth();
	}

}
