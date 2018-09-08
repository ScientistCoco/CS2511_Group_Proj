package project;

public interface ArrowBehaviour extends WeaponBehaviour{
	public Direction getDirection();
	public void Fly(Direction d);
	public boolean destroyed();
}
