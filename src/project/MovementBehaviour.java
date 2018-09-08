package project;

public class MovementBehaviour {
	public MovementBehaviour() {
		
	}
	
	public boolean moveUp(Entity e) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		return e.setCoordinates(x , y - 1);
	}
	
	public boolean moveDown(Entity e) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		return e.setCoordinates(x, y + 1);
	}
	
	public boolean moveRight(Entity e) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		return e.setCoordinates(x + 1, y);
	}
	
	public boolean moveLeft(Entity e) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		return e.setCoordinates(x - 1, y);
	}
}
