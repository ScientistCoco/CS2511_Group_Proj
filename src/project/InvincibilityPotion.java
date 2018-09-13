package project;

public class InvincibilityPotion extends Item{

	public InvincibilityPotion(Board board) {
		super(board);
	}

	@Override
	public boolean affectPlayer(Player player) {
		player.addBuff(Buff.Invincibility);
		return true;
	}

	@Override
	public void useItem(Player player) {
		affectPlayer(player);
	}

}
