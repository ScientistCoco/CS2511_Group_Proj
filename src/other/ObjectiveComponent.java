package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import points.ObjectiveObserver;
import points.PointSubject;
import points.PointType;
import points.Points1;
import visuals.ObjectiveLabel;

public class ObjectiveComponent implements ObjectiveObserver {	
	// The arraylist contains the 'objects' this objectiveComponent has subscribed to.
	// They are categorized by the 'type' of point they are. 
	// i.e. Key : TreasurePoint | Value : [object1 - treasurePoint1, object2 - treasurePoint2]
	private HashMap<PointType, ArrayList<PointSubject>> listOfPoints;
	
	// In terms of passing an ObservableList to the controller we will have two data structures to manage this.
	// _list stores the 'String' that gets passed to the controller
	// _listIndex stores the PointType, and the index for each PointType corresponds to the index in _list.
	// This makes it easier for updating the front end when a point is gained/lost as we only need to change
	// one index rather than the whole list.
	// i.e. it would look like this:
	// _list = ["Collect a treasure : 0/1", "Kill an enemy : 0/2"]
	// _listIndex = [TreasurePoint, EnemyPoint];
	private ObservableList<Node> _list; 
	private ArrayList<PointType> _listIndex;	
	
	public ObjectiveComponent() {
		this.listOfPoints = new HashMap<PointType, ArrayList<PointSubject>>();
		this._list = FXCollections.observableArrayList();	
		this._listIndex = new ArrayList<PointType>();
	}
	
	/**
	 * This method adds the given point to the listOfPoints. This would occur when entities
	 * are placed on the board that have an objective associated with it.
	 * @param ps
	 */
	public void addPoint(PointSubject ps) {
		// Check if the hashmap contains the key yet.
		// If not we make a new key and value
		if (listOfPoints.containsKey(ps.getPointType())) {
			listOfPoints.get(ps.getPointType()).add(ps);
		} else {
			ArrayList<PointSubject> newList = new ArrayList<PointSubject>();
			newList.add(ps);
			listOfPoints.put(ps.getPointType(), newList);
			
			// Also need to add this to the _listIndex
			_listIndex.add(ps.getPointType());
			_list.add(new ObjectiveLabel("", ""));
		}
	}
	
	/**
	 * This method removes the given point from its list. This might occur when the player
	 * is designing the map but decides they don't want an entity on the map anymore and removes it,
	 * if the removed entity has an objective associated with it then we need to remove it as well from this list. 
	 * @param ps, the Point to remove
	 */
	public void removePoint(PointSubject ps) {
		listOfPoints.get(ps.getPointType()).remove(ps);
		// Suppose we remove a point then we get left with a hash that might look like this:
		// Key : TreasurePoint  | Value : []	i.e. an empty arraylist. It would be better to remove this
		// because getStringOfObjectives() would process this empty key.
		if (listOfPoints.get(ps.getPointType()).size() == 0) {
			listOfPoints.remove(ps.getPointType());
			// Also need to remove this from _listIndex, and if it has any entries in _list those entries need
			// to be removed. First check the size of both lists are equal before doing this. 
			if (_list.size() == _listIndex.size()) {
				_list.remove(_listIndex.indexOf(ps.getPointType()));
			}
			_listIndex.remove(ps.getPointType());
		}
	}
	
	/**
	 * Updates the contents in the observableList.
	 * @param ps, the point that has been changed.
	 */
	@Override
	public void update(PointSubject ps) {
		// If update is called then we need to update the content in the ObservableList so the
		// dungeonController knows to change the visible contents.
		// This occurs when a point is gained/lost.
		
		// First find what changed and make the new string
		StringBuilder sb = new StringBuilder();
		int pointsEarned = 0;
		for(PointSubject p : listOfPoints.get(ps.getPointType())) {
			if (p.checkPointObtained()) {
				pointsEarned++;
			}
		}
		
		sb.append(pointsEarned + " / " + listOfPoints.get(ps.getPointType()).size());
		
		// Now we find the pointType index in the _listIndex so we can change the content in _list
		int index = _listIndex.indexOf(ps.getPointType());
		_list.set(index, new ObjectiveLabel(ps.getPointType().getDescription(), sb.toString()));	
	}
	
	/**
	 * Returns an ObservableList of the objectives 
	 * @return
	 */
	public ObservableList<Node> getObservableObjectives() {
		for (PointType key : listOfPoints.keySet()) {
			this.update(listOfPoints.get(key).get(0));
		}
		return this._list;
	}
	
	/**
	 * This method checks if all the points have been cleared or not
	 * @return true/false
	 */
	public boolean checkProgress() {
		int numCompleted = 0;
		int numOfPoints = 0;
		for (PointType key : listOfPoints.keySet()) {
			ArrayList<PointSubject> points = listOfPoints.get(key);
			numOfPoints += points.size();
			// Then iterate through the points arraylist to see how many has been completed
			for (PointSubject p : points) {
				if (p.checkPointObtained()) {
					numCompleted++;
				}
			}
		}
		return numCompleted == numOfPoints; 
	}
	
	/**
	 * This returns a string of all the points that objectiveComponent knows about, it is formated as such:
	 * "<Point description> : <current points obtained> / <total points required>" 
	 * @return
	 */
	public String getStringOfObjectives() {
		StringBuilder sb = new StringBuilder();
		for (PointType key : listOfPoints.keySet()) {
			ArrayList<PointSubject> points = listOfPoints.get(key);
			
			int obtainedPoints = 0;
			for (PointSubject p : points) {
				if (p.checkPointObtained()) {
					obtainedPoints++;
				}
			}
			
			sb.append(key.getDescription() + ":\t" + obtainedPoints + " / " + points.size() + "\n");
		}
		return sb.toString();
	}
}
