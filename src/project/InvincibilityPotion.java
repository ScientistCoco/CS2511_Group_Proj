package project;

public class InvincibilityPotion extends Item{

	public InvincibilityPotion(Board board) {
		super(board);
		this.name = "invincibility potion";
	}

	@Override
	public void useItem(Player player) {
		player.addBuff(Buff.Invincibility);
		items.removeItem(this);
	}

}
