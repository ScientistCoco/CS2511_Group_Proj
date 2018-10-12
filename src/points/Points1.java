package points;

import java.util.ArrayList;

public class Points1 implements PointSubject {
	// This is the objectives that have 'subscribed' to this point so they
	// can be notified when the point has been gained/lost
	private ArrayList<ObjectiveObserver> objectivesList;
	private boolean satisfied;
	private PointType pointType;
	
	public Points1(PointType type) {
		setPointType(type);
		this.satisfied = false;
		this.objectivesList = new ArrayList<ObjectiveObserver>();
	}
	
	public void setPointType(PointType type) {
		this.pointType = type;
	}
	
	public void notifyObserver() {
		for (ObjectiveObserver o : objectivesList) {
			o.update(this);
		}
	}
	
	public void pointAchieved() {
		this.satisfied = true;
		notifyObserver();
	}
	
	public void pointLost() {
		this.satisfied = false;
		notifyObserver();
	}

	@Override
	public void addObserver(ObjectiveObserver o) {
		this.objectivesList.add(o);
	}

	@Override
	public void removeObserver(ObjectiveObserver o) {
		this.objectivesList.remove(o);
	}

	@Override
	public PointType getPointType() {
		return this.pointType;
	}
	
	@Override
	public boolean checkPointObtained() {
		return this.satisfied;
	}
}
