package levels;

import enemies.Hound;
import enemies.Hunter;
import items.Arrow;
import items.Bomb;
import items.Key;
import items.Sword;
import other.Board;
import other.Boulder;
import other.Door;
import other.Exit;
import other.Pit;
import other.Player;
import other.Switch;
import other.Wall;

public class level2 implements BoardLevel {

	private Board board;
	private Player player;
	private int level;
	
	// This will be a simple level consisting of a door, key and an exit. It is a 10x10 grid.
	public level2 () {
		this.board = new Board();
		this.player = new Player(board);
		this.level = 2;
		buildMap();
	}
	
	public void buildMap() {
		this.board.placeEntity(player, 0, 0);
		for (int i = 0; i < 10; i++) {
			this.board.placeEntity(new Wall(board), 5, i);
		}
		this.board.removeEntity(this.board.getEntity(5, 4));
		this.board.placeEntity(new Door(board, 0), 5, 4);
		this.board.placeEntity(new Key(board, 0), 0, 8);
		this.board.placeEntity(new Exit(board), 8, 8);
		this.board.placeEntity(new Boulder(board), 7, 1);
		this.board.placeEntity(new Switch(board), 7, 2);
		this.board.placeEntity(new Arrow(board), 3, 3);
		this.board.placeEntity(new Sword(board), 2, 5);
		
		Hunter hunter = new Hunter(board);
		this.board.placeEntity(hunter, 3, 8);
		this.board.placeEntity(new Hound(board, hunter), 3, 1);
		this.board.placeEntity(new Pit(board), 3, 9);

		this.board.placeEntity(new Bomb(board), 2, 2);
	}
	
	@Override
	public Board getBoard() {
		return this.board;
	}

	@Override
	public int getLevel() {
		return this.level;
	}
}
