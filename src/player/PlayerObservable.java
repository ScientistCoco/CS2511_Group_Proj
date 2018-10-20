package player;

/**
 * Interface for the player object to be observable by observers. 
 * They will update the observers if there are any changes to the player object.
 * @author court
 *
 */
public interface PlayerObservable {
	public void addObserver(PlayerObserver po);
	public void removeObserver(PlayerObserver po);
	public void notifyObservers();
	public boolean checkIfAlive();	
}
