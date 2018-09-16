package play;

import java.util.ArrayList;
import java.util.Scanner;

import enemies.Enemy;
import other.Board;
import other.Player;
import other.PlayerCmds;
import other.UserComponent;

public class PlayMode {
	private Board board;
	private Player player;
	private UserComponent user;
	private Scanner sc;
	private ArrayList<Enemy> enemies;
	
	// This makes the assumption that the board already has a player placed somewhere.
	public PlayMode(Scanner sc, Board board, Player player) {
		this.sc = sc;
		this.board = board;
		this.player = player;
		this.user = new UserComponent();
		this.enemies = this.board.getEnemyObjects();
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
	
	/**
	 * This method requests the user to enter one of the give commands into the console. 
	 * These commands are related to actions that the player object can do
	 * @return a string of the player command.
	 */
	public String askForCmd() {
		System.out.println("Enter one of the commands: " + getPlayerCmds());
		
		String input = user.getCmd(sc);
		
		while (PlayerCmds.fromString(input) == null) {
			System.out.println("Please put in a valid command.");
			input = user.getCmd(sc);
		}
		
		return input;
	}
	
	/**
	 * This method receives a PlayerCmd type and determines which action to execute on the player object
	 * @param cmd
	 * @return true/false depending on whether the player wants to continue playing or not
	 */
	public boolean decipherInput(String input) {
		PlayerCmds cmd = PlayerCmds.fromString(input);
		
		if (cmd == PlayerCmds.Left || cmd == PlayerCmds.Right || cmd == PlayerCmds.Down || cmd == PlayerCmds.Up) {
			// The player makes a movement
			player.moveSelf(input);
		} 
		else if (cmd == PlayerCmds.Inv) {
			player.getInventory().displayItems();
		} 
		else if (cmd == PlayerCmds.Use_Item) {
			// Ask player for what item they want to use
			System.out.println("Enter the name of the item you want to use:");
			input = user.getCmd(sc);
			player.getInventory().useItem(player, input);
		} else if (cmd == PlayerCmds.Exit) {
			return false;	// The player wants to stop playing
		}
		return true;
	}
	
	/**
	 * This method goes through the list of enemy objects on the board and gets them to 
	 * make their move
	 */
	private void updateEnemyLocations() {
		for (Enemy enemy : this.enemies) {
			enemy.updateMove(this.player);
		}
	}
	
	/**
	 * This method does the handles all the work for the play mode.
	 */
	public void doAction() {
		System.out.println(player.getObjectiveString());
		board.printBoard();
		while (decipherInput(askForCmd())) {
			updateEnemyLocations();
			System.out.println(player.getObjectiveString());
			if (player.checkIfAlive() == false) {
				System.out.println("Game over you have died");
				break;
			}
			
			if (player.checkAllObjectivesCompleted()) {
				System.out.println("Congratulations you have completed this level!");
				break;
			}
			
			board.printBoard();
		}
	}
}
