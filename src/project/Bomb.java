package project;

public class Bomb extends Item{

	private BombBehaviour effect;
	private Integer bombExplosionTime;
	
	/**
	 * Instantiates a bomb class that is not yet lit
	 */
	public Bomb(Board board) {
		super(board);
		this.name = "Bomb";
		this.icon = " Ò ";
		effect = new UnlitBombBehaviour();
		this.bombExplosionTime = 1200;
	}
	
	public void changeBehaviour(BombBehaviour b) {
		effect = b;
		if (b instanceof LitBombBehaviour) this.icon = " Õ ";
	}
	
	public Integer getExplosionTime() {
		return this.bombExplosionTime;
	}
	
	/**
	 * This method is called to light the bomb
	 * 
	 */
	public void setBombToLight() {
		this.changeBehaviour(new LitBombBehaviour());
		board.addEntity(this);
		effect.useItem(this, board);
	}
	
	/**
	 * When the bomb is used we assume that this means the player is lighting the bomb
	 * with the intention of placing it on a nearby square to destroy nearby entities.
	 */
	@Override
	public void useItem(Player player) {
		//board.placeEntity(this, player.getXCoordinate() + 1, player.getYCoordinate());
		this.setCoordinates(player.getXCoordinate(), player.getYCoordinate());
		
		System.out.println("Where would you like to place the bomb?");
		while (checkValidPlacement(getPlayerInputForDirection())) {
			System.out.println("Please enter a valid direction for which the bomb can be placed: ");
		}
		setBombToLight();
	}
	
	/**
	 * This method checks whether or not the bomb can be placed in the desired location.
	 * This is used in conjunction with the useItem() method.
	 * @param direction
	 * @return
	 */
	public boolean checkValidPlacement(Direction direction) {
		switch(direction) {
			case Up :
				if (board.placeEntity(this, this.xCoordinate, this.yCoordinate - 1)) return true;
				break;
			case Down :
				if (board.placeEntity(this, this.xCoordinate, this.yCoordinate + 1)) return true;
				break;
			case Right :
				if (board.placeEntity(this, this.xCoordinate + 1, this.yCoordinate)) return true;
				break;
			case Left :
				if (board.placeEntity(this, this.xCoordinate -1, this.yCoordinate)) return true;
				break;
		default:
			break;
		}
		return false;
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
