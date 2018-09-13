package project;

public abstract class Item extends Entity{

	protected String name;
	protected ItemStatus status;
	
	public Item(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}
	
	public String getItemName() {
		return name;
	}
	
	public ItemStatus getStatus() {
		return status;
	}

	@Override
	public boolean affectPlayer(Player player) {
		player.getInventory().addItem(this);
		return false;
	}
	
	/**
	 * When a player passes over an item they will automatically pick it up
	 */
	@Override
	public boolean overlappingEffect(Entity entity) {
		if (entity instanceof Player) {
			affectPlayer((Player)entity);
			board.removeEntity(this);
		}
		return true;
	}
	
	/**
	 * This method is called when the player wants to use the item
	 */
	public abstract void useItem(Player player);
}
