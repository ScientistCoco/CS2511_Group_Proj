package project;

public class Treasure extends Item {

	public Treasure(Board board) {
		super(board);
		this.point = new TreasurePoint();
		this.name = "Treasure";
		this.description = "A very shiny piece of treasure.";
		this.icon = " ☆ ";
	}
	
	// The treasure has no actions when it is used
	@Override
	public void useItem(Player player) {

	}

}
