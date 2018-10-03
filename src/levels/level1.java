package levels;

import other.Board;
import other.Exit;
import other.Player;
import other.Wall;

public class level1 implements boardLevel {
	
	private Board board;
	private Player player;
	
	// This will be a simple level consisting of just walls and an exit that the
	// player has to find. It is a 10x10 grid.
	public level1 () {
		this.board = new Board();
		this.player = new Player(board);
		buildMap();
	}
	
	/**
	 * This function builds the map
	 */
	public void buildMap() {
		board.placeEntity(player, 1, 1);
		// Construct walls around the edges of the board
		for (int i = 0; i < 10; i ++) {
			board.placeEntity(new Wall(board), i, 0);
			board.placeEntity(new Wall(board), 0, i);
			board.placeEntity(new Wall(board), 9, i);
			board.placeEntity(new Wall(board), i, 9);
		}
		board.placeEntity(new Wall(board), 1, 2);
		board.placeEntity(new Wall(board), 2, 2);
		
		for (int i = 0; i < 8; i ++) {
			board.placeEntity(new Wall(board), 5, i);
		}
		
		for (int i = 8; i > 1; i --) {
			board.placeEntity(new Wall(board), 7, i);
		}
		board.placeEntity(new Exit(board), 8, 8);
	}
	
	@Override
	public Board getBoard() {
		return this.board;
	}

}
