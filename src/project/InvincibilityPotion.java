package project;

public class InvincibilityPotion extends Item{

	public InvincibilityPotion(Board board) {
		super(board);
		this.name = "invincibility potion";
		this.icon = " ♟ ";
	}

	@Override
	public void useItem(Player player) {
		player.addBuff(Buff.Invincibility);
	}

}
