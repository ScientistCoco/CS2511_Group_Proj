package project;

public class Boulder extends Entity{
	
	private boolean state; // This state indicates whether the boulder is on the board or not
	
	public Boulder(Board board) {
		super(board);
		this.state = true;
		this.icon = " • ";
		this.zOrder = 2;
	}
	
	/**
	 * This method removes the boulder from the board,
	 * this might be because it was in the range of a bomb or it fell through a pit
	 */
	public void remove() {
		board.removeEntity(this);
		this.state = false;
	}
	
	/**
	 * Method that tells us whether the boulder is on the board or not
	 * @return
	 */
	public boolean getState() {
		return this.state;
	}
	
	@Override
	public boolean overlappingEffect(Entity entity) {
		if (entity instanceof Player && state) {
			// We need to get the location of the player so we can determine which direction to push this boulder into.
			// However the boulder also needs to check if it is allowed to move into the new adjacent square.
			
			// Determine if boulder is above/below/left/right of the player
			int oldX = this.getXCoordinate();
			int oldY = this.getYCoordinate();
			
			// 'loc' is the location of the boulder relative to the player. i.e:
			//  "Boulder" "Player" : loc = left 
			//  "Player" "Boulder" : loc = right
			String loc = null;
			
			if (entity.getYCoordinate() == this.getYCoordinate()) {
				loc = (entity.getXCoordinate() - this.getXCoordinate() == 1 ? "Left" : "Right"); 
			} else if (entity.getXCoordinate() == this.getXCoordinate()) {
				loc = (entity.getYCoordinate() - this.getYCoordinate() == 1 ? "Up" : "Down");
			}
			
			this.move.move(loc, this, board);
			
			// Then check if the boulder has moved successfully to determine the value to return.
			// i.e. boulder moved = return true. else return false;
			//System.out.println(oldX != this.getXCoordinate() || oldY != this.getYCoordinate());
			return ((oldX != this.getXCoordinate()) || (oldY != this.getYCoordinate()));
		} else if (entity instanceof Player && !state) {
			return true;
		}
		return false;
	}

}
