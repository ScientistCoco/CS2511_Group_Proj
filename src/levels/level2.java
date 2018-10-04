package levels;

import items.Key;
import other.Board;
import other.Door;
import other.Exit;
import other.Player;
import other.Wall;

public class level2 implements boardLevel {

	private Board board;
	private Player player;
	
	// This will be a simple level consisting of a door, key and an exit. It is a 10x10 grid.
	public level2 () {
		this.board = new Board();
		this.player = new Player(board);
		buildMap();
	}
	
	public void buildMap() {
		this.board.placeEntity(player, 0, 0);
		for (int i = 0; i < 10; i++) {
			this.board.placeEntity(new Wall(board), 4, i);
		}
		this.board.removeEntity(this.board.getEntity(4, 4));
		this.board.placeEntity(new Door(board, 0), 4, 4);
		this.board.placeEntity(new Key(board, 0), 0, 8);
		this.board.placeEntity(new Exit(board), 8, 8);
	}
	
	@Override
	public Board getBoard() {
		return this.board;
	}
}