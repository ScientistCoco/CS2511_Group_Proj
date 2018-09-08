package project;

public class MovementBehaviour {
	public MovementBehaviour() {
		
	}
	
	public void moveUp(Entity e) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		e.setCoordinates(x , y - 1);
	}
	
	public void moveDown(Entity e) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		e.setCoordinates(x, y + 1);
	}
	
	public void moveRight(Entity e) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		e.setCoordinates(x + 1, y);
	}
	
	public void moveLeft(Entity e) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		e.setCoordinates(x - 1, y);
	}
}
