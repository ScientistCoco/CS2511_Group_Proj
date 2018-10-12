package other;

import java.util.ArrayList;

import enemies.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Character {
	
	private ObjectiveComponent1 objectives;
	private ArrayList<Buff> potionBuff;
	private Inventory inventory;
	private ArrayList<Enemy> enemies;
	private ArrayList<Direction> directions;
	private Direction cardinalDirection;	// This is the direction in which the player is facing. Default is down (south)

	public Player(Board board) {
		super(board);
		this.potionBuff = new ArrayList<Buff>();
		inventory = new Inventory();
		enemies = new ArrayList<Enemy>();
		directions = new ArrayList<Direction>();
		this.icon = "P";
		this.zOrder = 1;
		this.entityIcon = new ImageView(new Image("icons/player_front.png"));
		this.cardinalDirection = Direction.Down;
	}
	
	/**
	 * Returns an arraylist<ImageView> of the buffs on the player 
	 */
	public ArrayList<ImageView> getBuffs() {
		ArrayList<ImageView> buffs = new ArrayList<ImageView>();
		for (Buff b : potionBuff) {
			if (b.equals(Buff.Hover)) {
				buffs.add(new ImageView(new Image("icons/hover_buff.png")));
			} else if (b.equals(Buff.Invincibility)) {
				buffs.add(new ImageView(new Image("icons/invincibility_buff.png")));
			}
		}
		return buffs;
	}
	
	public void changeDirection(String d) {
		this.cardinalDirection = Direction.fromString(d);
		// We have to change the icon depending on the direction.
		this.board.removeEntity(this);
		if (this.cardinalDirection.equals(Direction.Down)) {
			this.entityIcon = new ImageView(new Image("icons/player_front.png"));
		} else if (this.cardinalDirection.equals(Direction.Up)) {
			this.entityIcon = new ImageView(new Image("icons/player_up.png"));
		} else if (this.cardinalDirection.equals(Direction.Right)) {
			this.entityIcon = new ImageView(new Image("icons/player_right.png"));
		} else if (this.cardinalDirection.equals(Direction.Left)) {
			this.entityIcon = new ImageView(new Image("icons/player_left.png"));
		}
		this.board.placeEntity(this, this.getXCoordinate(), this.getYCoordinate());
	}
	
	public Direction getCardinalDirection() {
		return this.cardinalDirection;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void addBuff(Buff b) {
		potionBuff.add(b);
	}
	
	public void deleteInvincibility() {
		potionBuff.remove(Buff.Invincibility);
		System.out.println(Buff.Invincibility.name() + " has expired");
	}
	
	public boolean containBuff(Buff b) {
		for (Buff buff : potionBuff) {
			if (buff.equals(b)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method moves the player character in the indicated direction
	 * @param direction
	 */
	public void moveSelf(String direction) {
		this.changeDirection(direction);
		this.move.move(direction, this, board);
	}
	
	public String getObjectiveString() {
		this.objectives = this.board.getObjectivesOnThisBoard();
		return objectives.getStringOfObjectives();
	}
	
	/**
	 * This method checks whether the player has achieved all the objectives or not
	 * @return
	 */
	public boolean checkAllObjectivesCompleted() {
		this.objectives = this.board.getObjectivesOnThisBoard();
		return objectives.checkProgress();
	}
	
	@Override
	public boolean overlappingEffect(Entity entity) {
		// Check if the being passed is null or not. If null then it means its passing over a 'floor', 
		// which is passable, so return true.
		if (entity instanceof Enemy) {
			this.deleteHealth();
		}
		return true;
	}
	
	public void notifyAllEnemies() {
		for (Enemy e : enemies) {
			e.updateMove(this);
		}
	}
	
	public void addDirection(Direction d) {
		directions.add(d);
	}
	
	public ArrayList<Direction> getDirections() {
		return this.directions;
	}
	
	public boolean addEnemy(Enemy e) {
		return enemies.add(e);
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

}
