package other;

import java.util.ArrayList;

import enemies.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Character {
	
	private ObjectiveComponent objectives;
	private ArrayList<Buff> potionBuff;
	private Inventory inventory;
	private ArrayList<Enemy> enemies;
	private ArrayList<Direction> directions;

	public Player(Board board) {
		super(board);
		this.potionBuff = new ArrayList<Buff>();
		inventory = new Inventory();
		enemies = new ArrayList<Enemy>();
		directions = new ArrayList<Direction>();
		this.icon = " ♀ ";
		this.zOrder = 1;
		this.entityIcon = new ImageView(new Image("icons/human.png"));
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
		return objectives.checkProgressOfObjectives();
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
