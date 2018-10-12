package points;

public enum PointType {
	TreasurePoint("Collect a treasure piece located on the map"),
	ExitPoint("Find this exit"),
	EnemyPoint("Kill an enemy located on this map"),
	SwitchPoint("Activate a switch on the map using a boulder");
	
	private String description;
	
	PointType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
