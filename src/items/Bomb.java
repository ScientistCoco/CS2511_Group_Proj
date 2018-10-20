package items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import other.Direction;
import other.Entity;
import player.Player;

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
		this.entityIcon = new ImageView(new Image("icons/bomb_unlit.png"));
		effect = new UnlitBombBehaviour();
		this.bombExplosionTime = 1200;
		this.description = "Put this bomb down to create an explosion";
	}
	
	public void changeBehaviour(BombBehaviour b) {
		effect = b;
		if (b instanceof LitBombBehaviour) {
			this.icon = " Õ ";
			this.entityIcon = new ImageView(new Image("icons/bomb_lit.png"));
		} else if (b instanceof UnlitBombBehaviour) {
			this.entityIcon = new ImageView(new Image("icons/bomb_unlit.png"));
		}
	}
	
	public Integer getExplosionTime() {
		return this.bombExplosionTime;
	}
	
	/**
	 * This method is called to light the bomb
	 * 
	 */
	public void setBombToLight() {
		//this.changeBehaviour(new LitBombBehaviour());
		//board.addEntity(this);
		effect.useItem(this, board);
	}
	
	/**
	 * When the bomb is used we assume that this means the player is lighting the bomb
	 * with the intention of placing it on a nearby square to destroy nearby entities.
	 * @return 
	 */
	@Override
	public String useItem(Player player) {
		//board.placeEntity(this, player.getXCoordinate() + 1, player.getYCoordinate());
		this.setCoordinates(player.getXCoordinate(), player.getYCoordinate());
		this.changeBehaviour(new LitBombBehaviour());
		//System.out.println("Where would you like to place the bomb?");
		//while (checkValidPlacement(getPlayerInputForDirection())) {
		//	System.out.println("Please enter a valid direction for which the bomb can be placed: ");
		//}
		
		// We can check that the player is placing the bomb in an appropriate location, if not then we return a message telling them that
		if (!checkValidPlacement(player.getCardinalDirection())) {
			this.changeBehaviour(new UnlitBombBehaviour());
			player.getInventory().addItem(this);
			return "The bomb can not be placed in this location";
		}
		setBombToLight();
		return "Bomb has been lit";
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
