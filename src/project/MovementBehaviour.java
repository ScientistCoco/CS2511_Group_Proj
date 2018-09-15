package project;

public class MovementBehaviour {
	
	public void move(String direction, Entity e, Board board) {
		switch (direction.toUpperCase()) {
			case "UP":
				moveUp(e, board);
				break;
			case "DOWN":
				moveDown(e, board);
				break;
			case "LEFT":
				moveLeft(e, board);
				break;
			case "RIGHT":
				moveRight(e, board);
				break;
		}
	}
	
	public boolean moveUp(Entity e, Board board) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		//e.setCoordinates(x , y - 1);
		if (board.placeEntity(e, x, y - 1)) {
			board.removeFromMap(e, x, y);
			return true;
		}
		return false;
	}
	
	public boolean moveDown(Entity e, Board board) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		//e.setCoordinates(x, y + 1);
		if (board.placeEntity(e, x, y + 1)) {
			board.removeFromMap(e, x, y);
			return true;
		}
		return false;
	}
	
	public boolean moveRight(Entity e, Board board) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		//e.setCoordinates(x + 1, y);
		if (board.placeEntity(e, x + 1, y)) {
			board.removeFromMap(e, x, y);
			return true;
		}
		return false;
	}
	
	public boolean moveLeft(Entity e, Board board) {
		int x = e.getXCoordinate();
		int y = e.getYCoordinate();
		
		//e.setCoordinates(x - 1, y);
		if (board.placeEntity(e, x - 1, y)) {
			board.removeFromMap(e, x, y);
			return true;
		}
		return false;
	}
}
