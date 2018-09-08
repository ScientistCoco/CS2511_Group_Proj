package project;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character {

	public Player(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean useItem(Item it) {
		// TODO
		return false;
	}
	
	public boolean pickUpItem(Item it) {
		// TODO
		return false;
	}
		
	public void getKeyboardInput() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> cmds = new ArrayList<>();
		cmds.add("Up"); cmds.add("Down"); cmds.add("Left"); cmds.add("Right"); cmds.add("Exit");
		
		String cmdInput = null;
		while (cmdInput != "Exit") {
			cmdInput = sc.next();
			if (cmds.contains(cmdInput)) {
				switch (cmdInput) {
				case "Up":
					
				case "Down":
					
				case "Left":
					
				case "Right":
					
				case "Exit":
					break;
				}
			} else {
				System.out.println("Please enter one of the commands: " + String.join(" ", cmds));
			}
		}
		sc.close();
	}
}
