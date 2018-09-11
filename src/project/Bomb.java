package project;

public class Bomb extends Item{

	private BombBehaviour effect;
	
	public Bomb(Board board, BombBehaviour b) {
		super(board);
		effect = b;
	}
	
	public void changeBehaviour(BombBehaviour b) {
		effect = b;
	}

	@Override
	public boolean affectPlayer(Player player) {
		return effect.effect();
	}
	
	

}
