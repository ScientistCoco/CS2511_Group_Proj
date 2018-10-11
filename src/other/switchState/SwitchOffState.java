package other.switchState;

import points.Points;

/**
 * The switch is turned off when a boulder is moved off it.
 *
 */
public class SwitchOffState implements SwitchState{

	@Override
	public String doAction(Points point) {
		point.pointLost();
		return "Switch has been turned off";
	}

	@Override
	public boolean getState() {
		return false;
	}

}
