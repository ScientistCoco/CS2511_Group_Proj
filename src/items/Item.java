package items;

import java.util.Scanner;

import other.Board;
import other.Direction;
import other.Entity;
import other.Inventory;
import other.Player;

public abstract class Item extends Entity{

	protected String name;
	protected ItemStatus status;
	protected Inventory items;
	protected String description;	// A description of the item
	
	public Item(Board board) {
		super(board);
	}
	
	public String getItemName() {
		return name;
	}
	
	public ItemStatus getStatus() {
		return status;
	}
	
	public String getDescription() {
		return description;
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
			return affectPlayer((Player)entity);
		}
		return true;
	}
	
	/**
	 * This method will use the scanner class to read user input until a direction is specified.
	 * It is used for the arrow, bomb & sword class where the user can specify in which direction
	 * they want to use the items.
	 * @return a direction enum requested by the user
	 */
	public Direction getPlayerInputForDirection() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String playerInput = sc.nextLine();
		while (Direction.fromString(playerInput) == null) {
			playerInput = sc.nextLine();
		}
		return Direction.fromString(playerInput);
	}
	
	/**
	 * This method is called when the player wants to use the item. Returns a string incase the system
	 * wants to print anything to the console to let the player know the result of their action.
	 */
	public abstract String useItem(Player player);
}
