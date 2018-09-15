package design;

import java.util.Scanner;

public class Designer {
	
	public String getCmd() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		return input;
	}

}
