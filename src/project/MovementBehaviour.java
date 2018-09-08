package project;

public class MovementBehaviour {
	public MovementBehaviour() {
		
	}
	
	public void moveUp(Character character) {
		int x = character.getXCoordinate();
		int y = character.getYCoordinate();
		
		character.setCoordinates(x , y - 1);
	}
	
	public void moveDown(Character character) {
		int x = character.getXCoordinate();
		int y = character.getYCoordinate();
		
		character.setCoordinates(x, y + 1);
	}
	
	// TODO: Add moveRight, moveDown functionality. Also integrate into the player class - i.e. player class calls these methods when user enters right/left.
}
