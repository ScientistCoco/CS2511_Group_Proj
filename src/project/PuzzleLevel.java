package project;

import java.util.ArrayList;

public class PuzzleLevel {
	protected Board board;
	private ArrayList<Condition> objectives;
	
	/**
	 * Instantiates the puzzle level with an empty arraylist of objectives. It is expected that
	 * the creator will fill these objectives.
	 */
	public PuzzleLevel() {
		this.objectives = new ArrayList<>();
	}
	
	/**
	 * Returns a string of the objectives and its current state, i.e. how much has been completed.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for (Condition obj : objectives) {
			str.append(obj.getDescription() + " " + obj.getAmountCompleted() + " / " + obj.getAmountRequired() + "\n");
		}
		
		return str.toString();
	}
}
