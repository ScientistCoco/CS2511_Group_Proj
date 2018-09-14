package project;

public class Hunter extends Enemy {

	Hound hound = null;
	
	public Hunter(Board board) {
		super(board);
		this.icon = " ☻ ";
	}

	@Override
	public void updateMove(Player player) {
		this.trackPlayer(player.getXCoordinate(), player.getYCoordinate());
		if (hound != null) {
			notifyHound(player);
		}
	}
	
	public void notifyHound(Player player) {
		hound.updateMove(player);
	}
	
	public void addHound(Hound h) {
		this.hound = h;;
	}
}