package project;

public abstract class Item extends Entity{

	protected String name;
	
	public Item(int x, int y, Board board) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
	}
	
	public String getItemName() {
		return name;
	}
	
}
