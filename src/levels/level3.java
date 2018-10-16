package levels;

import enemies.Hunter;
import items.HoverPotion;
import items.InvincibilityPotion;
import items.Treasure;
import other.Board;
import other.Pit;
import other.Player;
import other.Wall;

/**
 * This board level has a invincibility buff potion
 * @author court
 *
 */
public class level3 implements BoardLevel {
	private Board board;
	private Player player;
	private int level;
	
	// This will be a simple level consisting of a door, key and an exit. It is a 10x10 grid.
	public level3 () {
		this.board = new Board();
		this.player = new Player(board);
		this.level = 3;
		buildMap();
	}
	
	public void buildMap() {
		this.board.placeEntity(player, 3, 0);
		
		for (int i = 0; i < 7; i++) {
			this.board.placeEntity(new Wall(board), i, 2);
		}
		
		for (int i = 0; i < 7; i++) {
			this.board.placeEntity(new Wall(board), 8, i);
		}
		
		this.board.placeEntity(new Treasure(board), 9, 0);
		this.board.placeEntity(new InvincibilityPotion(board), 4, 4);
		this.board.placeEntity(new Hunter(board), 9, 2);
		this.board.placeEntity(new HoverPotion(board), 2, 4);
		this.board.placeEntity(new Pit(board), 2, 6);
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
