package project;

import java.util.Timer;
import java.util.TimerTask;

public class LitBombBehaviour extends Item implements BombBehaviour{
	private Timer timer;
	
	public LitBombBehaviour(Board board) {
		super(board);
		timer = new Timer();
	}
	
	@Override
	public boolean effect() {
		if (canBeDamaged(this.getXCoordinate()+1, this.getYCoordinate())) {
			deleteCharacter(this.getXCoordinate()+1, this.getYCoordinate());
		} else if (canBeDamaged(this.getXCoordinate()-1, this.getYCoordinate())) {
			deleteCharacter(this.getXCoordinate()-1, this.getYCoordinate());
		} else if (canBeDamaged(this.getXCoordinate(), this.getYCoordinate()-1)) {
			deleteCharacter(this.getXCoordinate(), this.getYCoordinate()-1);
		} else if (canBeDamaged(this.getXCoordinate(), this.getYCoordinate()+1)) {
			deleteCharacter(this.getXCoordinate(), this.getYCoordinate()+1);
		}
		board.removeEntity(this, this.xCoordinate, this.yCoordinate);
		System.out.println("BOOM!");
		return true;
	}
	
	/**
	 * This method checks the lit bombs nearby surroundings for any entities (player, enemy or bomb) that can
	 * be destroyed when the bomb explodes
	 * @param x
	 * @param y
	 * @return true/false
	 */
	private boolean canBeDamaged(int x, int y) {
		return (this.board.getEntity(x, y) instanceof Character || this.board.getEntity(x, y) instanceof Boulder);
	}
	
	private void deleteCharacter(int x, int y) {
		Entity e = this.board.getEntity(x, y);
		if(e instanceof Player) {
			Player p = (Player) e;
			if(p.containBuff(Buff.Invincibility)) {
				return;
			} else {
				((Character)e).deleteHealth();
			}
		}
		
		else if (e instanceof Boulder) {
			board.removeEntity(e, x, y);
			((Boulder)e).remove();
		}	
	}

	@Override
	public void useItem(Player player) {
		this.xCoordinate = player.xCoordinate + 1;
		this.yCoordinate = player.yCoordinate;
		board.placeEntity(this, this.xCoordinate, this.yCoordinate);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				effect();
			}
		}, 1200);
	}

}
