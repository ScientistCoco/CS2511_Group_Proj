package project;

public class Door extends Entity{

	private DoorStatus status;
	private int doorNum;
	
	public Door(int x, int y, Board board) {
		super(x, y, board);
		status = DoorStatus.Closed;
	}

	@Override
	public boolean affectPlayer(Player player) {
		if (status.equals(DoorStatus.Open)) {
			return false;
		}
		return true;
	}
	
	public void changeStatus(DoorStatus s) {
		status = s;
	}
	
	public DoorStatus getDoorStatus() {
		return this.status;
	}
	
	public int getDoorNum() {
		return doorNum;
	}
	
}
