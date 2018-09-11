package project;

public abstract class Item extends Entity{

	protected String name;
	protected ItemStatus status;
	
	public Item(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}
	
	public String getItemName() {
		return name;
	}
	
	public ItemStatus getStatus() {
		return status;
	}

	@Override
	public boolean affectPlayer(Player player) {
		player.getInventory().addItem(this);
		return false;
	}
	
	
}
