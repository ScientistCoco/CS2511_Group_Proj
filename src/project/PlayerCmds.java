package project;

// This is a list of the commands the player can execute
public enum PlayerCmds {
	Left("Left"),
	Right("Right"),
	Up("Up"),
	Down("Down"),
	Inv("Inv"),
	Use_Item("Use Item"),
	Exit("Exit");
	
	private String text;
	
	PlayerCmds(String text) {
	    this.text = text;
	}
	
	public String getText() {
		return this.text;
	}

	public static PlayerCmds fromString(String text) {
		for (PlayerCmds cmd : PlayerCmds.values()) {
			if (cmd.text.equalsIgnoreCase(text)) {
				return cmd;
			}
		}
		return null;
	}

}
