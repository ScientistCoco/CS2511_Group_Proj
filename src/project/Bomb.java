package project;

public class Bomb extends Item{

	private BombBehaviour effect;
	/**
	 * Instantiates a bomb class that is not yet lit
	 */
	public Bomb(Board board) {
		super(board);
		this.name = "Bomb";
		this.icon = " Ò ";
		effect = new UnlitBombBehaviour();
	}
	
	public void changeBehaviour(BombBehaviour b) {
		effect = b;
		if (b instanceof LitBombBehaviour) this.icon = " Õ ";
	}

	/**
	 * When the bomb is used we assume that this means the player is lighting the bomb
	 * with the intention of placing it on a nearby square to destroy nearby entities.
	 */
	@Override
	public void useItem(Player player) {
		board.placeEntity(this, player.getXCoordinate() + 1, player.getYCoordinate());
		this.changeBehaviour(new LitBombBehaviour());
		effect.useItem(player, board);
	}
	
	
	@Override
	public boolean overlappingEffect(Entity entity) {
		// Do not allow a player to pass over a lit bomb, as they are not allowed to pick it up
		if (entity instanceof Player && effect instanceof LitBombBehaviour) {
			return false;
		} 
		super.overlappingEffect(entity);
		return true;
	}
	
	
	
	
	

}
