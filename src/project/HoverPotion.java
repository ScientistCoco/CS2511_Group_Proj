package project;

public class HoverPotion extends Item {

	public HoverPotion(int x, int y, Board board) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
	}

	public boolean affectPlayer(Player player) {
		player.addBuff(Buff.Hover);
		return true;
	}

}
