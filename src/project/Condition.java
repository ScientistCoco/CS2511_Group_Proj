package project;

public class Condition {
	private String description;
	private boolean satisfied;
	private int amountRequired;
	private int amountCompleted;	// The amount of items/enemies that the player HAS FOUND/KILLED.
	
	/**
	 * Instantiates the Condition class
	 * @param desc: A description of the condition that needs to be satisfied
	 * @param amount_required: The amount of items/enemies the player needs to find/kill
	 */
	public Condition(String desc, int amountRequired) {
		this.description = desc;
		this.satisfied = false;
		this.amountRequired = amountRequired;
		this.amountCompleted = 0;
	}
	
	/**
	 * Method that returns the description of the completion condition
	 * @return a string of the description of the completion condition
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Method that returns whether the condition has been satisfied yet
	 * @return true/false if condition has been satisfied
	 */
	public boolean getSatisfiedState() {
		return this.satisfied;
	}
	
	public int getAmountCompleted() {
		return this.amountCompleted;
	}
	
	public int getAmountRequired() {
		return this.amountRequired;
	}
	
	/**
	 * Method that changes the satisfied state to true to indicate that the condition has been satisfied
	 */
	public void conditionSatisfied() {
		this.satisfied = true;
	}
	
	/**
	 * Method that increases the amountCompleted attribute to indicate that the player has completed part of the condition
	 */
	public void increaseAmountCompleted() {
		if (this.amountCompleted < this.amountRequired) {
			this.amountCompleted++;
		}
	}
}
