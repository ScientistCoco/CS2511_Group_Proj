package other;

import java.util.ArrayList;

import enemies.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Character implements PlayerObservable {
	
	private ObjectiveComponent objectives;
	private ArrayList<Buff> potionBuff;
	private Inventory inventory;
	private ArrayList<Direction> pastMoves;
	private Direction cardinalDirection;	// This is the direction in which the player is facing. Default is down (south)
	private ArrayList<PlayerObserver> observers;	// These are the observers that the player will notify when the player has had a change.

	public Player(Board board) {
		super(board);
		this.potionBuff = new ArrayList<Buff>();
		inventory = new Inventory();
		pastMoves = new ArrayList<Direction>();
		this.name = "Player";
		this.icon = "P";
		this.zOrder = 1;
		this.entityIcon = new ImageView(new Image("icons/player_front.png"));
		this.cardinalDirection = Direction.Down;
		this.observers = new ArrayList<PlayerObserver>();
	}
	
	/**
	 * Returns an arraylist<ImageView> of the buffs on the player 
	 */
	public ArrayList<Buff> getBuffs() {
		return this.potionBuff;
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
		notifyObservers();
	}
	
	public void deleteInvincibility() {
		potionBuff.remove(Buff.Invincibility);
		// We can notify any objects observing the player that the invincibility buff has expired
		notifyObservers();
		//System.out.println(Buff.Invincibility.name() + " has expired");
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
		this.addMove(Direction.fromString(direction));
		this.move.move(direction, this, board);
		this.notifyObservers();
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
	public void deleteHealth() {	
		super.deleteHealth();
		notifyObservers();			
	}
	
	@Override
	public boolean overlappingEffect(Entity entity) {
		// Check if the entity being passed is null or not. If null then it means its passing over a 'floor', 
		// which is passable, so return true.
		if (entity instanceof Enemy && this.containBuff(Buff.Invincibility)) {
			((Character)entity).deleteHealth();
		} else {
			this.deleteHealth();
		}
		return true;
	}
	
	
	public void addMove(Direction d) {
		this.pastMoves.add(d);
	}
	
	public ArrayList<Direction> getPastMoves() {
		return this.pastMoves;
	}
	
	@Override
	public void addObserver(PlayerObserver po) {
		this.observers.add(po);
	}

	@Override
	public void removeObserver(PlayerObserver po) {
		this.observers.remove(po);
	}

	@Override
	public void notifyObservers() {
		for (PlayerObserver po : observers) {
			po.update(this);
		}
	}

}
