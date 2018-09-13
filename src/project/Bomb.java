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

	@Override
	public boolean overlappingEffect(Entity entity) {
		if(entity instanceof Player) {
			Player p = (Player)entity;
			if(!p.containBuff(Buff.Invincibility)) {
				p.deleteHealth();
				return true;
			}else {
				return false;
			}
		}
		return true;
	}
	
	
	
	

}
