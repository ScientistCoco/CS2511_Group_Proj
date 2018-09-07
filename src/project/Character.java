package project;

public abstract class Character extends Entity{
	protected Movement move;
	
	public Character(int x, int y) {
		super(x, y);
	}
	
	public abstract void move();
	
}
