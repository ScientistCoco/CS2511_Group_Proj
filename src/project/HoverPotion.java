package project;

public class HoverPotion extends Item {

	public HoverPotion(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean affectPlayer(Player player) {
		player.addBuff(Buff.Hover);
		return true;
	}

}
