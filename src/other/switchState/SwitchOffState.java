package other.switchState;

import points.Points1;

/**
 * The switch is turned off when a boulder is moved off it.
 *
 */
public class SwitchOffState implements SwitchState{

	@Override
	public String doAction(Points1 point) {
		point.pointLost();
		return "Switch has been turned off";
	}

	@Override
	public boolean getState() {
		return false;
	}

}
