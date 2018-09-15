package items;

import java.util.Timer;
import java.util.TimerTask;

import other.Board;
import other.Boulder;
import other.Buff;
import other.Character;
import other.Entity;
import other.Player;

public class LitBombBehaviour implements BombBehaviour{
	private Timer timer = new Timer();
	private int xCoordinate;
	private int yCoordinate;
	
	public boolean effect(Board board) {
		// Have to check all the grids around the bomb.
		if (canBeDamaged(this.xCoordinate+1, this.yCoordinate, board)) {
			deleteEntities(this.xCoordinate+1, this.yCoordinate, board);
		} 
		if (canBeDamaged(this.xCoordinate-1, this.yCoordinate, board)) {
			deleteEntities(this.xCoordinate-1, this.yCoordinate, board);
		} 
		if (canBeDamaged(this.xCoordinate, this.yCoordinate-1, board)) {
			deleteEntities(this.xCoordinate, this.yCoordinate-1, board);
		} 
		if (canBeDamaged(this.xCoordinate, this.yCoordinate+1, board)) {
			deleteEntities(this.xCoordinate, this.yCoordinate+1, board);
		}
		
		System.out.println("BOOM!");
		// After the bomb explodes it should also remove itself
		removeBomb(board, this.xCoordinate, this.yCoordinate);
		board.printBoard();
		return true;
	}
	
	/**
	 * This method removes the exploded bomb off the board
	 * @param board
	 */
	private void removeBomb(Board board, int x, int y) {
		Entity e = board.getEntity(x, y);
		board.removeEntity(e);
	}
	
	/**
	 * This method checks the lit bombs nearby surroundings for any entities (player, enemy or bomb) that can
	 * be destroyed when the bomb explodes
	 * @param x
	 * @param y
	 * @return true/false
	 */
	private boolean canBeDamaged(int x, int y, Board board) {
		return (board.getEntity(x, y) instanceof Character || board.getEntity(x, y) instanceof Boulder);
	}
	
	private void deleteEntities(int x, int y, Board board) {
		Entity e = board.getEntity(x, y);
		if (e instanceof Boulder) {
			((Boulder)e).remove();
		}	
		
		if (e instanceof Player) {
			Player p = (Player) e;
			if(p.containBuff(Buff.Invincibility)) {
				return;
			} else {
				((Character)e).deleteHealth();
			}
		}
	}

	@Override
	public boolean useItem(Bomb bomb, Board board) {
		
		this.xCoordinate = bomb.getXCoordinate();
		this.yCoordinate = bomb.getYCoordinate();
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				effect(board);
			}
		}, bomb.getExplosionTime());
		
		return true;
	}

}
