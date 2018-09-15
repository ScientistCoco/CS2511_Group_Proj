package design;

import java.util.Scanner;

public class Main {
	// Class for testing the design mode
	public static void main(String[] args) {
		// When a Scanner is closed, it will close its input source if the source implements the Closeable interface.
		// In that case we have to pass in a scanner class to the modes. The scanner class will be closed at the end of the program
		Scanner sc = new Scanner(System.in);
		DesignMode dm = new DesignMode(sc);
	
		String cmd = "";
		while (!cmd.equals("Exit")) {
			System.out.println("What would you like to do: Play; Design; Exit"); 
			cmd = sc.nextLine();
			switch (cmd) {
			case "Play":
				break;
			case "Design":
				dm.doAction();
				break;
			}
	
		}
		sc.close();
	}
}
