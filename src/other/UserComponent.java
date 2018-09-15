package other;

import java.util.Scanner;

/**
 * This class reads in lines that the user inputs into the console
 * @author courtneylum
 *
 */
public class UserComponent {
	
	public String getCmd(Scanner sc) {
		String input = sc.nextLine();
		return input;
	}

}
