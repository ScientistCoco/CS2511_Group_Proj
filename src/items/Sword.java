package items;

import enemies.Enemy;
import other.Board;
import other.Direction;
import other.Entity;
import other.Player;

public class Sword extends Item {

	private int durability;
	
	public Sword(Board board) {
		super(board);
		this.name = "sword";
		durability = 5;
		status = ItemStatus.Exist;
		this.icon = " 🗡 ";
		this.description = "A very sharp sword, handy for killing enemies.";
	}
	
	/**
	 * Returns a description of the sword as well as the durability remaining on the sword
	 */
	@Override
	public String getDescription() {
		return this.description + " Durability: " + this.durability;
	}
	
	public void attack(Enemy e) {
		e.deleteHealth();
	}
	
	public void swing(Player player, Direction direction) {
		if (getNearbyEntities(player, direction) instanceof Enemy) {
			attack((Enemy) getNearbyEntities(player,direction));
		}
		
		this.durability--;
		if (disappear()) {
			status = ItemStatus.Disappear;
			items.removeItem(this);
		} 
		
		// Keep the sword in the inventory until its durability runs out
		if (status == ItemStatus.Exist) player.getInventory().addItem(this);
	}
	
	private boolean disappear() {
		if (durability<=0) {
			return true;
		}
		return false;
	}

	public int getDurability() {
		return this.durability;
	}
	
	/**
	 * This method returns an Entity object in the given direction, only if an Entity exists in the square.
	 * @param player
	 * @param direction
	 * @return
	 */
	public Entity getNearbyEntities(Player player, Direction direction) {
		// We check in the given direction for whether there is an Enemy in that square
		if (direction == Direction.Left) {
			return board.getEntity(player.getXCoordinate() - 1, player.getYCoordinate());
		} else if (direction == Direction.Right) {
			return board.getEntity(player.getXCoordinate() + 1, player.getYCoordinate());
		} else if (direction == Direction.Up) {
			return board.getEntity(player.getXCoordinate(), player.getYCoordinate() - 1);
		} else if (direction == Direction.Down) {
			return board.getEntity(player.getXCoordinate(), player.getYCoordinate() + 1 );
		}
		return null;
	}

	/**
	 * This method is called when the player wants to use the sword item in their inventory
	 */
	@Override
	public void useItem(Player player) {
		// When sword is used the system will ask the player for which direction they want to swing the sword
		System.out.println("Which direction do you want to swing the sword?");
		swing(player, getPlayerInputForDirection());
	}

}
