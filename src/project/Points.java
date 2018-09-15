package project;

/**
 * This class is related to the objectives that the player needs to complete in order to
 * complete the game level
 *
 */
public abstract class Points {
	private String description;
	private boolean satisfied;
	
	public Points () {
		this.description = this.setDescription();
		this.satisfied = false;
	}
	/**
	 * This returns a description of what the player needs to do in order to obtain this point.
	 * @return a description of this point
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * This is used to set the description for the point. A player should know what they need to do
	 * in order to achieve this point
	 */
	public abstract String setDescription();
	
	/**
	 * This returns what type this point is associated with.
	 * @return a string of the point type
	 */
	public String getType() {
		return this.getClass().getName();
	}
	
	/**
	 * Used to check whether or not this point has been satisfied yet or not.
	 * @return true/false
	 */
	public boolean checkIfPointObtained() {
		return this.satisfied;
	}
	
	/**
	 * This method is used when the point has been completed by the player. It will
	 * mark this point as 'achieved' (true). 
	 */
	public void pointAchieved() {
		this.satisfied = true;
	}
	
	/**
	 * This method is used when the point has been lost by the player. 
	 */
	public void pointLost() {
		this.satisfied = false;
	}
}
