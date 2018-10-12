package points;

public interface PointSubject {
	public void addObserver(ObjectiveObserver o);
	public void notifyObserver();
	public void removeObserver(ObjectiveObserver o);
	public PointType getPointType();
	public boolean checkPointObtained();
}
