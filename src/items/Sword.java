package items;

import enemies.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		this.entityIcon = new ImageView(new Image("icons/sword.png"));
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
	public String useItem(Player player) {
		// TODO: Should we let the player know if the sword has disappeared when its durability has reached 0?
		// To know if the sword has hit anything we can get the number of enemies on the board before the sword was used
		// then compare it afterwards to see if anything has changed.
		int enemies = board.getEnemyObjects().size();
		// When sword is used the system will ask the player for which direction they want to swing the sword
		//System.out.println("Which direction do you want to swing the sword?");
		swing(player, player.getCardinalDirection());
		if (enemies != board.getEnemyObjects().size()) {
			return "You swang the sword and killed an enemy";
		} 
		return "You swang the sword but did not kill anything";
	}

}
