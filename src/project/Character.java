package project;

public abstract class Character extends Entity{
	protected Movement move;
	private Integer health;
	
	public Character(int x, int y) {
		super(x, y);
		this.health = 1;
	}
	
	public abstract void move();
	
	public void depleteHealth() {
		this.health -= 1;
	}
	
}
