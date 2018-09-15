package design;

import other.PlayerCmds;

public enum EntityTypes {
	Player("Player"),
	Exit("Exit"),
	Hunter("Hunter"),
	Boulder("Boulder"),
	Switch("Switch"),
	Door("Door"),
	Key("Key"),
	Pit("Pit"),
	Wall("Wall"),
	Arrow("Arrow"),
	Bomb("Bomb"),
	Sword("Sword"),
	Treasure("Treasure");
	
	private String text;
	
	EntityTypes(String text) {
	    this.text = text;
	}
	
	public String getText() {
		return this.text;
	}

	public static EntityTypes fromString(String text) {
		for (EntityTypes cmd : EntityTypes.values()) {
			if (cmd.text.equalsIgnoreCase(text)) {
				return cmd;
			}
		}
		return null;
	}
}
