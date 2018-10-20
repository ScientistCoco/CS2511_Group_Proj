package enemies;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import other.Direction;
import player.Player;

public class Strategist extends Enemy{

	public Strategist(Board board) {
		super(board);
		this.setEnemyName("Strategist");
		this.entityIcon = new ImageView(new Image("icons/strategist.png"));
	}

	@Override
	public void updateMove(Player player) {
		ArrayList<Direction> directions = player.getPastMoves();
		int x = player.getXCoordinate();
		int y = player.getYCoordinate();
		// predict next move
		Direction d = predictDirection(directions);
		switch(d) {
			case Up:
				y--;
				break;
			case Down:
				y++;
				break;
			case Left:
				x--;
				break;
			case Right:
				x++;
				break;
		}
		this.trackPlayer(x, y);
		
	}


	public Direction predictDirection(ArrayList<Direction> directions) {
		int max = 0;
		Direction toPredict = Direction.Up;
		HashMap<Direction, Integer> cnt = new HashMap<Direction, Integer>();
		for (Direction d : directions) {
			if (!cnt.containsKey(d)) {
				cnt.put(d, new Integer(1));
			}
			cnt.put(d, new Integer(cnt.get(d).intValue()+1));
		}
		for (Direction dd : cnt.keySet()) {
			if (cnt.get(dd).intValue() >= max) {
				max = cnt.get(dd).intValue();
				toPredict = dd;
			}
		}
		return toPredict;
	}
	
	

}
