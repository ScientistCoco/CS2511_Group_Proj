package project;

public class Bomb extends Item{

	private BombBehaviour effect;
	
	public Bomb(Board board, BombBehaviour b) {
		super(board);
		this.name = "Bomb";
		effect = b;
	}
	
	public void changeBehaviour(BombBehaviour b) {
		effect = b;
	}

	/**
	 * When the bomb is used we assume that this means the player is lighting the bomb
	 * with the intention of placing it on a nearby square to destroy nearby entities.
	 */
	@Override
	public void useItem(Player player) {
		this.changeBehaviour(new LitBombBehaviour(board));
		this.effect.useItem(player);
	}
	
	/*
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
	*/
	
	
	
	

}
