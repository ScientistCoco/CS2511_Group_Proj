package items;

import other.Board;
import other.Buff;
import other.Player;

public class HoverPotion extends Item {

	public HoverPotion(Board board) {
		super(board);
		this.name = "hover potion";
	}

	@Override
	public void useItem(Player player) {
		player.addBuff(Buff.Hover);
	}

}
