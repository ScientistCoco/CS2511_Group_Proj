package other;

public enum Direction{
	Left("Left"),
	Right("Right"),
	Up("Up"),
	Down("Down");
	
	private String text;
	
	Direction(String text) {
	    this.text = text;
	}
	
	public String getText() {
		return this.text;
	}

	public static Direction fromString(String text) {
		for (Direction b : Direction.values()) {
			if (b.text.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
}
