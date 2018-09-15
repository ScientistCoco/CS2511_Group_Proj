package design;

import java.util.Scanner;

import enemies.Hunter;
import items.Arrow;
import items.Bomb;
import items.Key;
import items.Sword;
import items.Treasure;
import other.Board;
import other.Boulder;
import other.Door;
import other.Entity;
import other.Exit;
import other.Pit;
import other.Player;
import other.Switch;
import other.Wall;

public class DesignMode {
	private Board board;
	private Designer designer;
	private Scanner sc;
	
	public DesignMode(Scanner sc) {
		this.board = new Board();
		this.designer = new Designer();
		this.sc = sc;
	}
	
	/**
	 * This method receives an entity type and creates a new instance of that type
	 * @param entity: The entity that we want to make an instance of
	 * @return an instance of the entity requested
	 */
	private Entity makeEntity(EntityTypes entity) {
		switch (entity) {
		case Player :
			return new Player(board);
		case Exit :
			return new Exit(board);
		case Hunter :
			return new Hunter(board);
		case Boulder :
			return new Boulder(board);
		case Switch :
			return new Switch(board);
		case Door :
			return new Door(board, 0);
		case Key :
			//return new Key(board);
			return null;
		case Pit :
			return new Pit(board);
		case Wall :
			return new Wall(board);
		case Arrow :
			return new Arrow(board);
		case Bomb :
			return new Bomb(board);
		case Sword :
			return new Sword(board);
		case Treasure :
			return new Treasure(board);
		}
		return null;
		
	}
	
	/**
	 * This method calls on the designer to input the entity type they want to place on the board
	 * @return the Entity type requested by the designer.
	 */
	public Entity askForEntity() {
		System.out.println("What entity would you like to put on the board?");
		String input = designer.getCmd(sc);
		while (EntityTypes.fromString(input) == null) {
			System.out.println("Please put in a valid entity name");
			input = designer.getCmd(sc);
		}
		
		return makeEntity(EntityTypes.fromString(input));
	}
	
	/**
	 * This method calls on the designer to input a coordinate for where they want to put the entity
	 * @return an int of the designers requested coordinate.
	 */
	public int askForCoordinate() {
		String input = designer.getCmd(sc).trim();
		while (!input.matches("^[0-9]*$")) {
			System.out.println("Please put in a valid coordinate");
			input = designer.getCmd(sc).trim();
		}
		return Integer.parseInt(input);
	}
	
	/**
	 * This method tells the board to put the Entity on the given x & y coordinates. 
	 * @return true/false depending on whether the entity was successfully placed or not
	 */
	public void putOnBoard(Entity entity, int x, int y) {
		board.placeEntity(entity, x, y);
	}
	
	/**
	 * This method does most of the work for designing the board
	 */
	public void doAction() {
		Entity entity = askForEntity();
		
		System.out.println("Please put in a x-coordinate to put this entity on");
		int x = askForCoordinate();
		
		System.out.println("Please put in a y-coordinate to put this entity on");
		int y = askForCoordinate();
		
		while (board.getEntity(x, y) != null) {
			System.out.println("Another object already exists in the given location. Please try a different space.");
			System.out.println("Please put in a x-coordinate to put this entity on");
			x = askForCoordinate();
			System.out.println("Please put in a y-coordinate to put this entity on");
			y = askForCoordinate();
		}
		
		System.out.println("Putting Object: " + entity.getClass().getName() + " at coordinates: " + x + " " + y);
		putOnBoard(entity, x, y);
		board.printBoard();
		
	}
}
