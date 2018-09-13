package project;

public class Floor extends Entity{

	// A piece on the map that allows other entities to stand on it.
	public Floor(Board board) {
		super(board);
		this.zOrder = 4;
	}	
}
