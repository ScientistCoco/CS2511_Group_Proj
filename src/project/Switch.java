package project;

public class Switch extends Entity{
	
	private Boolean state;
	
	/**
	 * Instantiates the Switch, its default state is off (false).
	 * @param board: The board for which this switch will be placed on.
	 */
	public Switch(Board board) {
		super(board);
		this.state = false;
	}
	
	/**
	 * This method activates the switch, which can occur when a boulder is placed on top of it
	 */
	public void activate() {
		this.state = true;
		System.out.println("Switch turned on");
	}
	
	/**
	 * This method deactivates the switch, which can occur when a boulder is moved off it.
	 */
	public void deactivate() {
		this.state = false;
		System.out.println("Switch turned off");
	}
	
	/**
	 * Any other entities can be placed on the switch. However the only entity that will activate the switch
	 * is a boulder.
	 */
	@Override
	public boolean overlappingEffect(Entity entity) {
		if (entity instanceof Boulder) {
			activate();
		} else {
			deactivate();
		}
		return true;
	}
	
	/**
	 * This method returns the state of the switch
	 * @return true/false depending on whether the switch has been activated or not
	 */
	public boolean getState() {
		return this.state;
	}
}
