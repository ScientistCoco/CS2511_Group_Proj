package project;

public class HoverPotion extends Item {

	public HoverPotion(Board board) {
		super(board);
	}

	@Override
	public boolean affectPlayer(Player player) {
		player.addBuff(Buff.Hover);
		return true;
	}

	@Override
	public void useItem(Player player) {
		affectPlayer(player);
	}

}
