package project;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character {
	
	private ArrayList<Objective> objectives;
	private ArrayList<Buff> potionBuff;
	private Inventory inventory;
	private ArrayList<Enemy> enemies;
	private ArrayList<Direction> directions;

	public Player(Board board) {
		super(board);
		this.objectives = new ArrayList<>();
		this.potionBuff = new ArrayList<Buff>();
		inventory = new Inventory();
		enemies = new ArrayList<Enemy>();
		directions = new ArrayList<Direction>();
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	
	public void timeCountDown(int time, Buff b) {
		LocalTime current = LocalTime.now();
		LocalTime limitedTime = current.plusSeconds(time);
		int i = 0;
		while((LocalTime.now()) != limitedTime) {
			i++;
		}
		this.deleteBuff(b);
	}
	
	public void addBuff(Buff b) {
		potionBuff.add(b);
		if(b == Buff.Invincibility) {
			int time = 10;
			timeCountDown(time, b);
		}
	}
	
	public void deleteBuff(Buff b) {
		this.potionBuff.remove(b);
	}
	
	public boolean containBuff(Buff b) {
		for (Buff buff : potionBuff) {
			if (buff.equals(b)) {
				return true;
			}
		}
		return false;
	}

	/*@Override
	public void move(String direction) {
		switch (direction) {
			case "Up":
				if (!this.move.moveUp(this)) {
					findEntity(this.getXCoordinate(), this.getYCoordinate()-1);
				}
				break;
			case "Down":
				if (!this.move.moveDown(this)) {
					findEntity(this.getXCoordinate(), this.getYCoordinate()+1);
				}
				break;
			case "Left":
				if (!this.move.moveLeft(this)) {
					findEntity(this.getXCoordinate()-1, this.getYCoordinate());
				}
				break;
			case "Right":
				if (!this.move.moveRight(this)) {
					findEntity(this.getXCoordinate()+1, this.getYCoordinate());
				}
				break;
		}
		
	}*/
	
	
	/**
	 * Method that receives the keyboard input from the user, the expected commands are "Up, Down, Left, Right or Exit".
	 * It will execute the respective methods depending on the input received
	 * @return true/false. Returns true if the user wants to quit the game. Else it will return false indicating that 
	 * 						player still wants to play.
	 */
	@SuppressWarnings("resource")
	public boolean getKeyboardInput() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> cmds = new ArrayList<>();
		cmds.add("Up"); cmds.add("Down"); cmds.add("Left"); cmds.add("Right"); cmds.add("Exit");

		// Receive input from user, check that the user has entered a valid command.
		// If it is one of the movement commands then we call our move() method and pass the direction
		// the character wants to go to.
		System.out.println("Enter one of the commands: " + String.join(" ", cmds));
		String cmdInput = sc.next().replace("^[a-z]", "[A-Z]");
		if (cmds.contains(cmdInput)) {
			switch (cmdInput) {
			case "Exit":
				sc.close();
				return true;
			default:
				this.move.move(cmdInput, this, board);
			}
		} else {
			System.out.println("Please enter one of the commands: " + String.join(" ", cmds));
		}
		
		// For some reason, closing the scanner class seems to chuck an exception that the element is out of bounds?
		return false;
	}
	
	public String getObjectiveString() {
		StringBuilder str = new StringBuilder();
		for (Objective obj : objectives) {
			str.append(obj.getDescription() + " " + obj.getAmountCompleted() + " / " + obj.getAmountRequired() + "\n");
		}
		return str.toString();
	}
	
	/**
	 * Method that increases the amountCompleted attribute in the respective Objective class.
	 * The respective objective class is identified by the Entity type that is passed into the method.
	 * @param entity
	 */
	public void tickObjective(Entity entity) {
		// Find the index in the objectives arraylist that is associated with the passed in entity.
		Integer index = null;
		for (int i = 0; i < objectives.size(); i++) {
			if (objectives.get(i).getType().getClass() == entity.getClass()) index = i;
		}
		if (index != null) objectives.get(index).increaseAmountCompleted();
	}
	
	/**
	 * Method that checks how the player is going with the objectives. 
	 * @return true/false depending on whether the player has completed all the objectives or not.
	 */
	public boolean checkObjectives() {
		int numCompleted = 0;
		
		for (Objective obj : objectives) {
			if (obj.getSatisfiedState()) numCompleted++;
		}
		return numCompleted == objectives.size();
	}
	
	/**
	 * Method that adds a new objective to the players list of objectives
	 * @param obj
	 */
	public void addObjective(Objective obj) {
		this.objectives.add(obj);
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
