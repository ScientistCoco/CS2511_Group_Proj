package project;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character {
	
	private ObjectiveComponent objectives;
	private ArrayList<Buff> potionBuff;
	private Inventory inventory;
	private ArrayList<Enemy> enemies;
	private ArrayList<Direction> directions;

	public Player(Board board) {
		super(board);
		this.objectives = new ObjectiveComponent();
		this.potionBuff = new ArrayList<Buff>();
		inventory = new Inventory();
		enemies = new ArrayList<Enemy>();
		directions = new ArrayList<Direction>();
		this.icon = " ♀ ";
		this.zOrder = 1;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Returns a string of all the commands that the player can use
	 * @return a string of the player commands
	 */
	private String getPlayerCmds() {
		StringBuilder sb = new StringBuilder();
		for (PlayerCmds cmd : PlayerCmds.values()) {
			sb.append(cmd + " ; ");
		}
		return sb.toString();
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
	 * Method that receives the keyboard input from the user, the expected commands are "Up, Down, Left, Right or Exit".
	 * It will execute the respective methods depending on the input received
	 * @return true/false. Returns true if the user wants to quit the game. Else it will return false indicating that 
	 * 						player still wants to play.
	 */
	@SuppressWarnings("resource")
	public boolean getKeyboardInput() {
		Scanner sc = new Scanner(System.in);
		
		// Receive input from user, check that the user has entered a valid command.
		// If it is one of the movement commands then we call our move() method and pass the direction
		// the character wants to go to.
		System.out.println("Enter one of the commands: " + getPlayerCmds());
		String cmdInput = sc.nextLine();
		PlayerCmds cmdType = PlayerCmds.fromString(cmdInput);
		if (cmdType != null) {
			switch (cmdType) {
			case Exit:
				sc.close();
				return true;
			case Inv:		// This command allows the player to see items in their inventory
				getInventory().displayItems();
				break;
			case Use_Item:
				System.out.println("Enter the name of the item you want to use:");
				getInventory().useItem(this, sc.nextLine());
				break;
			default:
				this.move.move(cmdInput, this, board);
			}
		} else {
			System.out.println("Please enter one of the commands: " + getPlayerCmds());
		}
		
		// For some reason, closing the scanner class seems to chuck an exception that the element is out of bounds?
		return false;
	}
	
	public String getObjectiveString() {
		return objectives.getStringOfObjectives();
	}
	
	/**
	 * This method checks whether the player has achieved all the objectives or not
	 * @return
	 */
	public boolean checkAllObjectivesCompleted() {
		return objectives.checkProgressOfObjectives();
	}
	/**
	 * Method that adds a new objective to the players list of objectives
	 * @param obj
	 */
	public void addObjective(Points obj) {
		this.objectives.addObjecitve(obj);
	}
	
	@Override
	public boolean overlappingEffect(Entity entity) {
		// Check if the being passed is null or not. If null then it means its passing over a 'floor', 
		// which is passable, so return true.
		if (entity != null) {
			return entity.overlappingEffect(this);
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
