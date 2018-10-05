package other;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import points.SwitchPoint;

public class Switch extends Entity{
	
	private Boolean state;
	
	/**
	 * Instantiates the Switch, its default state is off (false).
	 * @param board: The board for which this switch will be placed on.
	 */
	public Switch(Board board) {
		super(board);
		this.state = false;
		this.icon = " Ø ";
		this.point = new SwitchPoint();
		this.entityIcon = new ImageView(new Image("icons/floor_switch.png"));
	}
	
	/**
	 * This method activates the switch, which can occur when a boulder is placed on top of it
	 */
	public void activate() {
		this.state = true;
		System.out.println("Switch turned on");
		point.pointAchieved();
	}
	
	/**
	 * This method deactivates the switch, which can occur when a boulder is moved off it.
	 */
	public void deactivate() {
		this.state = false;
		System.out.println("Switch turned off");
		point.pointLost();
	}
	
	/**
	 * Any other entities can be placed on the switch. However the only entity that will activate the switch
	 * is a boulder.
	 */
	@Override
	public boolean overlappingEffect(Entity entity) {
		if (!(entity instanceof Boulder)) {
			deactivate();
		} else if (entity instanceof Boulder){
			activate();
		}
		System.out.println("Checking: " + entity.getClass().getName());
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
