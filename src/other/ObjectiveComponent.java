package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import points.Points;

public class ObjectiveComponent {
	private ArrayList<Points> objectives;
	
	/**
	 * Instantiates the ObjectiveComponent class
	 * 
	 */
	public ObjectiveComponent() {
		this.objectives = new ArrayList<Points>();
	}
	
	/**
	 * This method receives a point class which it will add to its 
	 * list of objectives for the player to complete.
	 * @param point: The Point class that will be added to the list of objectives
	 */
	public void addObjecitve(Points point) {
		this.objectives.add(point);
	}
	
	/**
	 * This method checks on all the objectives and reports back on whether all 
	 * the objectives have been completed or not
	 * @return true/false
	 */
	public boolean checkProgressOfObjectives() {
		Integer numCompleted = 0;
		for (Points objective : objectives) {
			if (objective.checkIfPointObtained()) numCompleted++;
		}
		
		return numCompleted == objectives.size();
	}
	
	/**
	 * This method will go through its list of points and categorize each point so that
	 * it can return a string of the proportions that the player needs to complete.
	 * @return
	 */
	public String getStringOfObjectives() {
		HashMap<Points, Integer> toBeCompleted = new HashMap<>();	// This is a hashmap containing all the objectives grouped
		HashMap<Points, Integer> numCompleted = new HashMap<>();	// This is a hashmap containing the number of objectives completed
		
		for (Points objective : objectives) {
			Integer count = toBeCompleted.get(objective);
			
			// If the map contains no mapping for the key, then initialize
			// its value to 0
			if (count == null) {
				count = 0;
			}
			
			// Increment the keys value by 1
			toBeCompleted.put(objective, count + 1);
			
			// Then we determine whether or not this objective has been completed yet or not
			if (objective.checkIfPointObtained()) {
				Integer hasCompleted = numCompleted.get(objective);
				
				if (hasCompleted == null) {
					count = 0;
				}
				
				numCompleted.put(objective, count + 1);
			}
		}
		
		// Now we format the hashmap into a string
		StringBuilder sb = new StringBuilder();
		for (Entry<Points, Integer> entry : toBeCompleted.entrySet()) {
			// This is a total of all the points
		    Points key = entry.getKey();
		    Integer totalPoints = (Integer) entry.getValue();
		    
		    // This is a total of points the player has obtained
		    Integer obtainedPoints = (Integer) numCompleted.get(key);
		    if (obtainedPoints == null) {
		    	obtainedPoints = 0;
		    }
		    
		    sb.append(key.getDescription() + "\t" + obtainedPoints + " \\ " + totalPoints + "\n");
		}
		return sb.toString();
	}
	 
}
