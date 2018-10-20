package switchObj;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import other.Board;
import other.Boulder;
import other.Entity;
import points.PointType;
import points.Points1;

public class Switch extends Entity{
	
	private SwitchState state;
	
	/**
	 * Instantiates the Switch, its default state is off (false).
	 * @param board: The board for which this switch will be placed on.
	 */
	public Switch(Board board) {
		super(board);
		this.state = new SwitchOffState();
		this.icon = " Ø ";
		this.setName("Switch");
		this.point = new Points1(PointType.SwitchPoint);
		this.entityIcon = new ImageView(new Image("icons/floor_switch.png"));
	}
	
	/**
	 * Any other entities can be placed on the switch. However the only entity that will activate the switch
	 * is a boulder.
	 */
	@Override
	public boolean overlappingEffect(Entity entity) {
		if (!(entity instanceof Boulder)) {
			this.state = new SwitchOffState();
		} else if (entity instanceof Boulder){
			this.state = new SwitchOnState();
		}
		this.state.doAction(this.point);
		return true;
	}
	
	/**
	 * This method returns the state of the switch
	 * @return true/false depending on whether the switch has been activated or not
	 */
	public boolean getState() {
		return this.state.getState();
	}
}
