package project;

/**
 * The wall class which prevents other entities from passing through it
 * @author court
 *
 */
public class Wall extends Entity{
	
	public Wall(int x, int y, Board board) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return false. Nothing is allowed to pass through the walls.
	 */
	@Override
	public boolean overlappingEffect(Entity entity) {
		return false;
	}
}
