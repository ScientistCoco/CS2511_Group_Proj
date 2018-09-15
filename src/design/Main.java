package design;

import java.util.Scanner;

import other.Board;
import other.Exit;
import other.Player;
import play.PlayMode;

public class Main {
	// Class for testing the design mode
	public static void main(String[] args) {
		// When a Scanner is closed, it will close its input source if the source implements the Closeable interface.
		// In that case we have to pass in a scanner class to the modes. The scanner class will be closed at the end of the program
		Scanner sc = new Scanner(System.in);
		DesignMode dm = new DesignMode(sc);
		
		Board board = new Board();
		Player player = new Player(board);
		Exit exit = new Exit(board);
		board.placeEntity(player, 1, 1);
		board.placeEntity(exit, 8, 8);
		
		PlayMode pm = new PlayMode(sc, board, player);
		
		String cmd = "";
		while (!cmd.equals("Exit")) {
			System.out.println("What would you like to do: Play; Design; Exit"); 
			cmd = sc.nextLine();
			switch (cmd) {
			case "Play":
				pm.doAction();
				break;
			case "Design":
				dm.getDesignerCmds();
				break;
			}
	
		}
		sc.close();
	}
}
