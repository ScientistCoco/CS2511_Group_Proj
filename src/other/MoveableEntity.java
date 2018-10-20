package other;

public class MoveableEntity extends Entity {
	protected MovementBehaviour move;
	
	public MoveableEntity(Board board) {
		super(board);
		this.move = new MovementBehaviour();
	}	
}
