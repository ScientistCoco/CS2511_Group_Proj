package project;

import java.util.Timer;
import java.util.TimerTask;

public class LitBombBehaviour implements BombBehaviour{
	private Timer timer = new Timer();
	private Integer explosionTime = 1200;	// This is the time the bomb will stay ignited before it expldoes
	private int xCoordinate;
	private int yCoordinate;
	
	public Integer getExplosionTime() {
		return explosionTime;
	}
	
	public boolean effect(Board board) {
		if (canBeDamaged(this.xCoordinate+1, this.yCoordinate, board)) {
			deleteEntities(this.xCoordinate, this.yCoordinate, board);
		} else if (canBeDamaged(this.xCoordinate-1, this.yCoordinate, board)) {
			deleteEntities(this.xCoordinate-1, this.yCoordinate, board);
		} else if (canBeDamaged(this.xCoordinate, this.yCoordinate-1, board)) {
			deleteEntities(this.xCoordinate, this.yCoordinate-1, board);
		} else if (canBeDamaged(this.xCoordinate, this.yCoordinate+1, board)) {
			deleteEntities(this.xCoordinate, this.yCoordinate+1, board);
		}
		
		System.out.println("BOOM!");
		// After the bomb explodes it should also remove itself
		removeBomb(board, this.xCoordinate, this.yCoordinate);
		return true;
	}
	
	/**
	 * This method removes the exploded bomb off the board
	 * @param board
	 */
	private void removeBomb(Board board, int x, int y) {
		Entity e = board.getEntity(x, y);
		board.removeEntity(e, x, y);
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
		if (e instanceof Player) {
			Player p = (Player) e;
			if(p.containBuff(Buff.Invincibility)) {
				return;
			} else {
				((Character)e).deleteHealth();
			}
		}
		if (e instanceof Boulder) {
			board.removeEntity((Boulder)e, x, y);
			((Boulder)e).remove();
		}	
	}

	@Override
	public boolean useItem(Player player, final Board board) {
		this.xCoordinate = player.getXCoordinate() + 1;
		this.yCoordinate = player.getYCoordinate();
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				effect(board);
			}
		}, this.explosionTime);
		
		return true;
	}

}
