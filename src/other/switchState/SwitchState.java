package other.switchState;

import points.Points1;

public interface SwitchState {
	/**
	 * onAction the switch will either gain a point or lose a point, depending on whether a boulder is on it or not.
	 * @param point, that will be gained/lost.
	 * @return a string indicating whether a point was lost or gained
	 */
	public String doAction(Points1 point); 
	
	public boolean getState();
}
