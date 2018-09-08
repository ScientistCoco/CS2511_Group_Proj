package project;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character {

	public Player(int x, int y, Board board) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(String direction) {
		// TODO Auto-generated method stub
		switch (direction) {
			case "Up":
				this.move.moveUp(this);
				break;
			case "Down":
				this.move.moveDown(this);
				break;
			case "Left":
				this.move.moveLeft(this);
				break;
			case "Right":
				this.move.moveRight(this);
				break;
		}
		
	}
	
	public boolean useItem(Item it) {
		// TODO
		return false;
	}
	
	public boolean pickUpItem(Item it) {
		// TODO
		return false;
	}
	
	/**
	 * Method that receives the keyboard input from the user, the expected commands are "Up, Down, Left, Right or Exit".
	 * It will execute the respective methods depending on the input received
	 * @return true/false. Returns true if the user wants to quit the game. Else it will return false indicating that 
	 * 						player still wants to play.
	 */
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
				move(cmdInput);
			}
		} else {
			System.out.println("Please enter one of the commands: " + String.join(" ", cmds));
		}
		
		// For some reason, closing the scanner class seems to chuck an exception that the element is out of bounds?
		return false;
	}
	
}
