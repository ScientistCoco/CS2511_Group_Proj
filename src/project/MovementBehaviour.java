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
	
	public void moveRight(Character character) {
		int x = character.getXCoordinate();
		int y = character.getYCoordinate();
		
		character.setCoordinates(x + 1, y);
	}
	
	public void moveLeft(Character character) {
		int x = character.getXCoordinate();
		int y = character.getYCoordinate();
		
		character.setCoordinates(x - 1, y);
	}
}
