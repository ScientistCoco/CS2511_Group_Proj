package other.switchState;

import points.Points;

/**
 * The switch is turned on when a boulder is placed on top of it.
 *
 */
public class SwitchOnState implements SwitchState{

	@Override
	public String doAction(Points point) {
		point.pointAchieved();
		return "Switch has been turned on";
	}

	@Override
	public boolean getState() {
		return true;
	}

}
