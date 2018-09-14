package project;

public abstract class Item extends Entity{

	protected String name;
	protected ItemStatus status;
	protected Inventory items;
	
	public Item(Board board) {
		super(board);
	}
	
	public String getItemName() {
		return name;
	}
	
	public ItemStatus getStatus() {
		return status;
	}

	/**
	 * This method allows the player to pick up the item
	 */
	@Override
	public boolean affectPlayer(Player player) {
		player.getInventory().addItem(this);
		items = player.getInventory();
		board.removeEntity(this);
		return true;
	}
	
	/**
	 * When a player passes over an item they will automatically pick it up
	 */
	@Override
	public boolean overlappingEffect(Entity entity) {
		if (entity instanceof Player) {
			board.removeEntity(this);
			return affectPlayer((Player)entity);
		}
		return true;
	}
	
	/**
	 * This method is called when the player wants to use the item
	 */
	public abstract void useItem(Player player);
}
